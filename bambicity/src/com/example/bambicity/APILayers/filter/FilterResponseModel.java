package com.example.bambicity.APILayers.filter;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class FilterResponseModel extends ResponseModel {
	
	protected String userId;

	FilterResponseModel(JSONObject jsonObject) throws JSONException
	{
		super(jsonObject);
		this.userId  = jsonObject.optString("user_id");
	}
	
	public String getUserId() {
		return userId;
	}
}
