package com.example.bambicity.APILayers.chat.history;

import com.example.bambicity.view.chat.ChatFragment;

public class ChatManagerConfig {
	private String friendId;
	private ChatFragment chatFragment;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public ChatFragment getChatFragment() {
		return chatFragment;
	}

	public void setChatFragment(ChatFragment chatFragment) {
		this.chatFragment = chatFragment;
	}
}
