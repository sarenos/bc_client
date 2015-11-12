package com.example.bambicity.APILayers.Friends.confirm;

import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebView;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.APILayers.Friends.FriendManagerConfig;
import com.example.bambicity.APILayers.Friends.invite.InviteToFriendsMangerResponseModel;
import com.example.bambicity.model.UserInfoModel;

public class ConfirmFriendManager extends Manager {
	private FriendManagerConfig confirmFriendManagerConfig;
	  public String ACTION_URL = "friends.php";
	  public String METOD = "post";
	  public String ACTION_CONFIRM = "confirm";


		public ConfirmFriendManager(FriendManagerConfig confirmFriendManagerConfig)
		{
			this.confirmFriendManagerConfig = confirmFriendManagerConfig;
		}
		
		@Override
		public void initRequestParams()
		{
			params.put("user_from", UserInfoModel.getInstance().getUserId());
			params.put("user_to", confirmFriendManagerConfig.getUserTo());
		}
		
		@Override
		public void initRequestConfig()
		{
			httpRequestConfig.setActionUrl(ACTION_URL);
			httpRequestConfig.setMetod(METOD);
			httpRequestConfig.setParams(params);
			httpRequestConfig.setAction(ACTION_CONFIRM);
			httpRequestConfig.setHTTPService(this);
		}
		
		@Override
		public void success()
		{		
			confirmFriendManagerConfig.getUserAccountFragment().getUserResponseModel().setFriendStatus(1);
			final WebView webView = confirmFriendManagerConfig.getUserAccountFragment().getWebView();
			webView.post(new Runnable() {
			    @Override
			    public void run() {
			    	webView.loadUrl("javascript:  confirmSuccess(); ");
			    }
			});
		}
		
		@Override
		public void failed()
		{
			final WebView webView = confirmFriendManagerConfig.getUserAccountFragment().getWebView();
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
