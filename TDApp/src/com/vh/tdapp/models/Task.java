package com.vh.tdapp.models;

import java.util.Date;

public class Task {

	int id;
	String description;
	String taskTitle;
	int priority;
	Date dueDate;

	public Task(int id, String description, String taskTitle, int priority,
			Date dueDate) {
		super();
		this.id = id;
		this.description = description;
		this.taskTitle = taskTitle;
		this.priority = priority;
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
