package com.vh.tdapp.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;
import com.vh.tdapp.models.Task;

public class ShowTasksActivity extends ListActivity {

	TasksDbAdapter tasksAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tasksAdapter = new TasksDbAdapter(this);
		tasksAdapter.open();
		setContentView(R.layout.show_tasks_layout);
		fillData();

		Button addTaskButton = (Button) findViewById(R.id.add_task_button);

		addTaskButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent addTaskIntent = new Intent(ShowTasksActivity.this,
						AddTaskActivity.class);
				tasksAdapter.close();
				startActivity(addTaskIntent);
				

			}
		});

		
	}
	
	private void fillData()
	{
		ArrayList<Task> tasks = new ArrayList<Task>();
		tasks = tasksAdapter.getAllTasks();
		
		Log.d("in show tasks activity", tasks.size()+"");

		setListAdapter(new TaskArrayAdapter(this, tasks));
		
		this.getListView().setLongClickable(true);
			this.getListView().setOnLongClickListener(new OnLongClickListener() {
				
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					return false;
				}
			});
	}
	
	@Override
	protected void onResume() {		
		super.onResume();
		tasksAdapter = new TasksDbAdapter(this);
		tasksAdapter.open();
		fillData();
	}

	class TaskArrayAdapter extends ArrayAdapter<Task> {
		private List<Task> tasks;
		private Context context;

		public TaskArrayAdapter(Context context, List<Task> tasks) {
			super(context, R.layout.show_task_row, tasks	);
			this.tasks = tasks;
			this.context = context;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			if( rowView == null)	
			{
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView = inflater.inflate(R.layout.show_task_row, parent, false);
								
				Task currentTask = tasks.get(position);
				
				CheckBox isCompleteCheckBox = (CheckBox) rowView.findViewById(R.id.is_complete);
							
				
				TextView taskTitle = (TextView) rowView.findViewById(R.id.title_text);			
				taskTitle.setText(currentTask.getTaskTitle());
				
				TextView priorityText = (TextView) rowView.findViewById(R.id.priority);
				priorityText.setText(currentTask.getPriority()+"");
				
				TextView daysLeftText = (TextView) rowView.findViewById(R.id.days_left);
				Date now = Calendar.getInstance().getTime();			
//				int days = Days.daysBetween(new DateTime(currentTask.getDueDate()), new DateTime(now)).getDays();
				int days = 0;
				daysLeftText.setText(days+"");	
			}
			else
			{
				
				rowView = convertView;
			}
			
			
			return rowView;
		}
	}
}
