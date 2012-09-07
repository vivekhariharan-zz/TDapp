package com.vh.tdapp.db;

import com.vh.tdapp.db.schemas.TaskSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksDbHelper extends SQLiteOpenHelper {
	
	String databaseName;
	public TasksDbHelper(Context context, String databaseName, int databaseVersion) {
		super(context, databaseName, null, databaseVersion);
		this.databaseName = databaseName;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TaskSchema.TABLE_CREATE);	
F
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("Drop table if exists "+databaseName);
		onCreate(db);

	}

}
