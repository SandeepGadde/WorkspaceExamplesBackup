package com.example.dynamiclistview;

import java.util.LinkedList;

import android.content.Context;
import android.sax.RootElement;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	Context ctx;
	LinkedList<Helper> data;

	public CustomAdapter(Context ctx, LinkedList<Helper> data) {
		this.ctx = ctx;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final int position = arg0;
		View rowView = arg1;
		ViewHolder holder;
		if (rowView == null) {
			rowView = ((LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
			holder.text = (TextView) rowView.findViewById(R.id.text);
			holder.cb = (CheckBox) rowView.findViewById(R.id.checkbox);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg5, boolean arg6) {
				// TODO Auto-generated method stub
				if (arg5.isChecked()) {
					data.get(position).setChecked(1);
				} else {
					data.get(position).setChecked(0);
				}
			}
		});

		holder.text.setText(data.get(arg0).getText());
		boolean abc = (data.get(arg0).getChecked() == 0) ? false : true;
		holder.cb.setChecked(abc);

		return rowView;
	}

	static class ViewHolder {
		TextView text;
		CheckBox cb;
	}

}
