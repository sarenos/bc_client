package com.example.bambicity.view.friends;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONTokener;

import android.app.Activity;
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
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.users.UsersListResponseModel;
import com.example.bambicity.model.UserInfoModel;

public class MyAppFriendsTab extends Fragment implements OnItemClickListener 
{
	private JSONTokener tokener;

	private Handler mHandler;
	View view;
	ArrayList <UsersListResponseModel> friendsListResponse;
	//ArrayAdapter<String> arrayAdapter;
	FriendsListAdapter adapter;
	ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		Log.d("testActivity", "new onCreate");

    }
    
   public MyAppFriendsTab()
    {
		Log.d("testActivity", "MyFriendsTab ndfgdgdfg");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		Log.d("testActivity", " MyFriendsTab onCreateView");

     view = inflater.inflate(R.layout.friends_tab_fragment, container, false);
    	
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
	      			
	      		//		showFriends();
	      			
					break;
				}
			};
		};
        
        
		//new Thread(new LoadMyFriends(mHandler)).start();
		
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("action", "get_list");
		
		//new Thread(new HttpRequest(httpRequestConfig)).start();
	    adapter = new FriendsListAdapter(getActivity(), UserInfoModel.getInstance().getFriendsListResponseModel().getAppTofriendsList());
	    lv.setOnItemClickListener(this);
	    lv.setAdapter(adapter);
        return view;
    }
    
    private ProgressBar findViewById(int progressbar1) {
		// TODO Auto-generated method stub
		return null;
	}

	private void showFriends()
    {

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
		Log.d("testActivity", "new onActivityCreated");

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
		Log.d("testActivity", "new onAttach");

    }

    @Override
    public void onStart()
    {
        super.onStart();
		Log.d("testActivity", "new onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();

		Log.d("testActivity", "new onResume");
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d("testActivity", "new onItemClick");

//		// TODO Auto-generated method stub
//		String userId = friendsListResponse.get(position).getId();
//		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//		fragmentManager
//				.beginTransaction()
//				.replace(R.id.container,
//					 new UserAccountFragment(userId)).commit();
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

