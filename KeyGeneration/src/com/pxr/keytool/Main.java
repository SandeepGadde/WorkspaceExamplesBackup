package com.pxr.keytool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

public class Main extends Activity {
	
	public static String PACKAGENAME = "com.pxr.keytool";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
        
        try {
        	   PackageInfo info = getPackageManager().getPackageInfo(PACKAGENAME, PackageManager.GET_SIGNATURES);
        	   for (Signature signature : info.signatures) {
        	        MessageDigest md = MessageDigest.getInstance("SHA");
        	        md.update(signature.toByteArray());
        	        Log.i("PXR", Base64.encodeBytes(md.digest()));
        	   }
        	} 
        catch (NameNotFoundException e) {} 
        catch (NoSuchAlgorithmException e) {}
    }
}