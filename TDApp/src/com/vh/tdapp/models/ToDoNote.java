package com.vh.tdapp.models;

import java.util.Date;

public class ToDoNote {
	
	String note;
	int priority;
	Date dueDate;
	
	public ToDoNote(String note, int priority, Date dueDate) {
		super();
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
