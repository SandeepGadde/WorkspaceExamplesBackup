package com.example.dynamiclistview;

import java.util.ArrayList;
import java.util.LinkedList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	LinkedList<Helper> data = new LinkedList<Helper>();
	CustomAdapter adapter;
	int clickCounter = 0;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
		data.add(new Helper(++clickCounter + "|", 0));
		adapter = new CustomAdapter(this, data);
		setListAdapter(adapter);
	}

	public void addItemsFront(View v) {
		data.addFirst(new Helper(clickCounter++ + "|", 0));		
		adapter.notifyDataSetChanged();
	}

	public void addItemsEnd(View v) {
		data.addLast(new Helper(clickCounter++ + "|", 0));
		data.remove(0);
		data.add(0, new Helper(10000+"|",0));
		adapter.notifyDataSetChanged();
	}
}