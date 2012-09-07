package com.vh.tdapp.db;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.vh.tdapp.db.schemas.TaskSchema;
import com.vh.tdapp.models.Task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TasksDbAdapter {

	String LOG_TAG = "TASK_APP_DATABASE_ADAPTER";

	private TasksDbHelper helper;
	private int databaseVersion = 1;
	private String databaseName = "tasks_db";
	private SQLiteDatabase database;

	Context context;

	public TasksDbAdapter(Context context) {
		this.context = context;

	}

	public TasksDbAdapter open() throws SQLException {
		helper = new TasksDbHelper(context, databaseName, databaseVersion);
		database = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		helper.close();
	}

	public void addTask(Task newTask) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ContentValues rowValues = new ContentValues();
		// rowValues.put(TaskSchema.ID, newTask.getId());
		rowValues.put(TaskSchema.TITLE, newTask.getTaskTitle());
		rowValues.put(TaskSchema.DESCRIPTION, newTask.getDescription());
		rowValues.put(TaskSchema.PRIORITY, newTask.getPriority());
		rowValues.put(TaskSchema.DUE_DATE,
				dateFormat.format(newTask.getDueDate()));

		long returnValue = database.insert(TaskSchema.NAME, null, rowValues);
		if (returnValue == -1) {
			Log.d(LOG_TAG, "error inserting into table");
		}
	}

	public void modifyTask(Task modifiedTask) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ContentValues rowValues = new ContentValues();
		// rowValues.put(TaskSchema.ID, newTask.getId());
		rowValues.put(TaskSchema.TITLE, modifiedTask.getTaskTitle());
		rowValues.put(TaskSchema.DESCRIPTION, modifiedTask.getDescription());
		rowValues.put(TaskSchema.PRIORITY, modifiedTask.getPriority());
		rowValues.put(TaskSchema.DUE_DATE,
				dateFormat.format(modifiedTask.getDueDate()));

		database.update(TaskSchema.NAME, rowValues, TaskSchema.ID + " = "
				+ modifiedTask.getId(), null);

	}

	public void deleteTask(Task taskToDelete) {

		database.delete(TaskSchema.NAME,
				TaskSchema.ID + " = " + taskToDelete.getId(), null);

	}

	public ArrayList<Task> getAllTasks() {
		Cursor returnedData;
		ArrayList<Task> listOfTasks = new ArrayList<Task>();
		returnedData = database.query(TaskSchema.NAME, new String[] {
				TaskSchema.ID, TaskSchema.TITLE, TaskSchema.DESCRIPTION,
				TaskSchema.PRIORITY, TaskSchema.DUE_DATE }, null, null, null,
				null, null, null);

		while (returnedData.moveToNext()) {
			Task currentTask;
			int id = returnedData.getInt(returnedData
					.getColumnIndexOrThrow(TaskSchema.ID));
			String dueDateStr = returnedData.getString(returnedData
					.getColumnIndexOrThrow(TaskSchema.DUE_DATE));
			Date dueDate = null;
			try {
				dueDate = (Date) DateFormat.getInstance().parse(dueDateStr);
			} catch (ParseException e) {
				Log.d(LOG_TAG, "Error: problem with parsing date");
				e.printStackTrace();
			}
			int priority = returnedData.getInt(returnedData
					.getColumnIndexOrThrow(TaskSchema.PRIORITY));
			String taskTitle = returnedData.getString(returnedData
					.getColumnIndexOrThrow(TaskSchema.TITLE));
			String taskDescription = returnedData.getString(returnedData
					.getColumnIndexOrThrow(TaskSchema.DESCRIPTION));
			currentTask = new Task(id, taskDescription, taskTitle, priority,
					dueDate);
			listOfTasks.add(currentTask);
		}

		return listOfTasks;
	}

}
