package com.example.bambicity.APILayers.messages;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;


public class MessagesManager extends Manager {
    public static String ACTION_URL = "messages.php";
    public static String ACTION = "list_users";
    public static String METOD = "get";
	
	private MessagesManagerConfig messagesManagerConfig;
	
	public MessagesManager(MessagesManagerConfig messagesManagerConfig)
	{
		this.messagesManagerConfig = messagesManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{	
		params.put("user", UserInfoModel.getInstance().getUserId());
		params.put("action", ACTION);
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
    	httpRequestConfig.setAction(ACTION);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{
		messagesManagerConfig.getMessagesListFragment().showFriends(((MessagesListResponseModel)responseModel).getUsersList());
	}
	
	@Override
	public void failed()
	{

	}
	
	@Override
	public MessagesListResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new MessagesListResponseModel(finalResult);
	}
}
