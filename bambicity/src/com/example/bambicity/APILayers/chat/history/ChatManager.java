package com.example.bambicity.APILayers.chat.history;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;

public class ChatManager extends Manager {
	private ChatManagerConfig chatManagerConfig;
    public String ACTION_URL = "messages.php";
    public String METOD = "get";
    public String ACTION = "one_friend";
	private Intent intent;



	public ChatManager(ChatManagerConfig chatManagerConfig)
	{
		this.chatManagerConfig = chatManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user", UserInfoModel.getInstance().getUserId());
		params.put("friend", chatManagerConfig.getFriendId());
		params.put("action", ACTION);
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setAction(ACTION);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{
		chatManagerConfig.getChatFragment().showMessage(((CathListResponseModel)responseModel).getUsersList());
	}
	
	@Override
	public void failed()
	{

	}
	
	@Override
	public CathListResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new CathListResponseModel(finalResult);
	}
}
