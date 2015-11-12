package com.example.bambicity.APILayers.Registration;


import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebView;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.APILayers.user_info.ProfileManager;
import com.example.bambicity.APILayers.user_info.ProfileManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.main.MainActivity;

public class RegistrationManager extends Manager {
    public static String ACTION_URL = "user.php";
    public static String ACTION = "create";
    public static String METOD = "post";
	private RegistrationManagerConfig registrationManagerConfig;  
	private ProfileManagerConfig profileManagerConfig;
	private ProfileManager profileManager;


	public RegistrationManager(RegistrationManagerConfig registrationManagerConfig)
	{
		this.registrationManagerConfig = registrationManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("nick", registrationManagerConfig.getNickName());
		params.put("sex", registrationManagerConfig.getSex());
		params.put("age", registrationManagerConfig.getAge());
		params.put("user_account", UserInfoModel.getInstance().getUserAccount());
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
		UserInfoModel.getInstance().setUserId(((RegistrationResponseModel) responseModel).getUserId());
		this.profileManagerConfig = new ProfileManagerConfig();
		this.profileManagerConfig.setMainActivity(((MainActivity)registrationManagerConfig.getRegistrationFragment().getActivity()));
		this.profileManager = new ProfileManager(profileManagerConfig);
		profileManager.send();
	}
	
	@Override
	public void failed()
	{
	    UserInfoModel.getInstance().getProgressDialog().hide();			
		final WebView webView = registrationManagerConfig.getRegistrationFragment().getWebView();
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	webView.loadUrl("javascript:  failed('"+ responseModel.getStatusMsg() +"'); ");
		    }
		});
	}
	
	@Override
	public RegistrationResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new RegistrationResponseModel(finalResult);
	}
}
