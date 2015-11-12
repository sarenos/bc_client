package com.example.bambicity.APILayers.filter;

import com.example.bambicity.view.filter.FilterFragment;
import com.example.bambicity.view.main.MainActivity;

public class FilterManagerConfig {
	
	private MainActivity mainActivity;	
	private String sex;
	private String filterMinAge;
	private String filterMaxAge;
	private String radius;
	private FilterFragment filterFragment;
	private boolean showOffline;
	
	public MainActivity getMainActivity() {
		return mainActivity;
	}
	
	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	
	public String getSex() 
	{
		return sex;
	}

	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	
	public String getFilterMinAge() {
		return filterMinAge;
	}

	public void setFilterMinAge(String filterMinAge) {
		this.filterMinAge = filterMinAge;
	}

	public String getFilterMaxAge() {
		return filterMaxAge;
	}

	public void setFilterMaxAge(String filterMaxAge) {
		this.filterMaxAge = filterMaxAge;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public FilterFragment getFilterFragment() {
		return filterFragment;
	}

	public void setFilterFragment(FilterFragment filterFragment) {
		this.filterFragment = filterFragment;
	}

	public boolean isShowOffline() {
		return showOffline;
	}

	public void setShowOffline(boolean showOffline) {
		this.showOffline = showOffline;
	}
}
