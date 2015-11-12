package com.example.bambicity.APILayers.photo;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bambicity.APILayers.Manager;
import com.example.bambicity.model.UserInfoModel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public class UpdatePhotoManager extends Manager {
    public static String ACTION_URL = "authorization.php";
    public static String ACTION = "update";
    public static String METOD = "post";
	
	private UpdatePhotoManagerConfig updatePhotoManagerConfig;
	
	public UpdatePhotoManager(UpdatePhotoManagerConfig updatePhotoManagerConfig)
	{
		this.updatePhotoManagerConfig = updatePhotoManagerConfig;
	}
	
	@Override
	public void initRequestParams()
	{
		
		if(updatePhotoManagerConfig.getPhoto() != null){
			  Bitmap bitmapOrg = BitmapFactory.decodeFile(updatePhotoManagerConfig.getPhoto());
	          ByteArrayOutputStream bao = new ByteArrayOutputStream();

	          //Resize the image
	          double width = bitmapOrg.getWidth();
	          double height = bitmapOrg.getHeight();
	          double ratio = 200/width;
	          int newheight = (int)(ratio*height);

	          bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 200, newheight, false);

	          //Here you can define .PNG as well
	          bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
	          byte[] ba = bao.toByteArray();
				String encodedImage = Base64.encodeToString(ba, Base64.DEFAULT);
				params.put("photo", encodedImage);
			}
		
		params.put("user_id", UserInfoModel.getInstance().getUserId());
	}
	
	@Override
	public void initRequestConfig()
	{
		httpRequestConfig.setActionUrl(ACTION_URL);
    	httpRequestConfig.setAction(ACTION);
		httpRequestConfig.setMetod(METOD);
		httpRequestConfig.setParams(params);
		httpRequestConfig.setHTTPService(this);
	}
	
	@Override
	public void success()
	{
		UserInfoModel.getInstance().setPhoto("static/img/" + UserInfoModel.getInstance().getUserId()+".jpg");
		updatePhotoManagerConfig.getPhotoIntentActivity().close();
	}
	
	@Override
	public void failed()
	{

	}
	
	@Override
	public UpdatePhotoResponseModel getResponseModel(JSONObject finalResult) throws JSONException
	{
		return new UpdatePhotoResponseModel(finalResult);
	}
}
