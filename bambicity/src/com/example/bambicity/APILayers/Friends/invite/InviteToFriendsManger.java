package com.example.bambicity.APILayers.Friends.invite;

import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebView;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.APILayers.Friends.FriendManagerConfig;
import com.example.bambicity.model.UserInfoModel;

public class InviteToFriendsManger extends Manager {
	private FriendManagerConfig friendsManagerConfig;
  public String ACTION_URL = "friends.php";
  public String METOD = "post";
  public String ACTION_INVITE = "invite";


	public InviteToFriendsManger(FriendManagerConfig friendsManagerConfig)
	{
		this.friendsManagerConfig = friendsManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user_from", UserInfoModel.getInstance().getUserId());
		params.put("user_to", friendsManagerConfig.getUserTo());
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setAction(ACTION_INVITE);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{		
		friendsManagerConfig.getUserAccountFragment().getUserResponseModel().setFriendStatus(0);
		final WebView webView = friendsManagerConfig.getUserAccountFragment().getWebView();
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	webView.loadUrl("javascript:  inviteSuccess(); ");
		    }
		});
	}
	
	@Override
	public void failed()
	{
		final WebView webView = friendsManagerConfig.getUserAccountFragment().getWebView();
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	webView.loadUrl("javascript:  failed('"+ responseModel.getStatusMsg() +"'); ");
		    }
		});
	}
	
	@Override
	public InviteToFriendsMangerResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new InviteToFriendsMangerResponseModel(finalResult);
	}
}