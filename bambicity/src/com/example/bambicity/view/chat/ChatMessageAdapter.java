package com.example.bambicity.view.chat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.chat.history.ChatResponseModel;
import com.example.bambicity.services.ImageLoader;

public class ChatMessageAdapter extends BaseAdapter{
    
    private ArrayList <ChatResponseModel> dataList;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
	FragmentActivity activity;

     
    public ChatMessageAdapter(Activity a, ArrayList <ChatResponseModel> friendsListResponse) {
        activity = (FragmentActivity) a;
        this.dataList = friendsListResponse;
        inflater = (LayoutInflater)activity.
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
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
        public EditText editMessage;
        public TextView textWide;
        public ImageView image;
  
    }
    
    public void addElement(ArrayList <ChatResponseModel> friendsListResponse)
    {
    	this.dataList = friendsListResponse;
    }
     
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
         
        View vi=convertView;
        ViewHolder holder;
          
        if(convertView==null){
              
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.chat_message_layout, null);
            /****** View Holder Object to contain tabitem.xml file elements ******/
 
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.chat_message);

            if(dataList.get(position).getUser().equals("true")){
            	holder.text.setGravity(Gravity.LEFT);
            } else {
            	holder.text.setGravity(Gravity.RIGHT);
            }
            //holder.text1=(TextView)vi.findViewById(R.id.text1);
            //holder.image=(ImageView)vi.findViewById(R.id.listicon);
              
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();
         
         
       holder.text.setText(dataList.get(position).getMessage());
       // holder.text1.setText("company description "+position);
         
        //DisplayImage function from ImageLoader Class

         
        /******** Set Item Click Listner for LayoutInflater for each row ***********/
       // vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }
}
