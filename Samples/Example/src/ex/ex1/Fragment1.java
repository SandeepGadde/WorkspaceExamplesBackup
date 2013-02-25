package ex.ex1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment1 extends Fragment {
/**
* (non-Javadoc)
*
* @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
* android.view.ViewGroup, android.os.Bundle)
*/
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
return (LinearLayout) inflater.inflate(R.layout.fragment0_layout, container, false);
}
}
