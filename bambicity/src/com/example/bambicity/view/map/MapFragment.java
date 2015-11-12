package com.example.bambicity.view.map;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import com.example.bambicity.R;
import com.example.bambicity.APILayers.users.UserResponseModel;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.user_account.UserAccountFragment;
import com.example.bambicity.workers.UsersWoker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MapFragment extends Fragment {
	private static MapFragment fragment = new MapFragment();
	private MapView mMapView;
	private GoogleMap map;
	private UsersWoker loadLocations;
	private HashMap<String, UserResponseModel> markers;
	private HashMap<String, String> markersId;
	private boolean isShow;
    Circle circle;
    MarkerIconAdapter markerIconAdapter;
    ClusterManager clusterManager;
    

	public static MapFragment newInstance() {
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_fragment, container,
				false);

		initMap(rootView, savedInstanceState);
		markers = new HashMap<String, UserResponseModel>();
		markersId = new HashMap<String, String>();
		loadLocations = new UsersWoker(this);
		clusterManager = new ClusterManager<UserResponseModel>(getActivity(), map);
		clusterManager.setRenderer(new CustomRenderer<UserResponseModel>(getActivity(), map, clusterManager));
		map.setOnCameraChangeListener(clusterManager);
		clusterManager.cluster();

		return rootView;
	}

	private void initMap(View rootView, Bundle savedInstanceState) {
		mMapView = (MapView) rootView.findViewById(R.id.map);
		mMapView.onCreate(savedInstanceState);
		mMapView.onResume();
		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		map = mMapView.getMap();
		map.setMyLocationEnabled(true);
		map.setBuildingsEnabled(true);
		UserInfoModel.getInstance().setMap(map);
		CircleOptions circleOptions = new CircleOptions().center(new LatLng(0, 0)).radius(0)
        .strokeColor(Color.DKGRAY)
        .strokeWidth(5);
		circle = map.addCircle(circleOptions);
		UserInfoModel
				.getInstance()
				.getMap()
				.setOnMyLocationChangeListener(
						new OnMyLocationChangeListener() {
							@Override
							public void onMyLocationChange(Location location) {
								UserInfoModel.getInstance()
										.setLat(Double.toString(location
												.getLatitude()));
								UserInfoModel.getInstance()
										.setLot(Double.toString(location
												.getLongitude()));
							    
								circle.setCenter(new LatLng(Double.parseDouble(UserInfoModel.getInstance().getLat()), Double.parseDouble(UserInfoModel.getInstance().getLot())));
								circle.setRadius(UserInfoModel.getInstance().getUserFilterModel() == null ? 0 : UserInfoModel.getInstance().getUserFilterModel().getFilterRadius() * 1000);
								circle.setStrokeColor(Color.GREEN);
								circle.setStrokeWidth(5);
							}
						});
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
            	String usertId = markersId.get(marker.getId());
            	UserResponseModel urj = markers.get(usertId);
            	FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        		fragmentManager
        				.beginTransaction()
        				.replace(R.id.container,
        					 new UserAccountFragment(urj)).commit();
               
            }
        });

		if(UserInfoModel.getInstance().getSelectedUser() != null)
			showUserLocation();
	}
	
	private void showUserLocation()
	{
		  CameraPosition cameraPosition = new CameraPosition.Builder()
	        .target(new LatLng(Double.parseDouble(UserInfoModel.getInstance().getSelectedUser().getLat()), Double.parseDouble(UserInfoModel.getInstance().getSelectedUser().getLng())))
	        .zoom(18)
	        .bearing(45)
	        .build();
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
	    map.animateCamera(cameraUpdate);	    
		UserInfoModel.getInstance().setSelectedUser(null);
	}
	
	public void showMyLocation()
	{
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				  CameraPosition cameraPosition = new CameraPosition.Builder()
			        .target(new LatLng(Double.parseDouble(UserInfoModel.getInstance().getLat()), Double.parseDouble(UserInfoModel.getInstance().getLot())))
			        .zoom(18)
			        .bearing(45)
			        .build();
			    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
			    map.animateCamera(cameraUpdate);
			    UserInfoModel.getInstance().setShowMyLocation(true);
			}
		});
	}

	@SuppressLint("NewApi")
	public void showUsers() {		
		if (getActivity() != null) {
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
	        		markersId.clear();
					 ArrayList<UserResponseModel> usersList = UserInfoModel.getInstance().getUsersList();
				        if (usersList == null || usersList.isEmpty())
				        {
							clearMarkers();
				        	return;
				        }
							for(UserResponseModel user : usersList)
							{
								try {
									
									addToViewNewMarker(user);
									clusterManager.cluster();

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}					
							map.setInfoWindowAdapter(new MarkerIconAdapter(getActivity(), markers, markersId));
							clearMarkers();
				}
			});
		}
	}
	
	private void clearMarkers()
	{
		for(String key : markers.keySet())
		{
			if(!markersId.containsValue(key))
			{
				markers.get(key).getMarker().setVisible(false);
			}
		}
	}

	private void addToViewNewMarker(UserResponseModel jsonObject) throws JSONException {
		if (!markers.containsKey(jsonObject.getUserId())) {
			
			Marker marker =	map.addMarker(new MarkerOptions()
			.position(
					new LatLng(Double.parseDouble(jsonObject.getLat()), Double.parseDouble(jsonObject.getLng()))
					)
			.icon(BitmapDescriptorFactory
					.fromResource(jsonObject.getSex().equals("m") ? R.drawable.circle_blue : R.drawable.circle_red)));
			//.title(usersMarkersManager.getName()
					//+ " ("
				//	+ usersMarkersManager.getDateCreate() + ")"
					//).snippet(usersMarkersManager.getAge()));
			jsonObject.setMarker(marker);
			markers.put(
					jsonObject.getUserId(), jsonObject);
			markersId.put(marker.getId(), jsonObject.getUserId());
						
		} else {

			markers.get(jsonObject.getUserId()).getMarker().setPosition(
					new LatLng(Double.parseDouble(jsonObject.getLat()),
							Double.parseDouble(jsonObject.getLng())));
			markers.get(jsonObject.getUserId()).getMarker().setVisible(true);
			markersId.put(markers.get(jsonObject.getUserId()).getMarker().getId(), jsonObject.getUserId());
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
		loadLocations.start();
		UserLocationManager.getInstance().stop();
	}

	@Override
	public void onPause() {
		super.onPause();
		loadLocations.stop();
		mMapView.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
		loadLocations.stop();
		UserLocationManager.getInstance().start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}
}
