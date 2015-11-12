package com.example.bambicity.workers;

import com.example.bambicity.APILayers.chat.history.ChatManager;
import com.example.bambicity.APILayers.chat.history.ChatManagerConfig;
import com.example.bambicity.model.UserInfoModel;

public class ChatWorker implements Runnable {
    private boolean running = true;
	private ChatManagerConfig chatManagerConfig;
	private ChatManager chatManager;
	private Thread threadLoadLocations;
    
	public ChatWorker (ChatManagerConfig chatManagerConfig)
	{
		this.chatManagerConfig = chatManagerConfig;
		chatManager = new ChatManager(chatManagerConfig);
		threadLoadLocations = new Thread(this);
		threadLoadLocations.start();
	}
	  
	@Override
	public void run() {
		while (running)
		{		
			if(UserInfoModel.getInstance().isLogin())
			{
				chatManager.send();	
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
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

