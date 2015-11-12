package com.example.bambicity.APILayers.Friends.friends_list;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;

public class FriendsListManager extends Manager {
	private FriendsManagerConfig friendsManagerConfig;
    public String ACTION_URL = "friends.php";
    public String METOD = "get";

	public FriendsListManager(FriendsManagerConfig friendsManagerConfig)
	{
		this.friendsManagerConfig = friendsManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user", UserInfoModel.getInstance().getUserId());
		params.put("action", "get_list");
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
		UserInfoModel.getInstance().setFriendsListResponseModel(((FriendsListResponseModel) responseModel));

		friendsManagerConfig.getMyFriendsTab().view.post(new Runnable() {
		    @Override
		    public void run() {
				friendsManagerConfig.getMyFriendsTab().showTabs();
		    }
		});
		
		
	}
	
	@Override
	public void failed()
	{

	}
	
	@Override
	public FriendsListResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new FriendsListResponseModel(finalResult);
	}
}
