package com.example.bambicity.view.map;

import com.example.bambicity.model.UserInfoModel;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class UserLocationManager implements LocationListener{
	private static UserLocationManager userLocationManager;

	private LocationManager  locationManager;
	private Criteria criteria;
	private String provider;
	
	public static void initInstance(LocationManager  locationManager)
	{
		if(userLocationManager == null)
		{
			userLocationManager = new UserLocationManager(locationManager);
		}
	}
	
	public static UserLocationManager getInstance()
	{
		return userLocationManager;
	}
	
	private UserLocationManager(LocationManager  locationManager)
	{
		this.locationManager = locationManager;
	}
	
	public LocationManager getLocationManager()
	{
		return locationManager;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		//Log.d("loc", "manager loc");

		UserInfoModel.getInstance().setLat(Double.toString(location.getLatitude()));
		UserInfoModel.getInstance().setLot(Double.toString(location.getLongitude()));
	//	UserInfoModel.getInstance().setLocTime(location.getTime());
		UserInfoModel.getInstance().setSendStatus(false);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
	public void stop()
	{
		if(locationManager != null)
        locationManager.removeUpdates(this);
	}
	
	public void start()
	{
		
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
    	criteria.setBearingRequired(true);
    	criteria.setCostAllowed(true);
    	criteria.setPowerRequirement(Criteria.POWER_LOW);
    	criteria.setAltitudeRequired(false);
    	criteria.setSpeedRequired(false);
        provider = locationManager.getBestProvider(criteria, true);
    		    locationManager.requestLocationUpdates(
    		    		LocationManager.NETWORK_PROVIDER, 1000 * 30, 10, this);    
    		    	
	}

}
