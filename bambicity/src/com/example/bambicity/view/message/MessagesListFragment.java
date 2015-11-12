package com.example.bambicity.view.message;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONTokener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.messages.MessagesManager;
import com.example.bambicity.APILayers.messages.MessagesManagerConfig;
import com.example.bambicity.APILayers.messages.MessagesResponseModel;
import com.example.bambicity.APILayers.users.UsersListResponseModel;

public class MessagesListFragment extends Fragment implements OnItemClickListener{
	private static MessagesListFragment uafragment = new MessagesListFragment();
	private JSONTokener tokener;

	private Handler mHandler;
	View view;
	ArrayList <UsersListResponseModel> friendsListResponse;
	//ArrayAdapter<String> arrayAdapter;
	MessagesListAdapter adapter;
	ListView lv;
	MessagesManagerConfig messagesManagerConfig;
	MessagesManager messagesManager;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static MessagesListFragment newInstance() {
			return uafragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d("testActivity", " MyFriendsTab onCreateView");

			  view = inflater.inflate(R.layout.pageone_fragment, container, false);

		    	
		         lv = (ListView) view.findViewById(R.id.listView1);
		       // arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_layout, R.id.label);
		        ImageView imageView = (ImageView) view.findViewById(R.id.listicon);
		        
			//	new Thread(new DownloadImage(view)).start();

		        
		    	mHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						switch (msg.what) {
						case 1:
			      			Log.d("test", "handler ok");
			      			
			      			//	showFriends();
			      			
							break;
						}
					};
				};
		        
		        
				HashMap<String, String> params = new HashMap<String, String>();
				
				params.put("action", "list_users");				
				
				//new Thread(new HttpRequest(httpRequestConfig)).start();
				
				//new Thread(new LoadMyFriends(mHandler)).start();
				messagesManagerConfig = new MessagesManagerConfig();
				messagesManagerConfig.setMessagesListFragment(this);
				messagesManager = new MessagesManager(messagesManagerConfig);
				messagesManager.send();
		        return view;
		}
		
		public void showFriends(final ArrayList <MessagesResponseModel> list)
		{
			
			if (getActivity() != null) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
		    			adapter = new MessagesListAdapter(getActivity(), list);
		    						        lv.setAdapter(adapter);
					}
				});
			}
			
			
			
//				Log.d("test", "showFriends");
//				progressBar.setVisibility(View.GONE);
//	    	    tokener = new JSONTokener(httpRequestConfig.getResponse());
//	    		JSONObject finalResult;
//	    		try {
//	    			finalResult = new JSONObject(tokener);
//					JSONObject jsonObject = null;
//
//	    			JSONArray jsonArray = finalResult.getJSONArray("data");
//					int count = jsonArray.length();
//	    			Log.d("test", jsonArray.toString());
//			        //arrayList = new <String> ArrayList();
//
//	    			lv.setAdapter(adapter);
//	    			friendsListResponse = new ArrayList <ListResponseModel>();
//					for (int i = 0; i < count; i++) {
//
//						jsonObject = jsonArray.getJSONObject(i);
//						friendsListResponse.add(new MessageListResponse(jsonObject));
//					}
//	    			Log.d("test", friendsListResponse.toString());
//
//
//	    		} catch (JSONException e) {
//	    			// TODO Auto-generated catch block
//	    			e.printStackTrace();
//	    		}
//			        lv.setOnItemClickListener(this);
//
//			        lv.setAdapter(adapter);
    }
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
//			Log.d("testActivity", "new onItemClick");
//
//			// TODO Auto-generated method stub
//			String userId = friendsListResponse.get(position).getUsersLis;
//			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//			fragmentManager
//					.beginTransaction()
//					.replace(R.id.container,
//						 new ChatFragment(userId)).commit();
			Log.d("test","click");
		}
		
		
		 @Override
		    public void onDestroy()
		    {
		        // Remove adapter refference from list
		        lv.setAdapter(null);
		        super.onDestroy();
		    }
		     
		    public OnClickListener listener=new OnClickListener(){
		        @Override
		        public void onClick(View arg0) {
		             
		            //Refresh cache directory downloaded images
		            adapter.imageLoader.clearCache();
		            adapter.notifyDataSetChanged();
		        }
		    };
		}
	