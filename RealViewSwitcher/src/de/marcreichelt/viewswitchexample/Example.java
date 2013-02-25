package de.marcreichelt.viewswitchexample;

import java.util.ArrayList;

import de.marcreichelt.android.GridVehicleAdapter;
import de.marcreichelt.android.RealViewSwitcher;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Example of how to use the {@link RealViewSwitcher} class.
 * 
 * @author Marc Reichelt, <a href="http://www.marcreichelt.de/">http://www.marcreichelt.de/</a>
 */
public class Example extends Activity {
	static RealViewSwitcher realViewSwitcher = null;
	//int i=0;
	static GridView gv =null;
	static GridVehicleAdapter gia = null;
	public static ArrayList<de.marcreichelt.android.VehicleGridHelper> vehicleGridData;
	public static Context ctx ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// create the view switcher
		 realViewSwitcher = new RealViewSwitcher(getApplicationContext());
		vehicleGridData = new ArrayList<de.marcreichelt.android.VehicleGridHelper>();
		// add some views to it
		final int[] backgroundColors = { Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW };
		//for (int i = 1; i <= 5; i++) {
			TextView textView = new TextView(getApplicationContext());
			textView.setText(Integer.toString(0));
			textView.setTextSize(100);
			textView.setTextColor(Color.BLACK);
			textView.setGravity(Gravity.CENTER);
			textView.setBackgroundColor(backgroundColors[0]);
			realViewSwitcher.addView(textView);
		//}

		// set as content view
		setContentView(realViewSwitcher);
		
		
		
		// yeah, it really is as simple as this :-)
		
		// note that you can also define your own views directly in a resource XML, too by using:
		// <de.marcreichelt.android.RealViewSwitcher
		//     android:layout_width="fill_parent"
		//     android:layout_height="fill_parent"
		//     android:id="@+id/real_view_switcher">
		//         <!-- your views here -->
		// </de.marcreichelt.android.RealViewSwitcher>
		
		
		
		// OPTIONAL: listen for screen changes
		realViewSwitcher.setOnScreenSwitchListener(onScreenSwitchListener);
	}
	
	private final RealViewSwitcher.OnScreenSwitchListener onScreenSwitchListener = new RealViewSwitcher.OnScreenSwitchListener() {
		
		public void onScreenSwitched(int screen) {
			
			// this method is executed if a screen has been activated, i.e. the screen is completely visible
			//  and the animation has stopped (might be useful for removing / adding new views)
			//if(screen>=4)
			//{
				int x = screen;
				Log.d("Adding screen", "switched to screen: " + x);
				TextView textView = new TextView(getApplicationContext());
				textView.setText(Integer.toString(1+x));
				textView.setTextSize(100);
				textView.setTextColor(Color.BLACK);
				textView.setGravity(Gravity.CENTER);
				textView.setBackgroundColor(Color.GRAY);
				realViewSwitcher.addView(textView);
				realViewSwitcher.refreshDrawableState();
			//}
			//else
			//	Log.d("RealViewSwitcher", "switched to screen: " + screen);
		}
	};
}
