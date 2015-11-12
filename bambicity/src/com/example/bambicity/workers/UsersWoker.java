package com.example.bambicity.workers;

import com.example.bambicity.APILayers.users.UsersManager;
import com.example.bambicity.APILayers.users.UsersManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.map.MapFragment;

public class UsersWoker implements Runnable {
    private boolean running = true;
	private UsersManagerConfig usersManagerConfig;  
	private UsersManager usersManager;  
	private Thread threadLoadLocations;
    
	public UsersWoker (MapFragment mapFragment)
	{
		usersManagerConfig = new UsersManagerConfig(mapFragment);
		usersManager = new UsersManager(usersManagerConfig);
		threadLoadLocations = new Thread(this);
		threadLoadLocations.start();
	}
	  
	@Override
	public void run() {
		while (running)
		{		
			if(UserInfoModel.getInstance().isLogin())
			{
				usersManager.send();
			}
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			continue;
		}
		
	}
	
	public void stop()
	{
		running = false;
	}
	
	public void start()
	{
		running = true;
	}
}
