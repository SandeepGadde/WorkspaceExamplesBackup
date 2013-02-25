package com.example.advertise_mock;
import com.directionalviewpager.DirectionalViewPager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DirectionalViewPager pager = (DirectionalViewPager)findViewById(R.id.pager);
		 pager.setAdapter(new TestFragmentAdapter(getSupportFragmentManager()));
		 pager.setSaveEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

}
