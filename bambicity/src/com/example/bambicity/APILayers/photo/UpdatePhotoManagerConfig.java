package com.example.bambicity.APILayers.photo;

import com.example.bambicity.view.photo.PhotoIntentActivity;

public class UpdatePhotoManagerConfig {
	
	private String photo;
	private PhotoIntentActivity photoIntentActivity;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public PhotoIntentActivity getPhotoIntentActivity() {
		return photoIntentActivity;
	}

	public void setPhotoIntentActivity(PhotoIntentActivity photoIntentActivity) {
		this.photoIntentActivity = photoIntentActivity;
	}
}
