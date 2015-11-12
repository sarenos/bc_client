package com.example.bambicity.APILayers.chat.history;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.ResponseModel;

public class CathListResponseModel extends ResponseModel {
	
	private ArrayList <ChatResponseModel> messageList;

	
	public CathListResponseModel(JSONObject jsonObject) throws JSONException {
		super(jsonObject); 
		messageList = new ArrayList <ChatResponseModel>();
		JSONArray jsonArrayData = jsonObject.getJSONArray("data");
		int count = jsonArrayData.length();
		JSONObject jsonFriendObject = null;
		
		for (int i = 0; i < count; i++) 
		{
			jsonFriendObject = jsonArrayData.getJSONObject(i);
			messageList.add(new ChatResponseModel(jsonFriendObject));
		}
	}
	
	public ArrayList <ChatResponseModel> getUsersList()
	{
		return messageList;
	}
}
