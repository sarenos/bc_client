package com.example.bambicity.APILayers.chat.history;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class ChatResponseModel extends ResponseModel {
	
	private String id;
	private String user;
	private String read;
	private String message;
	private String date;

	ChatResponseModel(JSONObject jsonObject) throws JSONException
	{
		super(jsonObject);
		this.id = jsonObject.optString("id");
		this.user = jsonObject.optString("user");
		this.read = jsonObject.optString("read");
		this.message = jsonObject.optString("message");
		this.date = jsonObject.optString("date");
	}

	public String getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getRead() {
		return read;
	}

	public String getMessage() {
		return message;
	}

	public String getDate() {
		return date;
	}
}
