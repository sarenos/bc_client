package com.example.bambicity.APILayers.chat.send;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;

public class SendMessageManager extends Manager {
	private SendMessageManagerConfig chatManagerConfig;
    public String ACTION_URL = "messages.php";
    public String METOD = "post";
    public String ACTION = "send";
	private Intent intent;



	public SendMessageManager(SendMessageManagerConfig chatManagerConfig)
	{
		this.chatManagerConfig = chatManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user_from", UserInfoModel.getInstance().getUserId());
		params.put("user_to", chatManagerConfig.getUserTo());
		params.put("message", chatManagerConfig.getMessage());
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
		//chatManagerConfig.getChatFragment().getArchive();
	}
	
	@Override
	public void failed()
	{

	}
	
	@Override
	public SendMessageResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new SendMessageResponseModel(finalResult);
	}
}
