package com.example.bambicity.view.message;

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
import com.example.bambicity.R;

public class MessagesFragment extends Fragment {
	private JSONTokener tokener;
	private WebMessageAPI webMessageAPI;
	WebView web;
	private static final int REQUEST_FILE_PICKER = 1;
	public  ValueCallback<Uri> mFilePathCallback4;
	public  ValueCallback<Uri[]> mFilePathCallback5;
	String userId;
	
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public MessagesFragment(String userId) {
			this.userId = userId;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.message_fragment, container,
					false);
			init(rootView);
			return rootView;
		}
		
		@SuppressLint("NewApi")
		private void init(final View rootView)
		{	
			WebView wv;
		    wv = (WebView) rootView.findViewById(R.id.webView1);
		    wv.setVerticalScrollBarEnabled(false);        // ��������� ���������
		    wv.setHorizontalScrollBarEnabled(false);      // ��������� ���������
		    wv.getSettings().setJavaScriptEnabled(true);  // �������� JavaScript
		    wv.getSettings().setJavaScriptEnabled(true);  // �������� JavaScript

		    wv.getSettings().setDomStorageEnabled(true);  // �������� localStorage � �.�.
		    wv.getSettings().setSupportZoom(false);       // ��������� ���, �.�. ���������� ���������� �������� ������������ �� ��������
		    wv.getSettings().setSupportMultipleWindows(true);   // ��������� ��������� �������.  
		                                                         // �.�. ������������ ������ ������ � SPA ����������
		    
		    // Other webview options
		    wv.getSettings().setLoadWithOverviewMode(true);
		    wv.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
		    wv.getSettings().setAllowFileAccess(true);
		    wv.getSettings().setLoadsImagesAutomatically(true);
		    webMessageAPI = new WebMessageAPI(userId, wv, rootView);

		    wv.addJavascriptInterface(webMessageAPI, "WMAPI");   // ����������� ������ � JavaScript. 
		                                                                   // ��� ����� ��� ���� � ��� Java. � JavaScript`� ��������� ������ API
		    wv.loadUrl("file:///android_asset/pages/messages.html");
		    // ��������� ���� ���������
		    wv.setWebChromeClient(new WebChromeClient());
		}}
