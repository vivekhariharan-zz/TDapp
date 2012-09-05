package com.vh.tdapp.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TasksDbAdapter {
	
	private TasksDbHelper helper;
	private int databaseVersion = 1;
	private String databaseName = "tasks_db";
	private SQLiteDatabase database;
	
	
	Context context;
	public TasksDbAdapter(Context context)
	{
		this.context = context;
		
	}
	
	public TasksDbAdapter open() throws SQLException
	{
		helper = new TasksDbHelper(context, databaseName, databaseVersion);
		database = helper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		helper.close();
	}
	
	public void addTask()
	{
		
	}
	
	public void modifyTask()
	{
		
	}
	
	public void deleteTask()
	{
		
	}
	
	public void updateTask()
	{
		
	}

}
