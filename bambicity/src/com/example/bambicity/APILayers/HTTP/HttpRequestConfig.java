package com.example.bambicity.APILayers.HTTP;

import java.net.URI;
import java.util.HashMap;

import com.example.bambicity.APILayers.Manager;

import android.os.Handler;

public class HttpRequestConfig {
	private Handler handler;
	private String actionUrl;
	private String metod;
	private int handlerMessage = 1;
	private URI uri;
	private HashMap <String, String> params;
	private String response;
	private String action;
	private Manager httpService;
	
	public Manager getHTTPService() {
		return httpService;
	}
	public void setHTTPService(Manager httpService) {
		this.httpService = httpService;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public int getHandlerMessage() {
		return handlerMessage;
	}
	public void setHandlerMessage(int handlerMessage) {
		this.handlerMessage = handlerMessage;
	}
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getMetod() {
		return metod;
	}
	public void setMetod(String metod) {
		this.metod = metod;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
}
