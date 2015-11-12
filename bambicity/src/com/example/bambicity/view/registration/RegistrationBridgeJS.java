package com.example.bambicity.view.registration;

import com.example.bambicity.APILayers.Registration.RegistrationManager;
import com.example.bambicity.APILayers.Registration.RegistrationManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import android.webkit.JavascriptInterface;

public class RegistrationBridgeJS {
	
	private RegistrationManagerConfig registrationManagerConfig;
	private RegistrationManager registrationManager;
	private RegistrationFragment registrationFragment;

	RegistrationBridgeJS(RegistrationFragment registrationFragment) {	
		this.registrationFragment = registrationFragment;
		this.registrationManagerConfig = new RegistrationManagerConfig();
		this.registrationManagerConfig.setRegistrationFragment(registrationFragment);
		this.registrationManager = new RegistrationManager(registrationManagerConfig);
    }

	@JavascriptInterface
    public void setNickName(String nickName) {
		registrationManagerConfig.setNickName(nickName);
    }
    
    @JavascriptInterface
    public void setBirthdayDate(String age) {
    	registrationManagerConfig.setAge(age);
    }
    
    @JavascriptInterface
    public String getUserAccount()
    {
    	return UserInfoModel.getInstance().getUserAccount();
    }
    
    @JavascriptInterface
    public void setSex(String sex) {
    	registrationManagerConfig.setSex(sex);
    }
	
    @JavascriptInterface
    public void createUser()
    {
	    registrationFragment.getRootView().post(new Runnable() {
			@Override
			public void run() {
			    UserInfoModel.getInstance().getProgressDialog().show();			
			}
		});
    	registrationManager.send();
    }
    
    @JavascriptInterface
    public void cancel()
    {
    	android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
