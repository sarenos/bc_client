package com.example.bambicity.APILayers.messages;

import com.example.bambicity.view.message.MessagesListFragment;

public class MessagesManagerConfig {
	
	private MessagesListFragment messagesListFragment;

	public MessagesListFragment getMessagesListFragment() {
		return messagesListFragment;
	}

	public void setMessagesListFragment(MessagesListFragment messagesListFragment) {
		this.messagesListFragment = messagesListFragment;
	}
}
