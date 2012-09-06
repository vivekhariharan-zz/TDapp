package com.vh.tdapp.activities;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;


import android.app.Activity;
import android.os.Bundle;

public class ShowTasksActivity extends Activity{
	
	TasksDbAdapter tasksAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tasksAdapter = new TasksDbAdapter(this);
		setContentView(R.layout.show_tasks_layout);
	}

}
