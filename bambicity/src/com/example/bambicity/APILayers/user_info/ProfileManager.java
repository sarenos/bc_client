package com.example.bambicity.APILayers.user_info;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import com.example.bambicity.R;
import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.map.MapFragment;
import com.example.bambicity.workers.MyLocationsWorker;


public class ProfileManager extends Manager {
	  public static String ACTION_URL = "user.php";
	  public static String ACTION = "get_info";
	  public static String METOD = "get";
	  private ProfileManagerConfig profileManagerConfig;  
	  private Intent intent;
	  
	  public ProfileManager(ProfileManagerConfig profileManagerConfig)
	  {
		  this.profileManagerConfig = profileManagerConfig;
	  }

	  @Override
	public void initRequestParams()
	  {
		  params.put("user_id", UserInfoModel.getInstance().getUserId());
		  params.put("action", ACTION);
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
			UserInfoModel.getInstance().setNickName(((ProfileResponseModel) responseModel).getNickName());
			UserInfoModel.getInstance().setAge(((ProfileResponseModel) responseModel).getAge());
			UserInfoModel.getInstance().setSex(((ProfileResponseModel) responseModel).getSex());
			UserInfoModel.getInstance().setUserFilterModel(((ProfileResponseModel) responseModel).getUserFilterModel());
			UserInfoModel.getInstance().setPhoto(((ProfileResponseModel) responseModel).getPhoto());
			
			UserInfoModel.getInstance().setLogin(true);
			intent = new Intent(profileManagerConfig.getMainActivity(), MyLocationsWorker.class);
			profileManagerConfig.getMainActivity().startService(intent);
			profileManagerConfig.getMainActivity().getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, MapFragment.newInstance(), "MapFragment")
			.commit();
		}
		
		@Override
		public void failed()
		{
			exitApp();
		}
		
		@Override
		public ProfileResponseModel getResponseModel(JSONObject finalResult) throws JSONException
		{
			return new ProfileResponseModel(finalResult);
		}
}
