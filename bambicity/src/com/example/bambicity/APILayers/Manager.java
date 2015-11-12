package com.example.bambicity.APILayers;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.bambicity.APILayers.HTTP.HttpRequest;
import com.example.bambicity.APILayers.HTTP.HttpRequestConfig;

import android.annotation.SuppressLint;

public abstract class Manager {
    protected HttpRequestConfig httpRequestConfig;
	protected JSONTokener tokener;
	protected ResponseModel responseModel;
	protected HashMap<String, String> params;

	protected Manager()
	{
		params = new HashMap<String, String>();
	    httpRequestConfig = new HttpRequestConfig();
	}
	
	public abstract void initRequestParams();	
	public abstract void initRequestConfig();
	public abstract ResponseModel getResponseModel(JSONObject finalResult) throws JSONException;
	public abstract void success();	
	public abstract void failed();	
	
	public void send()
	{
		initRequestParams();
		initRequestConfig();
		new Thread(new HttpRequest(httpRequestConfig)).start();
	}
	
	@SuppressLint("NewApi")
	public void parseResponse()
	{
		tokener = new JSONTokener(httpRequestConfig.getResponse());
		JSONObject finalResult;
 			try {
				finalResult = new JSONObject(tokener);
				responseModel = getResponseModel(finalResult);
				strategyActions();
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	
	private void strategyActions()
	{
		switch (responseModel.getStatus()) 
		{
			case 0:
				success();
				break;
				
			case 1:
				failed();
				break;
				
			case 2:
				exitApp();
				break;	
				
			default:	
				exitApp();
		}
	}
	
	
	protected void exitApp()
	{
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
}
