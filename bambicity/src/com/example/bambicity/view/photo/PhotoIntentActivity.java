package com.example.bambicity.view.photo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.example.bambicity.R;
import com.example.bambicity.APILayers.photo.UpdatePhotoManager;
import com.example.bambicity.APILayers.photo.UpdatePhotoManagerConfig;
import com.example.bambicity.view.main.MainActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class PhotoIntentActivity extends ActionBarActivity implements OnClickListener{

	 ImageView viewImage;
	    Button b;
	    Button btnOK;
	    Button btnCancel;
	    String photoPath;
	    private UpdatePhotoManagerConfig updatePhotoManagerConfig;
	    private UpdatePhotoManager updatePhotoManager;
		private ProgressBar progressBar;


	    final String TAG = "States";

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        updatePhotoManagerConfig = new UpdatePhotoManagerConfig();
	        updatePhotoManager = new UpdatePhotoManager(updatePhotoManagerConfig);
	        setContentView(R.layout.phac);
	        b=(Button)findViewById(R.id.btnSelectPhoto);
	        viewImage=(ImageView)findViewById(R.id.viewImage);
	        b.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                selectImage();
	            }
	        });
	        
	        btnOK = (Button) findViewById(R.id.btnOK);
	        btnOK.setOnClickListener(this);
	        btnCancel = (Button) findViewById(R.id.btnCancel);
	        btnCancel.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	    	    	close();
	    	        }
	        });
			//progressBar = (ProgressBar) findViewById(R.id.photo_progress_bar);
			//progressBar.setVisibility(View.INVISIBLE);
	    }
	    
	    @Override
	    public void onBackPressed()
	    {
	    	close();
	    }
	    
	    @Override
		public void onClick(View v) {
	    	updatePhotoManagerConfig.setPhoto(photoPath);
	    	updatePhotoManagerConfig.setPhotoIntentActivity(this);
	    	updatePhotoManager.send();
	    }
	    
	    public void close()
	    {
	        Intent intent = new Intent(PhotoIntentActivity.this, MainActivity.class);
	        intent.putExtra("Check", "1");
	        startActivity(intent);
	        finish();
	    }
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds options to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 
	      private void selectImage() {
	 
	        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
	 
	        AlertDialog.Builder builder = new AlertDialog.Builder(PhotoIntentActivity.this);
	        builder.setTitle("Add Photo!");
	        builder.setItems(options, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	                if (options[item].equals("Take Photo"))
	                {
	                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
	                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
	                    startActivityForResult(intent, 1);
	                }
	                else if (options[item].equals("Choose from Gallery"))
	                {
	                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                    startActivityForResult(intent, 2);
	 
	                }
	                else if (options[item].equals("Cancel")) {
	                    dialog.dismiss();
	                }
	            }
	        });
	        builder.show();
	    }
	 
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (resultCode == RESULT_OK) {
	            if (requestCode == 1) {
	                File f = new File(Environment.getExternalStorageDirectory().toString());
	                for (File temp : f.listFiles()) {
	                    if (temp.getName().equals("temp.jpg")) {
	                        f = temp;
	                        break;
	                    }
	                }
	                try {
	                    Bitmap bitmap;
	                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	 
	                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
	                            bitmapOptions); 
	                   
	                    viewImage.setImageBitmap(bitmap);
	 
	                    String path = android.os.Environment
	                            .getExternalStorageDirectory()
	                            + File.separator
	                            + "DCIM" + File.separator + "Camera";
	                    f.delete();
	                    OutputStream outFile = null;
	                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
	                    photoPath = file.getAbsolutePath();
	                    try {
	                        outFile = new FileOutputStream(file);
	                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outFile);
	                        outFile.flush();
	                        outFile.close();
	                    } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            } else if (requestCode == 2) {
	 
	                Uri selectedImage = data.getData();
	                String[] filePath = { MediaColumns.DATA };
	                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
	                c.moveToFirst();
	                int columnIndex = c.getColumnIndex(filePath[0]);
	                String picturePath = c.getString(columnIndex);
	                photoPath = picturePath;
	                c.close();
	                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
	                Log.w("path of image from gallery......******************.........", picturePath+"");
	                viewImage.setImageBitmap(thumbnail);
	            }
	        }
	    }   
	    
	    @Override
	    protected void onRestart() {
	      super.onRestart();
	      Log.d(TAG, "ActivityTwo: onRestart()");
	    }

	    @Override
	    protected void onStart() {
	      super.onStart();
	      Log.d(TAG, "ActivityTwo: onStart()");
	    }

	    @Override
	    protected void onResume() {
	      super.onResume();
	      Log.d(TAG, "ActivityTwo: onResume()");
	    }

	    @Override
	    protected void onPause() {
	      super.onPause();
	      Log.d(TAG, "ActivityTwo: onPause()");
	    }

	    @Override
	    protected void onStop() {
	      super.onStop();
	      Log.d(TAG, "ActivityTwo: onStop()");
	    }

	    @Override
	    protected void onDestroy() {
	      super.onDestroy();
	      Log.d(TAG, "ActivityTwo: onDestroy()");
	    } 
	    
	    public void showProgressBar()
	    {
		
			    	progressBar.setVisibility(View.VISIBLE);
	    }
	    
	    public void hideProgressBar()
	    {
			    	progressBar.setVisibility(View.INVISIBLE);
	    }
}