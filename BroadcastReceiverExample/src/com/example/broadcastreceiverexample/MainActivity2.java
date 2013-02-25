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

public class MainActivity2 extends Activity {
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
}
public void startAlert(View view) {
	EditText text = (EditText) findViewById(R.id.time);
	int i = Integer.parseInt(text.getText().toString());
	Intent intent = new Intent(this, MyBroadcastReceiver.class);
	sendBroadcast(intent);
	PendingIntent pendingIntent = PendingIntent.getBroadcast(
			this.getApplicationContext(), 234324243, intent, 0);
	AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
			+ (i * 1000), pendingIntent);
	Toast.makeText(this, "Alarm set in " + i + " seconds",
			Toast.LENGTH_LONG).show();
}
}
