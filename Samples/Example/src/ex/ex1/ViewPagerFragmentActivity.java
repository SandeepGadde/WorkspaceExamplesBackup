package ex.ex1;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class ViewPagerFragmentActivity extends FragmentActivity {

private PagerAdapter mPagerAdapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
super.setContentView(R.layout.viewpager_layout);

// initialize the pager
this.initialisePaging();
}

/**
* Initialize the fragments to be paged
*/
private void initialisePaging() {

List<Fragment> fragments = new Vector<Fragment>();
fragments.add(Fragment.instantiate(this, Fragment0.class.getName()));
fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));
fragments.add(Fragment.instantiate(this, Fragment2.class.getName()));
this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

ViewPager pager = (ViewPager) super.findViewById(R.id.awesomepager);
pager.setAdapter(this.mPagerAdapter);
}
}
