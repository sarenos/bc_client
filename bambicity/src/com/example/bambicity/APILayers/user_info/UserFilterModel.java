package com.example.bambicity.APILayers.user_info;

import org.json.JSONObject;

public class UserFilterModel {
	private String filterSex;
	private int filterMinAge;
	private int filterMaxAge;
	private int filterRadius;
	private boolean filterShowOffline;
	
	public UserFilterModel(JSONObject jsonObject) {
		this.filterSex = jsonObject.optString("sex");
		this.filterMinAge = jsonObject.optInt("minAge");
		this.filterMaxAge = jsonObject.optInt("maxAge");
		this.filterRadius = jsonObject.optInt("radius");
		this.filterShowOffline = jsonObject.optBoolean("showOffline");
	}
	
	public String getFilterSex() {
		return filterSex;
	}

	public int getFilterMinAge() {
		return filterMinAge;
	}

	public int getFilterMaxAge() {
		return filterMaxAge;
	}

	public int getFilterRadius() {
		return filterRadius;
	}

	public void setFilterRadius(int filterRadius) {
		this.filterRadius = filterRadius;
	}

	public boolean isFilterShowOffline() {
		return filterShowOffline;
	}

	public void setFilterShowOffline(boolean filterShowOffline) {
		this.filterShowOffline = filterShowOffline;
	}

	public void setFilterSex(String filterSex) {
		this.filterSex = filterSex;
	}

	public void setFilterMinAge(int filterMinAge) {
		this.filterMinAge = filterMinAge;
	}

	public void setFilterMaxAge(int filterMaxAge) {
		this.filterMaxAge = filterMaxAge;
	}
}
