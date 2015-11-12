package com.example.bambicity.APILayers.Registration;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class RegistrationResponseModel extends ResponseModel {
	protected String userId;

	RegistrationResponseModel(JSONObject jsonObject) throws JSONException
	{
		super(jsonObject);
		this.userId  = jsonObject.optString("user_id");
	}
	
	public String getUserId() {
		return userId;
	}
}
