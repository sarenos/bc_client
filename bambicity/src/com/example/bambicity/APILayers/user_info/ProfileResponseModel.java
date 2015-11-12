package com.example.bambicity.APILayers.user_info;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;


public class ProfileResponseModel extends ResponseModel {
	
	private String nickName;
	private String age;
	private String sex;
	private String photo;
	private UserFilterModel userFilterModel;
	
	public ProfileResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject); 
		this.nickName = jsonObject.getJSONObject("data").optString("nick");
		this.age = jsonObject.getJSONObject("data").optString("age");
		this.sex = jsonObject.getJSONObject("data").optString("sex");
		this.photo = jsonObject.getJSONObject("data").optString("photo");
		this.userFilterModel = new UserFilterModel(new JSONObject(jsonObject.getJSONObject("data").optString("filter")));
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public UserFilterModel getUserFilterModel() {
		return userFilterModel;
	}

	public String getPhoto() {
		return photo;
	}
}
