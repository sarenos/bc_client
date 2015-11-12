package com.example.bambicity.view.user_account;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.users.UserResponseModel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class UserAccountFragment extends Fragment {
	
	private static final String JS_API_NAME = "UAAPI";
	private static final String FILE_PAGE_PATH = "file:///android_asset/pages/";
    private static final String FILE_PAGE_NAME = "user_account.html";
	private UserAccountBridgeJS userAccountBridgeJS;
	private WebView webView;
	private View rootView;
	private UserResponseModel userResponseModel;
	UserResponseModel friendResponseModel;
	
	public UserAccountFragment(UserResponseModel userResponseModel)
	{
		this.userResponseModel = userResponseModel;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.user_account_fragment, container, false);
		init();
		return rootView;
	}
		
	@SuppressLint("NewApi")
	private void init()
	{				    
		userAccountBridgeJS = new UserAccountBridgeJS(this);
		webView = (WebView) rootView.findViewById(R.id.userAccountWebView);
		webView.setVerticalScrollBarEnabled(false);        
		webView.setHorizontalScrollBarEnabled(false);      
		webView.getSettings().setJavaScriptEnabled(true);  
		webView.getSettings().setDomStorageEnabled(true);  
		webView.getSettings().setSupportZoom(false);       
		webView.getSettings().setSupportMultipleWindows(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.addJavascriptInterface(userAccountBridgeJS, JS_API_NAME);   		                                                                 
		webView.loadUrl(FILE_PAGE_PATH + FILE_PAGE_NAME);
		webView.setWebChromeClient(new WebChromeClient());
	}
	
	public View getRootView()
	{
		return rootView;
	}
	
	public WebView getWebView()
	{
		return webView;
	}
	
	public UserResponseModel getUserResponseModel()
	{
		return userResponseModel;
	}
	
	public UserResponseModel getFriendResponseModel()
	{
		return friendResponseModel;
	}
}
