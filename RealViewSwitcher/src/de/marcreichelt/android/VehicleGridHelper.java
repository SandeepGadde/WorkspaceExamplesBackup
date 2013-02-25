package de.marcreichelt.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class VehicleGridHelper {
	public Bitmap imagePath;
	public String price;
	public String listingID;
	public String year;
	public Bitmap getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		//this.imagePath = imagePath;
		//This convertion is to avoid loading from url eachtime the view changes.
		if(imagePath!=null)
		{
			this.imagePath = getConvertedImage(imagePath);
			//this.imagePath.recycle();
		}
		else
		{
			this.imagePath = null;
		}
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getListingID() {
		return listingID;
	}
	public void setListingID(String listingID) {
		this.listingID = listingID;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Bitmap getConvertedImage(String url)
	{
		InputStream is = null;
		try {
			is = (InputStream)new URL(url).getContent();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    //BitmapFactory.decodeStream(is,null,options);
		    //decodeResource(res, resId, options);

		    // Calculate inSampleSize
		    options.inSampleSize = calculateInSampleSize(options, 20, 20);

		    // Decode bitmap with inSampleSize set
		    options.inJustDecodeBounds = false;
		    return BitmapFactory.decodeStream(is, null, options);
		//BitmapFactory.decodeStream((InputStream)new URL(imagePath).getContent());
	}
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {
        if (width > height) {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        } else {
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }
    }
    return inSampleSize;
}
}
