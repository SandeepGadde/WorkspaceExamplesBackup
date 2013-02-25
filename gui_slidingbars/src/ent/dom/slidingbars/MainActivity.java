package ent.dom.slidingbars;

import com.directionalviewpager.DirectionalViewPager;

import ent.dom.slidingbars.widget.AnimationLayout;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity implements AnimationLayout.Listener {
	public final static String TAG = "Demo";

	protected ListView mList;
	protected AnimationLayout mLayout;
	protected String[] mStrings = { "a", "b", "c", "d"};
	DisplayMetrics disp;
	int width;
	LinearLayout sidebarLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		disp = getResources().getDisplayMetrics();
		width = disp.widthPixels;
		setContentView(R.layout.main);
		// sidebarLayout = (LinearLayout)
		// findViewById(R.id.animation_layout_sidebar);
		// sidebarLayout.setLayoutParams(new
		// LayoutParams(width,LinearLayout.LayoutParams.MATCH_PARENT));
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this);
		DirectionalViewPager pager = (DirectionalViewPager)findViewById(R.id.pager);
		pager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));
		pager.setSaveEnabled(false);
		// gallery = (ent.dom.slidingbars.widget.GalleryLayout)
		// findViewById(R.id.galleryview);
		// gallery.setAdapter(new ImageAdapter(this));

		//mList = (ListView) findViewById(R.id.sidebar_list);
		//mList.setAdapter(new MyAdapter(this, mStrings));
		/*
		 * mList.setAdapter( new ArrayAdapter<String>( this,
		 * android.R.layout.simple_list_item_multiple_choice , mStrings));
		 * mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		 */

	}

	public void onClickContentButton(View v) {
		mLayout.toggleSidebar(0);
	}

	public void onClickContentButton2(View v) {
		mLayout.toggleSidebar(1);
	}

	@Override
	public void onBackPressed() {
		if (mLayout.isOpening()) {
			mLayout.closeSidebar();
		} else {
			finish();
		}
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarOpened() {
		Log.d(TAG, "opened");

	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public void onSidebarClosed() {
		Log.d(TAG, "opened");
	}

	/* Callback of AnimationLayout.Listener to monitor status of Sidebar */
	@Override
	public boolean onContentTouchedWhenOpening() {
		// the content area is touched when sidebar opening, close sidebar
		Log.d(TAG, "going to close sidebar");
		mLayout.closeSidebar();
		return true;
	}


}
