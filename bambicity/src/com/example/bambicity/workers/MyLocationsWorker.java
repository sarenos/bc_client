package com.example.bambicity.workers;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.example.bambicity.R;
import com.example.bambicity.APILayers.my_location.MyLocationsManager;
import com.example.bambicity.APILayers.my_location.MyLocationsManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.map.UserLocationManager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;

@SuppressLint("NewApi")
public class MyLocationsWorker extends Service implements Runnable 
{
	private LocationManager  locationManager;
	private PendingIntent pendingIntent;
	private Notification notification;
	private Thread threadSendLocation;
	static UserLocationManager userLocatinManager;
	MyLocationsManagerConfig locationsManagerConfig;
	MyLocationsManager locationsManager;
	
    @Override
    public void onCreate()
    {
        threadSendLocation = new Thread(this);
    	initNotification();
    	locationsManagerConfig = new MyLocationsManagerConfig();
    	locationsManager = new MyLocationsManager(locationsManagerConfig);
    }
    
    private void initNotification()
    {
        notification = new Notification(R.drawable.common_signin_btn_icon_dark, "text", System.currentTimeMillis());
        Intent main = new Intent(this, LocationManager.class);
        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, main,  PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(this, "title", "text", pendingIntent);
        notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_FOREGROUND_SERVICE | Notification.FLAG_NO_CLEAR;
    }
    
    @Override
	public int onStartCommand(Intent intent, int flags, int startId)
    {
        startForeground(1, notification);  
        if(!threadSendLocation.isAlive())
        {
            threadSendLocation.start();
        }
        return START_STICKY;
      }

	
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	
    @Override
    public void onDestroy() {
    	super.onDestroy();
        threadSendLocation.stop();
    }

	@Override
	public void run() {
		while (true) {
			try {
				if (UserInfoModel.getInstance().getLat() == null && UserInfoModel.getInstance().getLot() == null || !UserInfoModel.getInstance().isLogin()) {
					TimeUnit.SECONDS.sleep(1);
					continue;
				}
				
				if(!UserInfoModel.getInstance().getLot().equals(UserInfoModel.getInstance().getLastSendLocationData().getLot()) 
					|| !UserInfoModel.getInstance().getLat().equals(UserInfoModel.getInstance().getLastSendLocationData().getLat()) 
					|| (new Date().getTime() - UserInfoModel.getInstance().getLastSendLocationData().getDate().getTime()) >= (5 * 60 * 1000))
				{
					locationsManager.send();
				}
				
				TimeUnit.SECONDS.sleep(30);
			} catch (Exception e) {
				
				e.printStackTrace();

				continue;

			}
		}
	}
}
