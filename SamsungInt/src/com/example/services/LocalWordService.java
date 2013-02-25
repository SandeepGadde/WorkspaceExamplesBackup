package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalWordService extends Service {
  private final IBinder mBinder = new MyBinder();
  private ArrayList<String> list = new ArrayList<String>();

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
	  if(list.size()>20)
    	  list.clear();
	  list.add("Linux");
      list.add("Android");
      list.add("iPhone");
      list.add("Windows7");
      
      return Service.START_STICKY;
  }

  @Override
  public IBinder onBind(Intent arg0) {
    return mBinder;
  }

  public class MyBinder extends Binder {
    LocalWordService getService() {
      return LocalWordService.this;
    }
  }

  public List<String> getWordList() {
    return list;
  }
} 
