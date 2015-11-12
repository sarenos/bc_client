package com.example.bambicity.view.main;

import com.example.bambicity.R;
import com.example.bambicity.model.UserInfoModel;
import com.example.bambicity.view.authorization.AuthorizationDialog;
import com.example.bambicity.view.friends.FriendsFragment;
import com.example.bambicity.view.map.MapFragment;
import com.example.bambicity.view.map.UserLocationManager;
import com.example.bambicity.view.message.MessagesListFragment;
import com.example.bambicity.view.profile.ProfileFragment;
import com.example.bambicity.view.users.UsersListFragment;
import com.example.bambicity.workers.MyLocationsWorker;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;
	private LocationManager  locationManager;
	private ProgressBar progressBar;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressLint("NewApi")
	private void init() throws Exception
	{	
		progressBar = (ProgressBar) findViewById(R.id.progressBar4);
		progressBar.setVisibility(View.INVISIBLE);
		initNavigationDrawer();
    	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        UserLocationManager.initInstance(locationManager);
        
        progressDialog = new ProgressDialog(this);	
        progressDialog.setTitle("Загрузка");
  	  	//progressDialog.setMessage("Message");
  	  	progressDialog.setCanceledOnTouchOutside(false);
        UserInfoModel.getInstance().setProgressDialog(progressDialog);
        
        if(!UserInfoModel.getInstance().isLogin())
        {
        	new AuthorizationDialog(this).start();
        }
	}
	
	private void initNavigationDrawer()
	{
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}
	
	@Override
	public void onNavigationDrawerItemSelected(int position)
	{
		Intent intent = getIntent();
		String s1 = intent.getStringExtra("Check");
		if(s1 != null && s1.equals("1"))
		{
			position = 1 ;
	        intent.putExtra("Check", "0");
		}
		
		
		final FragmentManager fragmentManager = getSupportFragmentManager();

		switch (position) {
		case 0:
			MainActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					
					if(fragmentManager.findFragmentByTag("fragmentManager") != null){
					fragmentManager
					.beginTransaction().remove(fragmentManager.findFragmentByTag("fragmentManager")).commit();
					}
					fragmentManager.beginTransaction()
							.replace(R.id.container, MapFragment.newInstance(), "MapFragment").addToBackStack("mapFragment")
							.commit();
				}
			});
			break;
		case 1:
			MainActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(fragmentManager.findFragmentByTag("fragmentManager") != null){
					fragmentManager
					.beginTransaction().remove(fragmentManager.findFragmentByTag("fragmentManager")).commit();
					}
					
					fragmentManager
							.beginTransaction()
							.replace(R.id.container,
									new ProfileFragment(), "MyAccountFragment").addToBackStack("myAccountFragment").commit();
				}
			});
			break;
		case 2:
			MainActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(fragmentManager.findFragmentByTag("fragmentManager") != null){
					fragmentManager
					.beginTransaction().remove(fragmentManager.findFragmentByTag("fragmentManager")).commit();
					}		
					
					fragmentManager
							.beginTransaction()
							.replace(R.id.container,
									new FriendsFragment(), "FriendsFragment").addToBackStack("friendsFragment").commit();
				}
			});
			break;
		case 3:
			MainActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(fragmentManager.findFragmentByTag("fragmentManager") != null){
					fragmentManager
					.beginTransaction().remove(fragmentManager.findFragmentByTag("fragmentManager")).commit();
					}		
					
					fragmentManager
							.beginTransaction()
							.replace(R.id.container,
									new MessagesListFragment(), "MessagesListFragment").addToBackStack("messagesListFragment").commit();
				}
			});
			break;
		case 4:
			MainActivity.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(fragmentManager.findFragmentByTag("fragmentManager") != null){
					fragmentManager
					.beginTransaction().remove(fragmentManager.findFragmentByTag("fragmentManager")).commit();
					}		
					
					fragmentManager
							.beginTransaction()
							.replace(R.id.container,
									new UsersListFragment(), "UsersListFragment").addToBackStack("usersListFragment").commit();
				}
			});
			break;
		}
	};

	public void restoreActionBar()
	{
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if (!mNavigationDrawerFragment.isDrawerOpen()) 
		{
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		super.onCreateOptionsMenu(menu);
		menu.close();
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} 
		
		if (id == R.id.log_out) 
		{
			try {
				UserInfoModel.getInstance().setLogin(false);
				init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		if (id == R.id.exit) 
		{
			this.stopService(new Intent(this, MyLocationsWorker.class));
        	android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } 
		return super.onOptionsItemSelected(item);
	}
	
	  @Override
	  protected void onPause() {
	    super.onPause();
	  }
	  
	  @Override
	  protected void onResume() {
		  super.onResume();
	    
	  }  
}
