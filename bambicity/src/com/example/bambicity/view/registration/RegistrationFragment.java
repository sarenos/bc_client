package com.example.bambicity.view.registration;

import com.example.bambicity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class RegistrationFragment extends Fragment {
    private static final String JS_API_NAME = "WRAPI";
    private static final String FILE_PAGE_PATH = "file:///android_asset/pages/";
    private static final String FILE_PAGE_NAME = "registration.html";
	private RegistrationBridgeJS registrationBridgeJs;
	private WebView webView;
	private View rootView;
	private ProgressBar progressBar;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.registration_fragment, container, false);
			progressBar = (ProgressBar) rootView.findViewById(R.id.registration_progress_bar);
			progressBar.setVisibility(View.INVISIBLE);
			init();
			return rootView;
		}
		
	private void init()
		{
		    registrationBridgeJs = new RegistrationBridgeJS(this);
			webView = (WebView) rootView.findViewById(R.id.registration_web_view);
			webView.setVerticalScrollBarEnabled(false);        
			webView.setHorizontalScrollBarEnabled(false);      
			webView.getSettings().setJavaScriptEnabled(true);  
			webView.getSettings().setDomStorageEnabled(true);  
			webView.getSettings().setSupportZoom(false);       
			webView.getSettings().setSupportMultipleWindows(true); 
			webView.getSettings().setLoadWithOverviewMode(true);
			webView.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
			webView.getSettings().setAllowFileAccess(true);
			webView.getSettings().setLoadsImagesAutomatically(true);
		    webView.addJavascriptInterface(registrationBridgeJs, JS_API_NAME);   		                                                                 
		    webView.loadUrl(FILE_PAGE_PATH + FILE_PAGE_NAME);
		    webView.setWebChromeClient(new WebChromeClient());
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
	
    public void showProgressBar()
    {
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	progressBar.setVisibility(View.VISIBLE);
		    }
		});	
    }
    
    public void hideProgressBar()
    {
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	progressBar.setVisibility(View.INVISIBLE);
		    }
		});
    }
}
