package com.example.bambicity.view.map;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.users.UserResponseModel;
import com.example.bambicity.services.ImageLoader;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class MarkerIconAdapter implements InfoWindowAdapter {
	
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    private UserResponseModel usersMarkersManager;
    Marker marker;
    HashMap<String, UserResponseModel> markers;
    HashMap<String, String> markersId;


	MarkerIconAdapter(Activity activity,
			HashMap<String, UserResponseModel> markers, HashMap<String, String> markersId ){
	inflater = (LayoutInflater)activity.
            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    imageLoader = new ImageLoader(activity.getApplicationContext());
    this.markers = markers;
    this.markersId = markersId;
	}
	@Override
	public View getInfoContents(Marker arg0)
	{
		// TODO Auto-generated method stub
        Log.d("adapter2", "okey");
        usersMarkersManager = markers.get(markersId.get(arg0.getId()));
        View v  = inflater.inflate(R.layout.infowindow_layout, null);
        ImageView image = (ImageView)v.findViewById(R.id.marker_icon);
        TextView title = (TextView) v.findViewById(R.id.marker_title);
        TextView age = (TextView) v.findViewById(R.id.marker_age);
        TextView onlineStatus = (TextView) v.findViewById(R.id.marker_online_status);

        title.setText(usersMarkersManager.getNickName());
        age.setText(usersMarkersManager.getAge());
        imageLoader.DisplayImage("http://bambicity.url.ph/new_api/" + usersMarkersManager.getPhoto(), image, usersMarkersManager.getMarker());
        onlineStatus.setText(usersMarkersManager.isOnline()?"online":"offline");
		return v;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
