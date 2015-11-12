package com.example.bambicity.APILayers.Friends.friends_list;

import com.example.bambicity.view.friends.FriendsFragment;


public class FriendsManagerConfig {
	
	private FriendsFragment friendsFragment;
	
	public FriendsFragment getMyFriendsTab() {
		return friendsFragment;
	}
	public void setMyFriendsTab(FriendsFragment friendsFragment) {
		this.friendsFragment = friendsFragment;
	}
}
