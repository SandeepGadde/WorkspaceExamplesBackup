package com.example.contentprovidersexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TutListDatabase extends SQLiteOpenHelper {
	private static final String DEBUG_TAG = "TutListDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "tutorial_data";
    
    public static final String TABLE_TUTORIALS = "tutorials";
    public static final String ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_URL = "url";
    private static final String CREATE_TABLE_TUTORIALS = "create table " + TABLE_TUTORIALS
    + " (" + ID + " integer primary key autoincrement, " + COL_TITLE
    + " text not null, " + COL_URL + " text not null);";
    private static final String DB_SCHEMA = CREATE_TABLE_TUTORIALS;
    
	public TutListDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DB_SCHEMA);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DEBUG_TAG, "Upgrading database. Existing contents will be lost. ["
	            + oldVersion + "]->[" + newVersion + "]");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORIALS);
	    onCreate(db);
	}
}
