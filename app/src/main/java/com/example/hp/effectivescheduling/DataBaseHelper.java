package com.example.hp.effectivescheduling;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_NOTE = "NOTE";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_CONTENT = "CONTENT";
	private static final String TAG ="EditDataActivity";

    //for reminder
	public static final String TABLE_REMINDER = "REMINDER";
//	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_TIME = "TIME";
	public static final String COLUMN_TITLE = "TITLE";


	private static final String DATABASE_NAME = "effectivescheduling.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " +TABLE_NOTE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
			COLUMN_CONTENT+" TEXT)";

    //for reminder
	private static final String DATABASE_CREATE_reminder = "create table " +TABLE_REMINDER +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,TIME TEXT,TITLE TEXT)";


	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	//	SQLiteDatabase db =this.getWritableDatabase ();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE_reminder);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTE);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_REMINDER);
		onCreate(db);
		
	}
	public boolean insertData(String content){
		SQLiteDatabase db=this.getWritableDatabase ();
		ContentValues contentValues =new ContentValues ();
		contentValues.put(COLUMN_CONTENT,content);
		long result = db.insert (TABLE_NOTE,null,contentValues);
		if(result == -1)
			return false;
		else
			return true;

	}
	public Cursor getAllData(){
		SQLiteDatabase db=this.getWritableDatabase ();
		Cursor res = db.rawQuery ("select * from "+TABLE_NOTE,null);
		return res;
	}
	public Cursor getItemID(String content){
		SQLiteDatabase db=this.getWritableDatabase ();
		String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NOTE +
				" WHERE " + COLUMN_CONTENT + " = '" + content + "'";

		Cursor data = db.rawQuery (query,null);
		return  data;
	}
	public void update(String newContent,int id,String oldContent) {
		SQLiteDatabase db = this.getWritableDatabase ();
		String query = "UPDATE " + TABLE_NOTE + " SET " + COLUMN_CONTENT +
				" = '" + newContent + "' WHERE " + COLUMN_ID + " = '" + id + "'" +
				" AND " + COLUMN_CONTENT + " = '" + oldContent + "'";
		Log.d(TAG, "updateName: query: " + query);
		Log.d(TAG, "updateName: Setting name to " + newContent);


		db.execSQL (query);
	}
	public void delete(int id, String content){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "DELETE FROM " + TABLE_NOTE + " WHERE "
				+ COLUMN_ID + " = '" + id + "'" +
				" AND " + COLUMN_CONTENT + " = '" + content + "'";
		Log.d(TAG, "deleteName: query: " + query);
		Log.d(TAG, "deleteName: Deleting " + content + " from database.");
		db.execSQL(query);
	}

	public boolean insertData(String time,String title){
		SQLiteDatabase db=this.getWritableDatabase ();
		ContentValues contentValues =new ContentValues ();
		contentValues.put(COLUMN_TIME,time);
		contentValues.put(COLUMN_TITLE,title);

		long result = db.insert (TABLE_REMINDER,null,contentValues);
		if(result == -1)
			return false;
		else
			return true;

	}

	public Cursor getTime()
	{
		SQLiteDatabase db=this.getWritableDatabase ();
		Cursor res = db.rawQuery ("select TIME from "+TABLE_REMINDER,null);
		final int count=res.getCount();


		return res;
	}
	public Cursor getTittle()
	{
		SQLiteDatabase db=this.getWritableDatabase ();
		Cursor res = db.rawQuery ("select TITLE,TIME from " + TABLE_REMINDER,null);
		return res;
	}

	public Cursor getTittleByTime(String time)
	{
		Cursor res=null;
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			//res = db.rawQuery("select TITLE from " + TABLE_REMINDER + " where TIME = " + time, null);

			//String query = "select TITLE,TIME from " + TABLE_REMINDER + " where TIME = " + time + ";";

			res = db.query(TABLE_REMINDER, new String[]{"TITLE", "TIME"},
					"TIME=?",
					new String[]{String.valueOf(time)}, null, null, null, null);


		}
		catch (Exception e)
		{

		}
		return res;
	}


}