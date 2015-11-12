package com.example.bambicity.APILayers.Authorization;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.APILayers.user_info.ProfileManager;
import com.example.bambicity.APILayers.user_info.ProfileManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.registration.RegistrationFragment;

public class AuthorizationManager extends Manager {
	private AuthorizationManagerConfig authorizationManagetConfig;
    public String ACTION_URL = "authorization.php";
    public String METOD = "get";
	private ProfileManagerConfig profileManagerConfig;
	private ProfileManager profileManager;


	public AuthorizationManager(AuthorizationManagerConfig authorizationManagetConfig)
	{
		this.authorizationManagetConfig = authorizationManagetConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user_account", UserInfoModel.getInstance().getUserAccount());
		params.put("action", "authorization");
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{
		UserInfoModel.getInstance().setUserId(((AuthorizationResponseModel) responseModel).getUserId());
		this.profileManagerConfig = new ProfileManagerConfig();
		profileManagerConfig.setProgressDialog(authorizationManagetConfig.getProgressDialog());
		this.profileManagerConfig.setMainActivity(authorizationManagetConfig.getMainActivity());
		this.profileManager = new ProfileManager(profileManagerConfig);
		profileManager.send();
	}
	
	@Override
	public void failed()
	{
		authorizationManagetConfig.getMainActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container,
						new RegistrationFragment(), "Registration").commit();
		UserInfoModel.getInstance().getProgressDialog().hide();
	}
	
	@Override
	public AuthorizationResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new AuthorizationResponseModel(finalResult);
	}
}
