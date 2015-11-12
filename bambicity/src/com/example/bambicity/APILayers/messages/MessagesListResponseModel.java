package com.example.bambicity.APILayers.messages;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class MessagesListResponseModel extends ResponseModel {
	
	private ArrayList <MessagesResponseModel> messageList;

	
	public MessagesListResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject); 
		messageList = new ArrayList <MessagesResponseModel>();
		JSONArray jsonArrayData = jsonObject.getJSONArray("data");
		int count = jsonArrayData.length();
		JSONObject jsonFriendObject = null;
		
		for (int i = 0; i < count; i++) 
		{
			jsonFriendObject = jsonArrayData.getJSONObject(i);
			messageList.add(new MessagesResponseModel(jsonFriendObject));
		}
	}
	
	public ArrayList <MessagesResponseModel> getUsersList()
	{
		return messageList;
	}
}
