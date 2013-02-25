package it.tn.alchemiasoft.casagranda.simone.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * TUTORIAL: communication between fragments in a ViewPAger
 * 
 * FragmentActivity that works as container for our fragment
 * 
 * @author Simone Casagranda
 * @category Android Tutorials
 *
 */
public class ViewPagerActivity extends FragmentActivity implements MessageLoader{
	
	private ViewPager mViewPager;
	private MessageLoader mLoader;
	private Button mSenderButton, mReceiverButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // We get UI references
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mSenderButton = (Button) findViewById(R.id.sender_button);
        mReceiverButton = (Button) findViewById(R.id.receiver_button);
        // We set pager adapter
        mViewPager.setAdapter(new MyAdapter(this));
        // We set receiver button listener
        mReceiverButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(1);
			}
		});
        // We set sender button listener
        mSenderButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(0);
			}
		});
    }
    
    /**
     * Adapter for ViewPAger that manages background interactions with fragments
     */
    private class MyAdapter extends FragmentPagerAdapter{

    	private Context mContext;
    	private String[] frags = {FirstFragment.class.getName(), SecondFragment.class.getName()};
    	
		public MyAdapter(FragmentActivity activity) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment frag = (Fragment) super.instantiateItem(container, position);
			if(frag instanceof MessageLoader){
				mLoader = (MessageLoader) frag;
			}
			return frag;
		}

		@Override
		public Fragment getItem(int pos) {
			return Fragment.instantiate(mContext, frags[pos]);
		}

		@Override
		public int getCount() {
			return frags.length;
		}
    	
    }

	@Override
	public void loadMessage(String message) {
		if(mLoader != null){
			mLoader.loadMessage(message);
			mViewPager.setCurrentItem(0);
		}
	}
}