package com.example.contentprovidersexample;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
	Context ctx;
	ListView list;
	Cursor tutorials;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ctx = this;
		list= (ListView)findViewById(R.id.list);
	    String[] projection = { TutListDatabase.ID, TutListDatabase.COL_TITLE };
	    String[] uiBindFrom = { TutListDatabase.COL_TITLE };
	    int[] uiBindTo = { android.R.id.text1 };
	     tutorials = getContentResolver().query(
	            TutListProvider.CONTENT_URI, projection, null, null, null);
	    
	    CursorAdapter adapter = new SimpleCursorAdapter(ctx.getApplicationContext(), 
	    		android.R.layout.simple_list_item_1, tutorials,
	            uiBindFrom, uiBindTo);
	    list.setAdapter(adapter);		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		tutorials.close();
	}
}
