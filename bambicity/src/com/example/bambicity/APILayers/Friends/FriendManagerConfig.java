package com.example.bambicity.APILayers.Friends;

import com.example.bambicity.view.user_account.UserAccountFragment;

public class FriendManagerConfig {
	private String userTo;
	private int friendStatus;
	private UserAccountFragment userAccountFragment;
	
	public int getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}

	public String getUserTo() {
		return userTo;
	}

	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}

	public UserAccountFragment getUserAccountFragment() {
		return userAccountFragment;
	}

	public void setUserAccountFragment(UserAccountFragment userAccountFragment) {
		this.userAccountFragment = userAccountFragment;
	}
}
