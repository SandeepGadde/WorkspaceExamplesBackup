package com.example.contentprovidersexample;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.Contacts.People;
import android.text.TextUtils;
import android.util.Log;

public class TutListProvider extends ContentProvider{
	private TutListDatabase mDB;
	private static final String AUTHORITY = "com.example.contentprovidersexample.TutListProvider";
	public static final int TUTORIALS = 100;
	public static final int TUTORIAL_ID = 110;
	private static final String TUTORIALS_BASE_PATH = "tutorials";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	        + "/" + TUTORIALS_BASE_PATH);
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	        + "/mt-tutorial";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	        + "/mt-tutorial";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(
	        UriMatcher.NO_MATCH);
	static {
	    sURIMatcher.addURI(AUTHORITY, TUTORIALS_BASE_PATH, TUTORIALS);
	    sURIMatcher.addURI(AUTHORITY, TUTORIALS_BASE_PATH + "/*", TUTORIAL_ID);
	}
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mDB = new TutListDatabase(getContext());
		ContentValues values = new ContentValues();
	    values.put(TutListDatabase.COL_TITLE, "assam");
	    values.put(TutListDatabase.COL_URL, "http://www.google.com");
	    insert(CONTENT_URI, values);	    
		return true;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = mDB.getWritableDatabase();
	    int rowsAffected = 0;
	    switch (uriType) {
	    case TUTORIALS:
	        rowsAffected = sqlDB.delete(TutListDatabase.TABLE_TUTORIALS,
	                selection, selectionArgs);
	        break;
	    case TUTORIAL_ID:
	        String id = uri.getLastPathSegment();
	        if (TextUtils.isEmpty(selection)) {
	            rowsAffected = sqlDB.delete(TutListDatabase.TABLE_TUTORIALS,
	                    TutListDatabase.ID + "=" + id, null);
	        } else {
	            rowsAffected = sqlDB.delete(TutListDatabase.TABLE_TUTORIALS,
	                    selection + " and " + TutListDatabase.ID + "=" + id,
	                    selectionArgs);
	        }
	        break;
	    default:
	        throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsAffected;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		int uriType = sURIMatcher.match(arg0);
	    SQLiteDatabase sqlDB = mDB.getWritableDatabase();
	    int rowsDeleted = 0;
	    long id = 0;
	    switch (uriType) {
	    case TUTORIALS:
	      id = sqlDB.insert(TutListDatabase.TABLE_TUTORIALS, null, arg1);
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + arg0);
	    }
	    getContext().getContentResolver().notifyChange(arg0, null);
	    //Log.d("Insertion","Succesful");
	    return Uri.parse(TUTORIALS_BASE_PATH + "/" + id);
	}	

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
	    queryBuilder.setTables(TutListDatabase.TABLE_TUTORIALS);
	    int uriType = sURIMatcher.match(uri);
	    switch (uriType) {
	    case TUTORIAL_ID:
	        queryBuilder.appendWhere(TutListDatabase.ID + "="
	                + uri.getLastPathSegment());
	        break;
	    case TUTORIALS:
	        // no filter
	        break;
	    default:
	        throw new IllegalArgumentException("Unknown URI");
	    }
	    Cursor cursor = queryBuilder.query(mDB.getReadableDatabase(),
	            projection, selection, selectionArgs, null, null, sortOrder);
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);
	    return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
