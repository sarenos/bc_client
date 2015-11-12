package com.example.bambicity.APILayers.filter;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.webkit.WebView;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;

public class UpdateFilterManager extends Manager {
	private FilterManagerConfig filterManagerConfig;
    public String ACTION_URL = "filters.php";
    public String METOD = "post";
    public String ACTION = "set_filter";
	private Intent intent;


	public UpdateFilterManager(FilterManagerConfig filterManagerConfig)
	{
		this.filterManagerConfig = filterManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		params.put("user_id", UserInfoModel.getInstance().getUserId());
		params.put("sex", filterManagerConfig.getSex());
		params.put("minAge", filterManagerConfig.getFilterMinAge());
		params.put("maxAge", filterManagerConfig.getFilterMaxAge());
		params.put("radius", filterManagerConfig.getRadius());
		params.put("showOffline", filterManagerConfig.isShowOffline()? "true": "false");
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setAction(ACTION);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{
		filterManagerConfig.getFilterFragment().getRootView().post(new Runnable() {
		    @Override
		    public void run() {
		    	UserInfoModel.getInstance().getProgressDialog().hide();
		    	}
		});
		
		
		final WebView webView = filterManagerConfig.getFilterFragment().getWebView();
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	webView.loadUrl("javascript:  success();");
		    }
		});
		UserInfoModel.getInstance().getUserFilterModel().setFilterSex(filterManagerConfig.getSex());
		UserInfoModel.getInstance().getUserFilterModel().setFilterMinAge(Integer.parseInt(filterManagerConfig.getFilterMinAge()));
		UserInfoModel.getInstance().getUserFilterModel().setFilterMaxAge(Integer.parseInt(filterManagerConfig.getFilterMaxAge()));
		UserInfoModel.getInstance().getUserFilterModel().setFilterRadius(Integer.parseInt(filterManagerConfig.getRadius()));
		UserInfoModel.getInstance().getUserFilterModel().setFilterShowOffline(filterManagerConfig.isShowOffline());
	}
	
	@Override
	public void failed()
	{
		filterManagerConfig.getFilterFragment().getRootView().post(new Runnable() {
		    @Override
		    public void run() {
		    	UserInfoModel.getInstance().getProgressDialog().hide();
		    	}
		});		
		final WebView webView = filterManagerConfig.getFilterFragment().getWebView();
		webView.post(new Runnable() {
		    @Override
		    public void run() {
		    	webView.loadUrl("javascript:  failed('"+ responseModel.getStatusMsg() +"'); ");
		    }
		});
	}
	
	@Override
	public FilterResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new FilterResponseModel(finalResult);
	}
}
