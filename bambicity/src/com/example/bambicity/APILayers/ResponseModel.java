package com.example.bambicity.APILayers;

import org.json.JSONException;
import org.json.JSONObject;


public class ResponseModel {
	
	protected int status;
	protected String statusMsg;

	public ResponseModel(JSONObject jsonObject) throws JSONException
	{
		this.status = jsonObject.optInt("status");
		this.statusMsg = jsonObject.optString("statusMsg");
	}
	
	public int getStatus() {
		return status;
	}

	public String getStatusMsg() {
		return statusMsg;
	}
}
