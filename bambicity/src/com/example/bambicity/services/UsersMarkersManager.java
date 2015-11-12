package com.example.bambicity.services;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.bambicity.R;
import com.example.bambicity.model.RequestUsersJsonMaper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UsersMarkersManager {
	
	private RequestUsersJsonMaper requestUsersJsonMaper;
	private Marker markerUser;
	
	public UsersMarkersManager(JSONObject jsonObject)
	{
		try {
			requestUsersJsonMaper = new RequestUsersJsonMaper(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createUserMarker(GoogleMap map)
	{
		markerUser = map.addMarker(new MarkerOptions().position(new LatLng(requestUsersJsonMaper.getLatitude(), requestUsersJsonMaper.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.drawable.icn_marker_user_offline))
				     .title(requestUsersJsonMaper.getUser_account() + " (" + requestUsersJsonMaper.getDateCreate() + ")"));
	}
	
	public void updateLocations()
	{
		if(markerUser == null){
		Log.d("test2","null");
		}
		else{
		markerUser.setPosition(new LatLng(requestUsersJsonMaper.getLatitude(), requestUsersJsonMaper.getLongitude()));
		Log.d("test2",Integer.toString(requestUsersJsonMaper.getId())+"-ok");
		}
	}
	
	public RequestUsersJsonMaper getRequestUsersJsonMaper() {
		return requestUsersJsonMaper;
	}

	public void setRequestUsersJsonMaper(RequestUsersJsonMaper requestUsersJsonMaper) {
		this.requestUsersJsonMaper = requestUsersJsonMaper;
	}

	public Marker getMarkerUser() {
		return markerUser;
	}

	public void setMarkerUser(Marker markerUser) {
		this.markerUser = markerUser;
	}
}
