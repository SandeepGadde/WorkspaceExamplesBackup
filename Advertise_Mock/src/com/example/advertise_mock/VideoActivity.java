package com.example.advertise_mock;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends MapActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(TestFragment.position1 == 3) {
			setContentView(R.layout.videoview);		
			VideoView v = (VideoView)findViewById(R.id.videoView);
			MediaController mediaController= new MediaController(this);
		    mediaController.setAnchorView(v);        
		    Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.documentariesandyou);        
		    v.setMediaController(mediaController);
		    v.setVideoURI(uri);        
		    v.requestFocus();	
		    v.start();
		}
		else if(TestFragment.position1 == 1) {			
			setContentView(R.layout.mapview);
			MapView map = (MapView)findViewById(R.id.mapview);
			float lat = -23.4456f;
			float lng = 45.44334f;
			GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
			//GeoPoint point = new GeoPoint((int)36.847603,(int)-76.291584);
			if(point!=null) {
				map.getController().animateTo(point);
			//	map.getController().setZoom(10);
			}
		}
		else if(TestFragment.position1 == 0) {
			setContentView(R.layout.webview);
			WebView webview = (WebView)findViewById(R.id.webview1);	 
	        WebSettings settings = webview.getSettings();
	        settings.setJavaScriptEnabled(true);
	        settings.setJavaScriptCanOpenWindowsAutomatically(false);
	        settings.setPluginsEnabled(false);
	        settings.setSupportMultipleWindows(true);
	        settings.setSupportZoom(false);
	        
	        //settings.setAppCacheEnabled(true);
	        
	        
	        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	        webview.setWebChromeClient(new WebChromeClient());
	        webview.setAlwaysDrawnWithCacheEnabled(true);        	       
	        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	        final Dialog progressBar = ProgressDialog.show(this, "", "Loading...     ");
	        webview.setWebViewClient(new WebViewClient() {
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                Log.i("asdkhsa", "Processing webview url click...");	                
	                view.loadUrl(url);
	                return false;
	            }
	            
	            public void onPageFinished(WebView view, String url) {
	            	//Log.d("onPageStarted original",view.getOriginalUrl());
	            	Log.d("onPageStarted",url);
	                if (progressBar.isShowing()) {
	                    progressBar.dismiss();
	                }
	                
	            }
	            
	            public void onPageStarted(WebView view, String url, Bitmap favicon)
	            {
	            	//Log.d("onPageStarted original",view.getOriginalUrl());
	            	Log.d("onPageStarted",url);
	            	if(!progressBar.isShowing())
	            		progressBar.show();
	            }
	            
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {	                	                
	                alertDialog.setTitle("Error");
	                alertDialog.setMessage(description);
	                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int which) {
	                        return;
	                    }
	                });
	                alertDialog.show();
	            }
	        });     
	        webview.loadUrl("http://m.hotelcoupons.com/mobile/");
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
