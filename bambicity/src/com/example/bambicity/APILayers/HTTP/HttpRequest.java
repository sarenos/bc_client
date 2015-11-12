package com.example.bambicity.APILayers.HTTP;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.util.Log;

public class HttpRequest implements Runnable {
	private HttpClient httpclient;
	private HttpUriRequest  httpMetod;
	private String port = "http";
	private String serverUri = "bambicity.url.ph";
	private String applicationUrl = "new_api";
	private HttpParams httpParameters;
	private int timeoutConnection = 20000;
	private int timeoutSocket = 20000;
	HttpRequestConfig httpRequestConfig;
	Uri.Builder uriRequest;

	public HttpRequest(HttpRequestConfig httpRequestConfig)
	{
		this.httpRequestConfig = httpRequestConfig;
		initRequestParams();
		initRequestUri();
		callRequestMetod();
	}
	
	private void initRequestParams()
	{
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpclient = new DefaultHttpClient(httpParameters);
	}
	
	private void initRequestUri()
	{
		uriRequest = new Uri.Builder()
	    .scheme(port)
	    .authority(serverUri)
	    .path(applicationUrl + "/" + httpRequestConfig.getActionUrl());
	}
	
	private void callRequestMetod()
	{
		try {
			this.getClass().getMethod(httpRequestConfig.getMetod(), null).invoke(this, null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	private void addUrlParams()
	{
		HashMap<String, String> selects = httpRequestConfig.getParams();
		for(Entry<String, String> entry : selects.entrySet()) 
		{
			String key = entry.getKey();
			String value = entry.getValue();
			uriRequest.appendQueryParameter(key, value);
		}  
	}
	
	private String getFullRequestUrl()
	{
		return uriRequest.build().toString();
	}
	
	public void get() throws URISyntaxException
	{
		addUrlParams();
		httpMetod = new HttpGet(getFullRequestUrl());
	}
	
	public void post()
	{		
		try {
			httpMetod = addBodyRequestParams();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private HttpPost addBodyRequestParams() throws UnsupportedEncodingException
	{
		HttpPost httpPost = new HttpPost(getFullRequestUrl()+"?action=" + httpRequestConfig.getAction());

		HashMap<String, String> selects = httpRequestConfig.getParams();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(selects.size());
		for(Entry<String, String> entry : selects.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			uriRequest.appendQueryParameter(key, value);
	        nameValuePairs.add(new BasicNameValuePair(key, value));
		}  
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

			return httpPost;
	}
	
	@Override
	public void run() {
		HttpResponse response;
		HttpEntity entity;
		while(true){

				Log.d("request","HttpRequest start");

				try {
					response = httpclient.execute(httpMetod);

				entity = response.getEntity();

				httpRequestConfig.setResponse(EntityUtils.toString(entity));
				Log.d("request",httpRequestConfig.getActionUrl());
				Log.d("response",httpRequestConfig.getResponse());

				httpRequestConfig.getHTTPService().parseResponse();
				
				Log.d("request","HttpRequest stop");
			    return;
				} catch (Exception e) {
					e.printStackTrace();
					Log.d("request","HttpRequest Exception");
					continue;
				}
			}
		
	}
}
