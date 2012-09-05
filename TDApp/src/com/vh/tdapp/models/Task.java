package com.vh.tdapp.models;

import java.util.Date;

public class Task {

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	String note;
	int priority;
	Date dueDate;

	public Task(int id, String note, int priority, Date dueDate) {
		super();
		this.id = id;
		this.note = note;
		this.priority = priority;
		this.dueDate = dueDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
