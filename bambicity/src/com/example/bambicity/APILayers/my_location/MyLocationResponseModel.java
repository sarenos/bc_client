package com.example.bambicity.APILayers.my_location;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class MyLocationResponseModel extends ResponseModel {
	
	private String newFriends;
	private String newMessages;

	public MyLocationResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		this.newFriends = ((JSONObject) jsonObject.getJSONArray("data").get(0)).optString("new_friends");
		this.newMessages = ((JSONObject) jsonObject.getJSONArray("data").get(0)).optString("new_messages");
	}

	public String getNewFriends() {
		return newFriends;
	}

	public void setNewFriends(String newFriends) {
		this.newFriends = newFriends;
	}

	public String getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(String newMessages) {
		this.newMessages = newMessages;
	}

}
