package de.marcreichelt.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.marcreichelt.viewswitchexample.Example;
import de.marcreichelt.viewswitchexample.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridVehicleAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	public GridVehicleAdapter(Context context) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return Example.vehicleGridData.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Example.vehicleGridData.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gridvehicle, null);
            Log.d("Convert View is null","Yes it is "+position);
            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.grid_vehicle_text);
            holder.icon = (ImageView) convertView.findViewById(R.id.grid_item_image);

            convertView.setTag(holder);
        } else {
        	Log.d("Convert View is not null","Yes it is not "+position);
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
            
        }

        // Bind the data efficiently with the holder.
        holder.text.setText("$"+Example.vehicleGridData.get(position).getPrice()+" / "+
        		Example.vehicleGridData.get(position).getYear());
        
        if(Example.vehicleGridData.get(position).getImagePath()!=null)
		{			
				//imageView.setImageBitmap();
			holder.icon.setImageBitmap(Example.vehicleGridData.get(position).getImagePath());			
		}
        else
        	holder.icon.setImageResource(R.drawable.icon);

        return convertView;
		/*View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.gridvehicle, null);
		}
		
		String user = VehicleGrid.vehicleGridData.get(position).getPrice();
		if (user != null) {
			ImageView imageView = (ImageView) v
					.findViewById(R.id.grid_item_image);
			imageView.setTag(VehicleGrid.numbers[position]);
			if(VehicleGrid.vehicleGridData.get(position).getImagePath()!="")
			{
				try {
					imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream)new URL(VehicleGrid.vehicleGridData.get(position).getImagePath()).getContent()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					Log.d("Exc",e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("Exc",e.getMessage());
				}
			}
			
			TextView vehicleText = (TextView) v.findViewById(R.id.grid_vehicle_text);
			vehicleText.setText("$"+VehicleGrid.vehicleGridData.get(position).getPrice()+" / "+
			VehicleGrid.vehicleGridData.get(position).getYear());
			//Log.d("tag of img view:",VehicleGrid.numbers[position]);
			
			imageView.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("Clicked listing id",VehicleGrid.vehicleGridData.get(position).getListingID());
					//GetAutoActivity.updateModel(String.valueOf(VehicleGrid.numbers[position]));
				}
			});
		}
		return v;*/
	}
	static class ViewHolder {
        TextView text;
        ImageView icon;
    }

}
