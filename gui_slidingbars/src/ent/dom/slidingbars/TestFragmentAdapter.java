package ent.dom.slidingbars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

class TestFragmentAdapter extends FragmentPagerAdapter {
	protected final int[] CONTENT = new int[] { R.drawable.priority_rewards, R.drawable.map_view, R.drawable.top_hotels, R.drawable.video};

	public TestFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return TestFragment.newInstance(CONTENT[position],position);
	}

	@Override
	public int getCount() {
		return CONTENT.length;
	}
}
