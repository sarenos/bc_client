package com.example.bambicity.view.chat;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.bambicity.R;
import com.example.bambicity.APILayers.chat.history.ChatManager;
import com.example.bambicity.APILayers.chat.history.ChatManagerConfig;
import com.example.bambicity.APILayers.chat.history.ChatResponseModel;
import com.example.bambicity.APILayers.chat.send.SendMessageManager;
import com.example.bambicity.APILayers.chat.send.SendMessageManagerConfig;
import com.example.bambicity.workers.ChatWorker;

public class ChatFragment extends Fragment {
	private JSONTokener tokener;
	private Handler mHandler;
	ProgressBar progressBar;
	View view;
	ArrayList <MessageHistoryResponse> friendsListResponse;
	ChatMessageAdapter adapter;
	ListView lv;
	String userId;
	String message;
	private ChatManagerConfig chatManagerConfig;
	private ChatManager chatManager;
	private SendMessageManagerConfig sendMessageManagerConfig;
	private SendMessageManager sendMessageManager;
	ArrayList <ChatResponseModel> list;
	ChatWorker chatWorker;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public ChatFragment(String userId) {
			this.userId = userId;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			  view = inflater.inflate(R.layout.chat_message_fragment, container, false);
		    	
		         lv = (ListView) view.findViewById(R.id.listView1);
		        		        
		    	mHandler = new Handler() {
					@SuppressLint("NewApi")
					@Override
					public void handleMessage(Message msg) {
						switch (msg.what) {
						case 1:
			      			
			      			//	showFriends();
								break;

						case 2:
			      			if(message == null || message.equals(""))
			      				break;
							try {
								friendsListResponse.add(new MessageHistoryResponse(message));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		//	adapter = new ChatMessageAdapter(getActivity(), friendsListResponse);

				    	      lv.setAdapter(adapter);
				    	      
							break;
							
						case 3:
			      			
							getArchive();
							break;
						}
					};
				};
				chatManagerConfig = new ChatManagerConfig();
				chatManager = new ChatManager(chatManagerConfig);
				sendMessageManagerConfig = new SendMessageManagerConfig();
				sendMessageManager = new SendMessageManager(sendMessageManagerConfig);
		        
				getArchive();

				//new Thread(new LoadMyFriends(mHandler)).start();

		        return view;
		}
		
		public void getArchive()
		{			
			chatManagerConfig.setFriendId(userId);
			chatManagerConfig.setChatFragment(this);
			
			chatWorker = new ChatWorker(chatManagerConfig);

		}
		
		public void showMessage(final ArrayList <ChatResponseModel> list)
	    {
			this.list = list;
			if (getActivity() != null) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						
						if(adapter == null){
						adapter = new ChatMessageAdapter(getActivity(), list);
					
			    	      lv.setAdapter(adapter);
			    	      lv.setSelection(list.size() - 1);
			    			Button button = (Button) view.findViewById(R.id.chat_button_send);
			    			button.setOnClickListener(new View.OnClickListener() {
			    			    @Override
								public void onClick(View v) {
			    			    	EditText et =  (EditText) view.findViewById(R.id.chat_edit_message);
			    			     message = et.getText().toString();
			    			    	sendMessage(message);
			    			    	et.setText("");
			    			    }
			    			});
						}
						else{
							if(list.size() != adapter.getCount())
							{
								adapter.addElement(list);
								adapter.notifyDataSetChanged();
							}

						}
					}
				});
			}		
	    }
		
		public void sendMessage(String message)
		{	
			sendMessageManagerConfig.setUserTo(userId);
			sendMessageManagerConfig.setMessage(message);
			sendMessageManagerConfig.setChatFragment(this);
			sendMessageManager.send();			
		}
		
		 @Override
		    public void onDestroy()
		    {
		        // Remove adapter refference from list
		       // lv.setAdapter(null);
		       // super.onDestroy();
		    }
		 
		 
			@Override
			public void onResume() {
				super.onResume();
				chatWorker.start();
			}

			@Override
			public void onPause() {
				super.onPause();
				chatWorker.stop();
			}

			@Override
			public void onStop() {
				super.onStop();
				chatWorker.stop();
			}
		}
