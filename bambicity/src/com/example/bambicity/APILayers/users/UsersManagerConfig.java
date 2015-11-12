package com.example.bambicity.APILayers.users;

import com.example.bambicity.view.map.MapFragment;

public class UsersManagerConfig  {
	private MapFragment mapFragment;

	public UsersManagerConfig(MapFragment mapFragment)
	{
		this.mapFragment = mapFragment;
	}
	
	public MapFragment getMapFragment() {
		return mapFragment;
	}
}
