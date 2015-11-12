package com.example.bambicity.APILayers.messages;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class MessagesResponseModel extends ResponseModel {
	
	private String id;
	private String nickName;
	private String age;
	private String sex;
	private String photo;
	private boolean isOnline;

	public MessagesResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject);
		this.id = jsonObject.optString("id");
		this.nickName = jsonObject.optString("nick");
		this.age = jsonObject.optString("age");
		this.sex = jsonObject.optString("sex");
		this.photo = jsonObject.optString("photo");
		this.isOnline = jsonObject.optBoolean("isOnline");
	}

	public String getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public String getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getPhoto() {
		return photo;
	}
	
	public boolean isOnline() {
		return isOnline;
	}

}
