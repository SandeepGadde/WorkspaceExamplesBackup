package com.example.smsapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		textSMS = (EditText) findViewById(R.id.editTextSMS);
		
		buttonSend.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
 
			  String phoneNo = textPhoneNo.getText().toString();
			  String sms = textSMS.getText().toString();
 
			  try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, sms, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
			  } catch (Exception e) {
				Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
				e.printStackTrace();
			  }
 
			}
		});
		
		
		buttonSend.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
 
				try {
 
				     Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				     sendIntent.putExtra("sms_body", "default content"); 
				     sendIntent.setType("vnd.android-dir/mms-sms");
				     startActivity(sendIntent);
				     
				     
     
 
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
