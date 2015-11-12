package com.example.bambicity.APILayers.my_location;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;

public class MyLocationsManager extends Manager {
	  public static String ACTION_URL = "send_location.php";
	  public static String ACTION = "send_location";
	  public static String METOD = "post";
	  private MyLocationsManagerConfig locationsManagerConfig;  
	  
	  public MyLocationsManager(MyLocationsManagerConfig locationsManagerConfig)
	  {
		  this.locationsManagerConfig = locationsManagerConfig;
	  }

	  @Override
	public void initRequestParams()
	  {		    
			params.put("latitude", UserInfoModel.getInstance().getLat());
			params.put("longitude", UserInfoModel.getInstance().getLot());
			params.put("user_id", UserInfoModel.getInstance().getUserId());
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
			UserInfoModel.getInstance().setNew_friends(((MyLocationResponseModel)responseModel).getNewFriends());
			UserInfoModel.getInstance().setNew_messages(((MyLocationResponseModel)responseModel).getNewMessages());
			UserInfoModel.getInstance().getLastSendLocationData().setLat(params.get("latitude"));
			UserInfoModel.getInstance().getLastSendLocationData().setLot(params.get("longitude"));
			UserInfoModel.getInstance().getLastSendLocationData().setUserId(params.get("user_id"));
			UserInfoModel.getInstance().getLastSendLocationData().setDate(new Date());
		}
		
		@Override
		public void failed()
		{

		}
		
		@Override
		public MyLocationResponseModel getResponseModel(JSONObject finalResult) throws JSONException
		{
			return new MyLocationResponseModel(finalResult);
		}
}