package com.example.bambicity.view.friends;

import java.util.ArrayList;

import com.example.bambicity.R;
import com.example.bambicity.APILayers.Friends.friends_list.FriendsListManager;
import com.example.bambicity.APILayers.Friends.friends_list.FriendsManagerConfig;
import com.example.bambicity.model.UserInfoModel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

public class FriendsFragment extends Fragment {

	   private TabHost mTabHost;
	    private ViewPager mViewPager;
	    public TabsAdapter mTabsAdapter;
	    MyFriendsTab myFriendsTab;
	    public MyAppFriendsTab myAppFriendsTab;

		private FriendsManagerConfig friendsMangerConfig;
		private FriendsListManager friendsListManger;
		public View view;


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.friends_fragment, container, false);
		        mTabHost = (TabHost) view.findViewById(android.R.id.tabhost);
		        mTabHost.setup();

		        mViewPager = (ViewPager) view.findViewById(R.id.pager);
		        		        
				this.friendsMangerConfig = new FriendsManagerConfig();
				friendsMangerConfig.setMyFriendsTab(this);
				this.friendsListManger = new FriendsListManager(friendsMangerConfig);
				friendsListManger.send();

		        Log.d("testActivity","onCreateView");

		        return view;
		}

		public void showTabs()
		{
	        mTabsAdapter = new TabsAdapter(getActivity(), mTabHost, mViewPager);

	        myFriendsTab = new MyFriendsTab();
	        myAppFriendsTab = new MyAppFriendsTab();
	        // Here we load the content for each tab. 
	        mTabsAdapter.addTab(mTabHost.newTabSpec("one").setIndicator("Друзья"), myFriendsTab.getClass(), null);
	        mTabsAdapter.addTab(mTabHost.newTabSpec("two").setIndicator("Заявки в друзья"), myAppFriendsTab.getClass(), null);
		}
		
	    @Override
	    public void onAttach(Activity activity)
	    {
	        super.onAttach(activity);
			Log.d("testActivity", "FriendsFragment onAttach");

	    }

	    @Override
	    public void onStart()
	    {
	        super.onStart();
			Log.d("testActivity", "FriendsFragment onStart");

	    }

	    @Override
	    public void onResume()
	    {
	        super.onResume();

			Log.d("testActivity", "FriendsFragment onResume");

	    }

	    @Override
	    public void onStop()
	    {
	        super.onResume();
			Log.d("testActivity", "FriendsFragment onStop");

	    }

		public static class TabsAdapter extends FragmentStatePagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
	    {
	        private final Context mContext;
	        private final TabHost mTabHost;
	        private final ViewPager mViewPager;
	        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	        static final class TabInfo
	        {
	            private final String tag;
	            private final Class<?> clss;
	            private final Bundle args;

	            TabInfo(String _tag, Class<?> _class, Bundle _args)
	            {
			        Log.d("testActivity","2");

	                tag = _tag;
	                clss = _class;
	                args = _args;
	            }
	        }

	        static class DummyTabFactory implements TabHost.TabContentFactory
	        {
	            private final Context mContext;

	            public DummyTabFactory(Context context)
	            {
	                mContext = context;
	            }

	            @Override
				public View createTabContent(String tag)
	            {
			        Log.d("testActivity","3");

	                View v = new View(mContext);
	                v.setMinimumWidth(0);
	                v.setMinimumHeight(0);
	                return v;
	            }
	        }

	        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager)
	        {
	            super(activity.getSupportFragmentManager());
		        Log.d("testActivity","4");

	            mContext = activity;
	            mTabHost = tabHost;
	            mViewPager = pager;
	            mTabHost.setOnTabChangedListener(this);
	            mViewPager.setAdapter(this);
	            mViewPager.setOnPageChangeListener(this);
	        }

	        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args)
	        {
		        Log.d("testActivity","5");

	            tabSpec.setContent(new DummyTabFactory(mContext));
	            String tag = tabSpec.getTag();

	            TabInfo info = new TabInfo(tag, clss, args);
	            mTabs.add(info);
	            mTabHost.addTab(tabSpec);
	            notifyDataSetChanged();
	        }

	        @Override
	        public int getCount()
	        {
	            return mTabs.size();
	        }

	        @Override
	        public Fragment getItem(int position)
	        {		      
	        	Log.d("testActivity","6");
	            TabInfo info = mTabs.get(position);
	            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
	        }

	        @Override
			public void onTabChanged(String tabId)
	        {
	            int position = mTabHost.getCurrentTab();
	            mViewPager.setCurrentItem(position);
	           
	        	Log.d("test","change1 tab: " + tabId);
	        }

	        @Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	        {

	        }

	        @Override
			public void onPageSelected(int position)
	        {
	            TabWidget widget = mTabHost.getTabWidget();
	            int oldFocusability = widget.getDescendantFocusability();
	            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
	            mTabHost.setCurrentTab(position);
	            widget.setDescendantFocusability(oldFocusability);
	        	Log.d("test","change3");
	        }

	        @Override
			public void onPageScrollStateChanged(int state)
	        {
	        }
	    }
	}
