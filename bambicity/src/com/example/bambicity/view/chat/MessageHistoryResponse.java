package com.example.bambicity.view.chat;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class MessageHistoryResponse {

	private String id;
	private String user;
	private String read;
	private String message;
	private String date;
	
	public MessageHistoryResponse (JSONObject jsonObject) throws JSONException
	{
		this.id = jsonObject.getString("id");
		this.user = jsonObject.getString("user");
		this.read = jsonObject.getString("read");
		this.message = jsonObject.getString("message");
		this.date = jsonObject.getString("date");

		Log.d("test", "ok");
	}
	
	public MessageHistoryResponse (String message) throws JSONException
	{
		this.user = "true";
		this.message = message;

		Log.d("test", "ok");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
