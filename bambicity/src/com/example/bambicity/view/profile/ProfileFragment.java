package com.example.bambicity.view.profile;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.bambicity.R;



public class ProfileFragment extends Fragment {
	//private static MyAccountFragment uafragment = new MyAccountFragment();

	private static final int REQUEST_FILE_PICKER = 1;
	public  ValueCallback<Uri> mFilePathCallback4;
	public  ValueCallback<Uri[]> mFilePathCallback5;
	
    private static final String JS_API_NAME = "WPAPI";
    private static final String FILE_PAGE_PATH = "file:///android_asset/pages/";
    private static final String FILE_PAGE_NAME = "profile.html";
	private ProfileBridgeJS profileBridgeJS;
	private WebView webView;
	private View rootView;
	private ProgressBar progressBar;

		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.profile_fragment, container, false);
			progressBar = (ProgressBar) rootView.findViewById(R.id.profile_progress_bar);
			progressBar.setVisibility(View.INVISIBLE);
			init();
			return rootView;
		}
		
	    @Override
		public void onResume() {
	      super.onResume();
	      Log.d("test", "resume");
	      webView.reload();
	      }
	    
	    @Override
	    public void onStart() {
	      super.onStart();
	      Log.d("test", "start");
	      webView.clearCache(true);
	    }
		
		private void init()
		{	
			profileBridgeJS = new ProfileBridgeJS(this);
			webView = (WebView) rootView.findViewById(R.id.profileWebView);
			webView.setVerticalScrollBarEnabled(false);        
			webView.setHorizontalScrollBarEnabled(false);      
			webView.getSettings().setJavaScriptEnabled(true);  
			webView.getSettings().setDomStorageEnabled(true);  
			webView.getSettings().setSupportZoom(false);       
			webView.getSettings().setSupportMultipleWindows(true); 
			webView.getSettings().setLoadWithOverviewMode(true);
			webView.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK);
			webView.getSettings().setAllowFileAccess(true);
			webView.getSettings().setLoadsImagesAutomatically(true);
		    webView.addJavascriptInterface(profileBridgeJS, JS_API_NAME);   		                                                                 
		    webView.loadUrl(FILE_PAGE_PATH + FILE_PAGE_NAME);
		    
		    webView.setWebChromeClient(new WebChromeClient()
		    {
		        public void openFileChooser(ValueCallback<Uri> filePathCallback)
		        {
		        	mFilePathCallback4 = filePathCallback;
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            intent.addCategory(Intent.CATEGORY_OPENABLE);
		            intent.setType("*/*");
		            startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
		        }

		        public void openFileChooser(ValueCallback filePathCallback, String acceptType)
		        {
		        	mFilePathCallback4 = filePathCallback;
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            intent.addCategory(Intent.CATEGORY_OPENABLE);
		            intent.setType("*/*");
		            startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
		        }

		        public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture)
		        {
		        	mFilePathCallback4 = filePathCallback;
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            intent.addCategory(Intent.CATEGORY_OPENABLE);
		            intent.setType("*/*");
		            startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
		        }

		        @Override
		        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
		        {
		        	mFilePathCallback5 = filePathCallback;
		            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		            intent.addCategory(Intent.CATEGORY_OPENABLE);
		            intent.setType("*/*");
		            startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
		            return true;
		        }
		    });
		}
		
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent intent)
		{
		    if(requestCode==REQUEST_FILE_PICKER)
		    {
		        if(mFilePathCallback4!=null)
		        {
		            Uri result = intent==null || resultCode!=Activity.RESULT_OK ? null : intent.getData();
		            if(result!=null)
		            {
		                String path = MediaUtility.getPath(getActivity(), result);
		                Uri uri = Uri.fromFile(new File(path));
		                mFilePathCallback4.onReceiveValue(uri);
		            }
		            else
		            {
		                mFilePathCallback4.onReceiveValue(null);
		            }
		        }
		        if(mFilePathCallback5!=null)
		        {
		            Uri result = intent==null || resultCode!=Activity.RESULT_OK ? null : intent.getData();
		            if(result!=null)
		            {
		                String path = MediaUtility.getPath(getActivity(), result);
		                Uri uri = Uri.fromFile(new File(path));
		            	//profileBridgeJS.setPhotoUrl(path);
		                mFilePathCallback5.onReceiveValue(new Uri[]{ MediaStore.Images.Media.EXTERNAL_CONTENT_URI });
		                
		            }
		            else
		            {
		                mFilePathCallback5.onReceiveValue(null);
		            }
		        }

		        mFilePathCallback4 = null;
		        mFilePathCallback5 = null;
		    }
		}
		
		
		public WebView getWebView()
		{
			return webView;
		}
		
		public View getRootView()
		{
			return rootView;
		}
		
		public ProgressBar getProgressBar()
		{
			return progressBar;
		}
	}
