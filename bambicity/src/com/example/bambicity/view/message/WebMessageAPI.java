package com.example.bambicity.view.message;

import java.util.HashMap;

import org.json.JSONTokener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.bambicity.R;

public class WebMessageAPI {
	private JSONTokener tokener;
	private String userId;
	private ProgressBar progressBar;
    private Context mContext;
	private Handler mHandler;
	WebView wv;



	WebMessageAPI(String userId ,WebView wv, View rootView)
	{
		this.userId = userId;
    	this.wv = wv;

        mContext = rootView.getContext();
    	progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar4);

    	mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
	      			Log.d("test", "handler ok");
	      			
	      			parseMessageHistory();

					break;
				case 2:
	      			Log.d("test", "send message ok");

	      			parseMessageSender();
					break;
				}
			};
		};
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("action", "one_friend");
		params.put("friend", userId);

	    
		
		//new Thread(new HttpRequest(httpRequestConfig)).start();
	}
	
	
	  private void parseMessageHistory()
	    {

			wv.loadUrl("javascript: showHistory();");
/*
	    	 tokener = new JSONTokener(httpRequestConfig.getResponse());
	 		JSONObject finalResult;
	 		try {
	 			finalResult = new JSONObject(tokener);
	 			Log.d("test", finalResult.toString());

	 			JSONObject jsonObject = finalResult.getJSONArray("data").getJSONObject(0);
	 					//this.nickName = jsonObject.getString("user_account");
		  				//wv.loadUrl("javascript: location.reload();");

	 		    	    progressBar.setVisibility(View.INVISIBLE);
	 		    			
	 		} catch (JSONException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 		*/
 			progressBar.setVisibility(View.INVISIBLE);
	    }
	  
	  private void parseMessageSender()
	  {
			wv.loadUrl("javascript: sendOk();");
	  }
	  
//	    @JavascriptInterface
//	    public String getJsonMessagesHistory() {
//	    	return httpRequestConfig.getResponse();
//	    }
	    
	    @JavascriptInterface
	    public void sendMessage(String message)
	    {
	    	
	    	
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("user_to", userId);
			params.put("message", message);
			
		//	new Thread(new HttpRequest(httpRequestConfig)).start();	    	
	    }
}
