package com.example.bambicity.view.filter;

import android.webkit.JavascriptInterface;
import com.example.bambicity.APILayers.filter.FilterManagerConfig;
import com.example.bambicity.APILayers.filter.UpdateFilterManager;
import com.example.bambicity.model.UserInfoModel;

public class FilterBridgeJS {

	private FilterManagerConfig filterManagerConfig;
	private UpdateFilterManager updateFilterManager;
	private FilterFragment filterFragment;
	
	FilterBridgeJS(FilterFragment filterFragment)
	{
		this.filterFragment = filterFragment;
		this.filterManagerConfig = new FilterManagerConfig();
		filterManagerConfig.setFilterFragment(filterFragment);
		this.updateFilterManager = new UpdateFilterManager(filterManagerConfig);
	}
	
	@JavascriptInterface
	public String getFilterSex() 
	{
		return UserInfoModel.getInstance().getUserFilterModel().getFilterSex();
	}
	    
	@JavascriptInterface
	public int getFilterMinAge()
	{
		return UserInfoModel.getInstance().getUserFilterModel().getFilterMinAge();
	}
	    
	@JavascriptInterface
	public int getFilterMaxAge()
	{
		return UserInfoModel.getInstance().getUserFilterModel().getFilterMaxAge();
	}
	
	@JavascriptInterface
	public int getFilterRadius()
	{
		return UserInfoModel.getInstance().getUserFilterModel().getFilterRadius();
	}
	
	@JavascriptInterface
	public boolean getFilterShowOffline()
	{
		return UserInfoModel.getInstance().getUserFilterModel().isFilterShowOffline();
	}
	    
	@JavascriptInterface
	public void saveFilter(String filterSex, String filterMinAge, String filterMaxAge, String filterRadius, boolean showOffline)
	{
		filterFragment.getRootView().post(new Runnable() {
			@Override
			public void run() {
			    UserInfoModel.getInstance().getProgressDialog().show();			
			}
		});
		filterManagerConfig.setSex(filterSex);
		filterManagerConfig.setFilterMaxAge(filterMaxAge);
		filterManagerConfig.setFilterMinAge(filterMinAge);
		filterManagerConfig.setRadius(filterRadius);
		filterManagerConfig.setShowOffline(showOffline);
		updateFilterManager.send();
	}
}
