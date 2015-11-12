package com.example.bambicity.APILayers.chat.send;

import com.example.bambicity.view.chat.ChatFragment;

public class SendMessageManagerConfig {
	private String userTo;
	private String message;
	
	private ChatFragment chatFragment;

	public ChatFragment getChatFragment() {
		return chatFragment;
	}

	public void setChatFragment(ChatFragment chatFragment) {
		this.chatFragment = chatFragment;
	}

	public String getUserTo() {
		return userTo;
	}

	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
