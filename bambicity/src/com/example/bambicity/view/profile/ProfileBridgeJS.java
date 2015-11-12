package com.example.bambicity.view.profile;

import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.bambicity.APILayers.user_info.ProfileManager;
import com.example.bambicity.APILayers.user_info.ProfileManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.photo.PhotoIntentActivity;


public class ProfileBridgeJS 
{
	private ProfileManagerConfig profileManagerConfig;
	private ProfileManager profileManager;
	private ProfileFragment profileFragment;
	
	ProfileBridgeJS(ProfileFragment profileFragment) {
		this.profileFragment = profileFragment;
    }
    
    @JavascriptInterface
    public String getPhoto() {
    	if(UserInfoModel.getInstance().getPhoto().equals(""))
    	{
        	return "../image/no_photo.jpg";
    	}
    	return "http://bambicity.url.ph/new_api/" + UserInfoModel.getInstance().getPhoto();
    }
	
    @JavascriptInterface
    public String getNickName() {
    	return UserInfoModel.getInstance().getNickName();
    }
    
    @JavascriptInterface
    public String getSex() {
    	return UserInfoModel.getInstance().getSex();
    }
    
    @JavascriptInterface
    public String getAge() {
    	return UserInfoModel.getInstance().getAge();
    }
    
    @JavascriptInterface
    public void changePhoto() {
		   Intent intent = new Intent(profileFragment.getActivity(), PhotoIntentActivity.class);
		   profileFragment.getActivity().startActivity(intent);
    }
    
    
    /*
    @JavascriptInterface
    public void setNickName(String nickName) {
    	profileManagerConfig.setNickName(nickName);
    }
    
    
    @JavascriptInterface
    public void setAge(String age) {
    	profileManagerConfig.setAge(age);
    }
    
    @JavascriptInterface
    public String getSex() {
    	return "33";
    }
    
    @JavascriptInterface
    public void setSex(String sex) {
    	profileManagerConfig.setSex(sex);
    }
    
    @JavascriptInterface
    public void updateInfo()
    {
		//new Thread(new UpdateUserInfo(this)).start();
    }
    */
}
