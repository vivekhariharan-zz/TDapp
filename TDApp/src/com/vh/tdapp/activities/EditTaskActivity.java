package com.vh.tdapp.activities;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;
import com.vh.tdapp.models.Task;
import com.vh.tdapp.ui.NumberPicker;

public class EditTaskActivity extends Activity {

	Task taskToModify;
	TasksDbAdapter taskDbAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_task_layout);
		int taskId = Integer.parseInt(getIntent().getStringExtra("taskId"));
		taskDbAdapter = new TasksDbAdapter(this);
		taskDbAdapter.open();
		taskToModify = taskDbAdapter.getTaskById(taskId);
						
		TextView headText = (TextView) findViewById(R.id.title_text);
		headText.setText("Modify Task");

		final EditText taskTitle = (EditText) findViewById(R.id.task_title_text);
		taskTitle.setText(taskToModify.getTaskTitle());

		final EditText taskDescription = (EditText) findViewById(R.id.task_description_text);
		taskDescription.setText(taskToModify.getDescription());
		
		final NumberPicker priorityPicker = (NumberPicker) findViewById(R.id.task_priority_value);
		priorityPicker.setCurrent(taskToModify.getPriority());
		
		final DatePicker dueDatePicker = (DatePicker) findViewById(R.id.set_due_date_picker);
		
		Button cancelButton = (Button) findViewById(R.id.cancel_button);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				taskDbAdapter.close();
				finish();
			}
		});
		
		
		final AlertDialog.Builder builder = new Builder(this);

		builder.setMessage("All tasks must have a title, please provide the details.");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		Button doneButton = (Button) findViewById(R.id.done_button);

		doneButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (taskTitle.getText().toString().length() <=0) {
					builder.show();
				} else {
					Log.d("vivek debug", ""+dueDatePicker.getYear());
					Date dueDate = new Date();
					dueDate.setYear(dueDatePicker.getYear());
					dueDate.setMonth(dueDatePicker.getMonth());
					dueDate.setDate(dueDatePicker.getDayOfMonth());
					
					taskToModify.setDescription(taskDescription.getText().toString());
					taskToModify.setTaskTitle(taskTitle.getText().toString());
					taskToModify.setPriority(priorityPicker.getCurrent());
					taskToModify.setDueDate(dueDate);
					
					taskDbAdapter.modifyTask(taskToModify);
					taskDbAdapter.close();
					finish();
				}
			}
		});

	}

}
