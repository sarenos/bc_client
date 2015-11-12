package com.example.bambicity.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.Marker;

public class RequestUsersJsonMaper {
	
	private String user_account;
	private Double latitude;
	private Double longitude;
	private String date_crt;
	private int id;
	private Marker marker;
	private String name;
	private String sex;
	private String age;
	private String photo;

	
	public RequestUsersJsonMaper(JSONObject jsonObject) throws JSONException
	{
		user_account = jsonObject.getString("user_account");
		latitude = jsonObject.getDouble("latitude");
		longitude = jsonObject.getDouble("longitude");
		date_crt = jsonObject.getString("date_crt");
		id = jsonObject.getInt("user_id");
		name = jsonObject.getString("name");
		sex = jsonObject.getString("sex");
		age = jsonObject.getString("age");
		photo = jsonObject.getString("photo");
	}
	
	public String getName() {
		return name;
	}
	
	public String getAge() {
		return age;
	}
	
	public String getSex() {
		return sex;
	}
	
	public String getUser_account() {
		return user_account;
	}

	public Double getLatitude() {
		return latitude;
	}
	

	public Double getLongitude() {
		return longitude;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDateCreate() {
		return date_crt;
	}
	
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public String getPhoto() {
		return photo;
	}
}
