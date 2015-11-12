package com.example.bambicity.APILayers.users;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;

public class UserResponseModel implements ClusterItem {

	protected String userId;
	private String nickName;
	private String age;
	private String sex;
	private String lat;
	private String lng;
	private String photo;
	private int friendStatus;
	private Marker marker;
	private boolean isOnline;

	
	public UserResponseModel(JSONObject jsonObject) throws JSONException
	{
		this.userId = jsonObject.optString("user_id");
		this.nickName = jsonObject.optString("nick");
		this.age = jsonObject.optString("age");
		this.sex = jsonObject.optString("sex");	
		this.lat = jsonObject.optString("lat");	
		this.lng = jsonObject.optString("lng");	
		this.photo = jsonObject.optString("photo");	
		this.friendStatus = jsonObject.optInt("friend");
		this.isOnline = jsonObject.optBoolean("isOnline");
	}
	
	public String getUserId() {
		return userId;
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

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}
	
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public void getFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}
	
	public int getFriendStatus() {
		return friendStatus;
	}

	public String getPhoto() {
		return photo;
	}

	public void setFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}
	
	public boolean isOnline() {
		return isOnline;
	}

	@Override
	public LatLng getPosition() {
		// TODO Auto-generated method stub
		return new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
	}
}
