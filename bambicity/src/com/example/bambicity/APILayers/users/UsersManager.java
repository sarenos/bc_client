package com.example.bambicity.APILayers.users;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;


public class UsersManager extends Manager {
	
	  public static String ACTION_URL = "filters.php";
	  public static String ACTION = "get_users";
	  public static String METOD = "get";
	  private UsersManagerConfig usersManagerConfig;  
	  
	  
		public UsersManager(UsersManagerConfig usersManagerConfig)
		{
			this.usersManagerConfig = usersManagerConfig;
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
			UserInfoModel.getInstance().setUsersList(((UsersListResponseModel)responseModel).getUsersList());
			usersManagerConfig.getMapFragment().getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					UserInfoModel.getInstance().getProgressDialog().dismiss();
				}
			});
			usersManagerConfig.getMapFragment().showUsers();
		}
		
		@Override
		public void failed()
		{
			exitApp();
		}
		
		@Override
		public UsersListResponseModel getResponseModel(JSONObject finalResult) throws JSONException
		{
			return new UsersListResponseModel(finalResult);
		}
}
