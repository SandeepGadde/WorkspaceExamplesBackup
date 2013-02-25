package com.example.broadcastreceiverexample;

import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        //When Event is published, onReceive method is called
      public void onReceive(Context c, Intent i) {
            //Get Battery %
          int level = i.getIntExtra("level", 0);
            //Find the progressbar creating in main.xml
          ProgressBar pb = (ProgressBar) findViewById(R.id.progressbar);
            //Set progress level with battery % value
          pb.setProgress(level);
            //Find textview control created in main.xml
          TextView tv = (TextView) findViewById(R.id.textfield);
            //Set TextView with text
          tv.setText("Battery Level: " + Integer.toString(level) + "%");
      }
   };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		registerReceiver(mReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
  	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {		
		super.onPause();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
/*


*/
