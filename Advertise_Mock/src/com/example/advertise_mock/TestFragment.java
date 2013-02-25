package com.example.advertise_mock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
public final class TestFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	public static int position1=0;
	public static TestFragment newInstance(int content,int position) {
		TestFragment fragment = new TestFragment();
		fragment.mContent = content;
		return fragment;
	}

	private int mContent = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getInt(KEY_CONTENT);
		}

		//Main Layout
		LinearLayout text = new LinearLayout(getActivity());
		LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params0.setMargins(20, 20, 20, 20);			
		text.setLayoutParams(params0);
		text.setWeightSum(1.0f);
		text.setOrientation(LinearLayout.VERTICAL);
		text.setBackgroundResource(mContent);
		text.setGravity(Gravity.CENTER_HORIZONTAL);			
		
			//Sub Layout
			LinearLayout layout = new LinearLayout(getActivity());
			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 0,0.5f);
					
			layout.setLayoutParams(params2);						
			layout.setGravity(Gravity.TOP);			
			
						
			//Child 1 of Sub Layout
			ImageView up = new ImageView(getActivity());					
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			up.setLayoutParams(params);
			up.setBackgroundResource(R.drawable.arrow_up);	
			layout.addView(up);

			//Sub Layout
			LinearLayout layout1 = new LinearLayout(getActivity());
			layout1.setLayoutParams(params2);
			layout1.setGravity(Gravity.BOTTOM);
			
			
			//Child 2 of Sub Layout
			ImageView down = new ImageView(getActivity());			
			LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			down.setLayoutParams(params1);			
			down.setBackgroundResource(R.drawable.arrow_down);
			layout1.addView(down);
		
		text.addView(layout);
		text.addView(layout1);
		
		if(mContent == R.drawable.video) {
			text.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					position1 = 3;
					startActivity(new Intent(getActivity(),VideoActivity.class));
				}
			});
		}
		else if(mContent == R.drawable.map_view) {
			text.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					position1 = 1;
					startActivity(new Intent(getActivity(),VideoActivity.class));
				}
			});
		}
		else if(mContent == R.drawable.priority_rewards) {
			text.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					position1 = 0;
					startActivity(new Intent(getActivity(),VideoActivity.class));
				}
			});
		}
		return text;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CONTENT, mContent);
	}
}
