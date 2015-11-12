package com.example.bambicity.view.users;

import java.util.ArrayList;
import org.json.JSONTokener;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.users.UserResponseModel;
import com.example.bambicity.APILayers.users.UsersListResponseModel;
import com.example.bambicity.APILayers.users.UsersManagerConfig;
import com.example.bambicity.model.UserInfoModel;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UsersListFragment extends Fragment implements OnItemClickListener{
	private static UsersListFragment uafragment = new UsersListFragment();
	private JSONTokener tokener;

	private Handler mHandler;
	public View view;
	ArrayList <UsersListResponseModel> friendsListResponse;
	ArrayAdapter<String> arrayAdapter;
	public UsersListAdapter adapter;
	public ListView lv;
	UsersManagerConfig usersManagerConfig;
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			  view = inflater.inflate(R.layout.user_list_fragment, container, false);


		        lv = (ListView) view.findViewById(R.id.listView);
		        ImageView imageView = (ImageView) view.findViewById(R.id.listicon);
		        
				adapter = new UsersListAdapter(getActivity(), UserInfoModel.getInstance().getUsersList());
				lv.setOnItemClickListener(this);
				lv.setAdapter(adapter);	
		        return view;
		}
		
		 @Override
		    public void onDestroy()
		    {
		        lv.setAdapter(null);
		        super.onDestroy();
		    }
		     
		    public OnClickListener listener=new OnClickListener(){
		        @Override
		        public void onClick(View arg0) {
		        	adapter.imageLoader.clearCache();
		        	adapter.notifyDataSetChanged();
		        }
		    };
		public void show(final ArrayList <UserResponseModel> usersList)
		{
			adapter = new UsersListAdapter(getActivity(), usersList);
			lv.setOnItemClickListener(this);
			lv.setAdapter(adapter);	
		}    
		
		public void showProgressBar()
		{
			view.post(new Runnable() {
			    @Override
			    public void run() {
			    	//progressBar.setVisibility(View.VISIBLE);
			    }
			});
		}
		
		public void hideProgressBar()
		{
			view.post(new Runnable() {
			    @Override
			    public void run() {
			    //	progressBar.setVisibility(View.INVISIBLE);
			    }
			});
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		}
}
