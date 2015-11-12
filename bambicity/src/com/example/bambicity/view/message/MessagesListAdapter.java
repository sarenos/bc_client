package com.example.bambicity.view.message;

import java.util.ArrayList;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.messages.MessagesResponseModel;
import com.example.bambicity.services.ImageLoader;
import com.example.bambicity.view.chat.ChatFragment;
import com.example.bambicity.view.main.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessagesListAdapter extends BaseAdapter implements OnClickListener{
    
    private ArrayList <MessagesResponseModel> dataList;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
	FragmentActivity activity;

     
    public MessagesListAdapter(Activity a, ArrayList <MessagesResponseModel> friendsListResponse) {
        activity = (FragmentActivity) a;
        this.dataList = friendsListResponse;
        inflater = (LayoutInflater)activity.
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }
 
    @Override
	public int getCount() {
        return dataList.size();
    }
 
    @Override
	public Object getItem(int position) {
        return position;
    }
 
    @Override
	public long getItemId(int position) {
        return position;
    }
     
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
          
        public TextView text;
        public TextView age;
        public TextView textWide;
        public TextView onlineStatus;
        public ImageView image;
  
    }
     
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
         
        View vi=convertView;
        ViewHolder holder;
          
        if(convertView==null){
              
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_layout, null);
            /****** View Holder Object to contain tabitem.xml file elements ******/
 
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.listlabel);
            holder.age=(TextView)vi.findViewById(R.id.list_age);
            holder.image=(ImageView)vi.findViewById(R.id.listicon);
            holder.onlineStatus=(TextView)vi.findViewById(R.id.list_online_status);
              
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();
         
         
       holder.text.setText(dataList.get(position).getNickName());
       holder.age.setText(dataList.get(position).getAge());
       holder.onlineStatus.setText(dataList.get(position).isOnline()?"online":"offline");
        ImageView image = holder.image;
         
        //DisplayImage function from ImageLoader Class

       // imageLoader.DisplayImage("http://bambicity.url.ph/bambi_city/" + dataList.get(position).getPhotoUrl(), image, null);
        imageLoader.DisplayImage("http://bambicity.url.ph/new_api/" + dataList.get(position).getPhoto(), image, null);
        /******** Set Item Click Listner for LayoutInflater for each row ***********/
        vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }
 
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
         
    }
     
     
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
         
       OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View arg0) {
        	
//            MainActivity sct = (MainActivity)activity;
//            Log.d("handler","ok");
//            String userId = dataList.get(mPosition).getUserId();
//    		FragmentManager fragmentManager = sct.getSupportFragmentManager();
//    		fragmentManager
//    				.beginTransaction()
//    				.replace(R.id.container,
//    					 new UserAccountFragment(userId)).commit();
           // sct.onItemClick(mPosition);   
        	
          MainActivity sct = (MainActivity)activity;

          sct.getSupportFragmentManager().beginTransaction()
			.replace(R.id.container, new ChatFragment(dataList.get(mPosition).getId()), "ChatFragment")
			.commit();
        }               
    }   
}
