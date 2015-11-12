//package com.example.bambicity.APILayers.Friends;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class FriendResponseModel {
//	
//	protected String userId;
//	private String nickName;
//	private String age;
//	private String sex;
//	private String photo;
//	
//	public FriendResponseModel(JSONObject jsonObject) throws JSONException {
//		this.userId = jsonObject.optString("user_id");
//		this.nickName = jsonObject.optString("nick");
//		this.age = jsonObject.optString("age");
//		this.sex = jsonObject.optString("sex");	
//		this.photo = jsonObject.optString("photo");		
//	}
//
//	public String getId() {
//		return sex;
//	}
//	
//	public String getNickName() {
//		return nickName;
//	}
//	
//	public String getAge() {
//		return age;
//	}
//	
//	public String getSex() {
//		return sex;
//	}
//
//	public String getPhoto() {
//		return photo;
//	}
//
//}
