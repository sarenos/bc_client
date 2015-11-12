package com.example.bambicity.APILayers.user_info;

import android.app.ProgressDialog;

import com.example.bambicity.APILayers.Registration.RegistrationManagerConfig;
import com.example.bambicity.view.main.MainActivity;

public class ProfileManagerConfig extends RegistrationManagerConfig {
	private MainActivity profileFragment;
	private ProgressDialog progressDialog;

	
	public MainActivity getMainActivity() {
		return profileFragment;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.profileFragment = mainActivity;
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}
}
