package com.example.bambicity.APILayers.Friends.friends_list;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;
import com.example.bambicity.APILayers.users.UserResponseModel;

public class FriendsListResponseModel extends ResponseModel {	
	private ArrayList <UserResponseModel> friendsList;
	private ArrayList <UserResponseModel> appTofriendsList;


	FriendsListResponseModel(JSONObject jsonObject) throws JSONException
	{
		super(jsonObject);
		
		friendsList = new ArrayList <UserResponseModel>();
		appTofriendsList = new ArrayList <UserResponseModel>();

		JSONArray jsonArrayData = jsonObject.getJSONArray("data");
		int count = jsonArrayData.length();
		JSONObject jsonFriendObject = null;
		
		for (int i = 0; i < count; i++) 
		{
			jsonFriendObject = jsonArrayData.getJSONObject(i);
			if(jsonArrayData.getJSONObject(i).optInt("friend") == 1)
			{
				friendsList.add(new UserResponseModel(jsonFriendObject));
			}
			else
			{
				appTofriendsList.add(new UserResponseModel(jsonFriendObject));
			}
		}
	}
	
	public ArrayList <UserResponseModel> getFriendsList() 
	{
		return friendsList;
	}
	
	public ArrayList <UserResponseModel> getAppTofriendsList() 
	{
		return appTofriendsList;
	}
}
