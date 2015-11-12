package com.example.bambicity.view.user_account;

import android.webkit.JavascriptInterface;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.Friends.FriendManagerConfig;
import com.example.bambicity.APILayers.Friends.confirm.ConfirmFriendManager;
import com.example.bambicity.APILayers.Friends.delete.DeleteFriendManager;
import com.example.bambicity.APILayers.Friends.invite.InviteToFriendsManger;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.chat.ChatFragment;
import com.example.bambicity.view.map.MapFragment;

public class UserAccountBridgeJS {
	
		private UserAccountFragment userAccountFragment;
		private InviteToFriendsManger addToFriendsManger;
		private ConfirmFriendManager confirmFriendManager;
		private FriendManagerConfig friendManagerConfig;
		private DeleteFriendManager deleteFriendManager;


		UserAccountBridgeJS(UserAccountFragment userAccountFragment)
		{
			this.userAccountFragment = userAccountFragment;
			this.friendManagerConfig = new FriendManagerConfig();
			friendManagerConfig.setUserTo(userAccountFragment.getUserResponseModel().getUserId());
			friendManagerConfig.setFriendStatus(userAccountFragment.getUserResponseModel().getFriendStatus());
			friendManagerConfig.setUserAccountFragment(userAccountFragment);
			this.addToFriendsManger = new InviteToFriendsManger(friendManagerConfig);			
			confirmFriendManager = new ConfirmFriendManager(friendManagerConfig);
			deleteFriendManager = new DeleteFriendManager(friendManagerConfig);
	    }

	    @JavascriptInterface
	    public String getNickName()
	    {
	    	return userAccountFragment.getUserResponseModel().getNickName();
	    }

	    @JavascriptInterface
	    public String getSex() 
	    {
		    return userAccountFragment.getUserResponseModel().getSex();
	    }
	    
	    @JavascriptInterface
	    public String getPhoto() 
	    {
	    	if(userAccountFragment.getUserResponseModel().getPhoto().equals(""))
	    		return "../image/no_photo.jpg";
	    	return "http://bambicity.url.ph/new_api/" + userAccountFragment.getUserResponseModel().getPhoto();
	    }
	    
	    @JavascriptInterface
	    public String getAge() 
	    {
		    return userAccountFragment.getUserResponseModel().getAge();
	    }
	    
	    @JavascriptInterface
	    public int getFriendStatus() 
	    {
		    return userAccountFragment.getUserResponseModel().getFriendStatus();
	    }
	    
	    @JavascriptInterface
	    public String getOnlineStatus() 
	    {
		    return userAccountFragment.getUserResponseModel().isOnline()?"online":"offline";
	    }
	    
	    @JavascriptInterface
	    public void addToFriend() 
	    {
	    	if(userAccountFragment.getUserResponseModel().getFriendStatus() == 0)
	    	{
	    		confirmFriendManager.send();
	    	} else {	
	    	addToFriendsManger.send();
	    	}
	    }
	    
	    @JavascriptInterface
	    public void deleteFriend() 
	    {
	    	deleteFriendManager.send();
	    }
	    @JavascriptInterface
	    public void sendMessage() 
	    {	
	    	userAccountFragment.getActivity().getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, new ChatFragment(userAccountFragment.getUserResponseModel().getUserId()), "ChatFragment")
			.commit();
	    }
	    
	    @JavascriptInterface
	    public void showOnMap() 
	    {
	    	UserInfoModel.getInstance().setSelectedUser(userAccountFragment.getUserResponseModel());
	    	userAccountFragment.getActivity().getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, MapFragment.newInstance(), "MapFragment")
			.commit();
	    }
}
