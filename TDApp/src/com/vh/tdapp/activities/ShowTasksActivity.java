package com.vh.tdapp.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;
import com.vh.tdapp.models.Task;

public class ShowTasksActivity extends ListActivity {

	TasksDbAdapter tasksAdapter;
	ArrayList<Task> tasks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tasksAdapter = new TasksDbAdapter(this);
		tasksAdapter.open();
		setContentView(R.layout.show_tasks_layout);
		tasks = new ArrayList<Task>();
		tasks = tasksAdapter.getAllTasks();
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

		Button sortByDateButton = (Button) findViewById(R.id.sort_by_date_button);

		sortByDateButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tasks = tasksAdapter.getAllTasksByDate();
				fillData();

			}
		});

		Button sortByPriorityButton = (Button) findViewById(R.id.sort_by_priority_button);

		sortByPriorityButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				tasks = tasksAdapter.getAllTasksByPriority();
				fillData();
			}
		});

	}

	private void fillData() {

		Log.d("in show tasks activity", tasks.size() + "");

		setListAdapter(new TaskArrayAdapter(this, tasks));

	}

	private void deleteTask(int position) {
		Log.d("task id", position + "");
		Task taskToDelete = tasks.get(position);
		tasksAdapter.deleteTask(taskToDelete);
		fillData();
	}

	private void modifyTask(int position) {
		Log.d("task id", position + "");
		Task taskToModify = tasks.get(position);
		// need to call edit task
		tasksAdapter.modifyTask(taskToModify);
	}

	@Override
	protected void onResume() {
		super.onResume();
		tasksAdapter = new TasksDbAdapter(this);
		tasksAdapter.open();
		tasks = new ArrayList<Task>();
		tasks = tasksAdapter.getAllTasks();
		fillData();
	}

	class TaskArrayAdapter extends ArrayAdapter<Task> {
		private List<Task> tasks;
		private Context context;

		public TaskArrayAdapter(Context context, List<Task> tasks) {
			super(context, R.layout.show_task_row, tasks);
			this.tasks = tasks;
			this.context = context;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final int pos = position;
			View rowView = convertView;
			if (rowView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView = inflater.inflate(R.layout.show_task_row, parent,
						false);

				Task currentTask = tasks.get(position);

				CheckBox isCompleteCheckBox = (CheckBox) rowView
						.findViewById(R.id.is_complete);

				TextView taskTitle = (TextView) rowView
						.findViewById(R.id.title_text);
				taskTitle.setText(currentTask.getTaskTitle());

				TextView priorityText = (TextView) rowView
						.findViewById(R.id.priority);
				priorityText.setText(currentTask.getPriority() + "");

				TextView daysLeftText = (TextView) rowView
						.findViewById(R.id.days_left);
				Date now = Calendar.getInstance().getTime();
				// int days = Days.daysBetween(new
				// DateTime(currentTask.getDueDate()), new
				// DateTime(now)).getDays();
				int days = 0;
				daysLeftText.setText(days + "");
			} else {

				rowView = convertView;
			}
			rowView.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Log.d("v debuggin", "item clicked" + pos);
					AlertDialog.Builder dialogBuilder = new Builder(
							ShowTasksActivity.this);
					dialogBuilder.setMessage("Here are your options: ");
					dialogBuilder.setPositiveButton("Modify Task",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									modifyTask(pos);
									dialog.dismiss();

								}
							});
					dialogBuilder.setNegativeButton("Delete Task",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									deleteTask(pos);
									dialog.dismiss();

								}
							});
					dialogBuilder.setNeutralButton("Cancel",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();

								}
							});

					dialogBuilder.show();

				}
			});

			return rowView;
		}
	}
}
