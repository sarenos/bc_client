package com.example.bambicity.APILayers.Authorization;

import android.app.ProgressDialog;

import com.example.bambicity.view.main.MainActivity;

public class AuthorizationManagerConfig {

	private MainActivity mainActivity;
	private ProgressDialog progressDialog;
	
	public MainActivity getMainActivity() {
		return mainActivity;
	}
	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}
	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}
}
