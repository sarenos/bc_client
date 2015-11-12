package com.example.bambicity.APILayers.users;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;


public class UsersListResponseModel extends ResponseModel {
	
	private ArrayList <UserResponseModel> usersList;

	
	public UsersListResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject); 
		usersList = new ArrayList <UserResponseModel>();
		JSONArray jsonArrayData = jsonObject.getJSONArray("data");
		int count = jsonArrayData.length();
		JSONObject jsonFriendObject = null;
		
		for (int i = 0; i < count; i++) 
		{
			jsonFriendObject = jsonArrayData.getJSONObject(i);
				usersList.add(new UserResponseModel(jsonFriendObject));
		}
	}
	
	public ArrayList <UserResponseModel> getUsersList()
	{
		return usersList;
	}
}
