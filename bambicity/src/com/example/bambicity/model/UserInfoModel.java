package com.example.bambicity.model;

import java.util.ArrayList;

import android.app.ProgressDialog;
import com.example.bambicity.APILayers.Friends.friends_list.FriendsListResponseModel;
import com.example.bambicity.APILayers.user_info.UserFilterModel;
import com.example.bambicity.APILayers.users.UserResponseModel;
import com.google.android.gms.maps.GoogleMap;

public class UserInfoModel {
	
	private static UserInfoModel userInfoModel = new UserInfoModel();
	private String userAccount;
	private String nickName;
	private String userId;
	private String lat;
	private String lot;
	private String sex;
	private String age;
	private String photo;
	private String new_friends;
	private String new_messages;
	private boolean sendStatus;
	private UserFilterModel userFilterModel;
	private ArrayList <UserResponseModel> usersList;
	private FriendsListResponseModel friendsListResponseModel;
	private boolean isLogin;
	private GoogleMap map;
	private UserResponseModel selectedUser;
	private LastSendLocationData lastSendLocationData;
	private ProgressDialog progressDialog;
	private boolean isShowMyLocation;


	public boolean isSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(boolean sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	private UserInfoModel()
	{
		this.lastSendLocationData = new LastSendLocationData();
	}  
	
	public static UserInfoModel getInstance()
	{
		return userInfoModel;
	}
	
	public String getUserAccount() 
	{
		return userAccount;
	}	

	public void setUserAccount(String userAccount) 
	{
		this.userAccount = userAccount;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public static UserInfoModel getUserInfoModel() {
		return userInfoModel;
	}

	public static void setUserInfoModel(UserInfoModel userInfoModel) {
		UserInfoModel.userInfoModel = userInfoModel;
	}

	public ArrayList<UserResponseModel> getUsersList() {
		return usersList;
	}

	public void setUsersList(ArrayList<UserResponseModel> usersList) {
		this.usersList = usersList;
	}
	
	public UserFilterModel getUserFilterModel() {
		return userFilterModel;
	}

	public void setUserFilterModel(UserFilterModel userFilterModel) {
		this.userFilterModel = userFilterModel;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public FriendsListResponseModel getFriendsListResponseModel() {
		return friendsListResponseModel;
	}

	public void setFriendsListResponseModel(
			FriendsListResponseModel friendsListResponseModel) {
		this.friendsListResponseModel = friendsListResponseModel;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		this.map = map;
	}

	public UserResponseModel getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserResponseModel selectedUser) {
		this.selectedUser = selectedUser;
	}

	public LastSendLocationData getLastSendLocationData() {
		return lastSendLocationData;
	}

	public void setLastSendLocationData(LastSendLocationData lastSendLocationData) {
		this.lastSendLocationData = lastSendLocationData;
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}

	public String getNew_friends() {
		return new_friends;
	}

	public void setNew_friends(String new_friends) {
		this.new_friends = new_friends;
	}

	public String getNew_messages() {
		return new_messages;
	}

	public void setNew_messages(String new_messages) {
		this.new_messages = new_messages;
	}

	public boolean isShowMyLocation() {
		return isShowMyLocation;
	}

	public void setShowMyLocation(boolean isShowMyLocation) {
		this.isShowMyLocation = isShowMyLocation;
	}
}
