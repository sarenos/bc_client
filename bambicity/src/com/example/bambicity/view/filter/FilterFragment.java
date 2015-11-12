package com.example.bambicity.view.filter;

import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.bambicity.R;

public class FilterFragment extends Fragment {
	private JSONTokener tokener;
	private FilterBridgeJS webFilterAPI;
	WebView web;
	ProgressBar progressBar;
	private static final int REQUEST_FILE_PICKER = 1;
	public  ValueCallback<Uri> mFilePathCallback4;
	public  ValueCallback<Uri[]> mFilePathCallback5;
	String userAccount;
	View rootView;
	
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public FilterFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.filter_fragment, container,
					false);
			progressBar = (ProgressBar) rootView.findViewById(R.id.filter_progress_bar);
			progressBar.setVisibility(View.INVISIBLE);
			init(rootView);
			return rootView;
		}
		
		@SuppressLint("NewApi")
		private void init(final View rootView)
		{	
			web = (WebView) rootView.findViewById(R.id.webView1);
			web.setVerticalScrollBarEnabled(false);        
			web.setHorizontalScrollBarEnabled(false);      
			web.getSettings().setJavaScriptEnabled(true);  
			web.getSettings().setJavaScriptEnabled(true);  

			web.getSettings().setDomStorageEnabled(true);  
			web.getSettings().setSupportZoom(false);       
			web.getSettings().setSupportMultipleWindows(true); 
		                                                        
			web.getSettings().setLoadWithOverviewMode(true);
			web.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
			web.getSettings().setAllowFileAccess(true);
			web.getSettings().setLoadsImagesAutomatically(true);
		    webFilterAPI = new FilterBridgeJS(this);

		    web.addJavascriptInterface(webFilterAPI, "WFFAPI");   
		                                                                 
		    web.loadUrl("file:///android_asset/pages/filter.html");
		    web.setWebChromeClient(new WebChromeClient());
		}
		
		public void showProgressBar()
		{
			rootView.post(new Runnable() {
			    @Override
			    public void run() {
			    	progressBar.setVisibility(View.VISIBLE);
			    }
			});
		}
		
		public void hideProgressBar()
		{
			rootView.post(new Runnable() {
			    @Override
			    public void run() {
			    	progressBar.setVisibility(View.INVISIBLE);
			    }
			});
		}
		
		public WebView getWebView()
		{
			return web;
		}
		
		public View getRootView()
		{
			return rootView;
		}
}
