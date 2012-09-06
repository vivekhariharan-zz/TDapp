package com.vh.tdapp.activities;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowTasksActivity extends Activity{
	
	TasksDbAdapter tasksAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tasksAdapter = new TasksDbAdapter(this);
		setContentView(R.layout.show_tasks_layout);
		
		Button addTaskButton = (Button)findViewById(R.id.add_task_button);
		
		addTaskButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent addTaskIntent = new Intent(ShowTasksActivity.this, AddTaskActivity.class);	
				startActivity(addTaskIntent);
				
			}
		});
	}

}
