package com.vh.tdapp.activities;

import java.util.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.vh.tdapp.R;
import com.vh.tdapp.db.TasksDbAdapter;
import com.vh.tdapp.models.Task;
import com.vh.tdapp.ui.NumberPicker;

public class AddTaskActivity extends Activity {

	TasksDbAdapter taskDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_task_layout);

		TextView headText = (TextView) findViewById(R.id.title_text);
		headText.setText("Add a new Task");

		final EditText taskTitle = (EditText) findViewById(R.id.task_title_text);

		final EditText taskDescription = (EditText) findViewById(R.id.task_description_text);

		final NumberPicker priorityPicker = (NumberPicker) findViewById(R.id.task_priority_value);

		final DatePicker dueDatePicker = (DatePicker) findViewById(R.id.set_due_date_picker);

		Calendar c = Calendar.getInstance();

		Date currentDateTime = c.getTime();

		//dueDatePicker.setMinDate(currentDateTime.getTime());

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

				if (taskTitle.getText().toString().trim() == "") {
					builder.show();
				} else {
					@SuppressWarnings("deprecation")
					Date dueDate = new Date(dueDatePicker.getYear(),
							dueDatePicker.getMonth(), dueDatePicker
									.getDayOfMonth());
					Task newTask = new Task(taskDescription.getText()
							.toString(), taskTitle.getText().toString(),
							priorityPicker.getCurrent(), dueDate);
					taskDatabase.addTask(newTask);
					finish();
				}
			}
		});

		Button cancelButton = (Button) findViewById(R.id.cancel_button);

		cancelButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
	}

}