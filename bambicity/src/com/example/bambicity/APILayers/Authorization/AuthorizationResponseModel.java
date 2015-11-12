package com.example.bambicity.APILayers.Authorization;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class AuthorizationResponseModel extends ResponseModel {
	
	protected String userId;

	AuthorizationResponseModel(JSONObject jsonObject) throws JSONException
	{
		super(jsonObject);
		this.userId  = jsonObject.optString("user_id");
	}
	
	public String getUserId() {
		return userId;
	}
}
