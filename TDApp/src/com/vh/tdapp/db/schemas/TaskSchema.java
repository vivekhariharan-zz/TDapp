package com.vh.tdapp.db.schemas;

public abstract class TaskSchema {

	public static String NAME = "task_schema";

	public static final String ID = "id";

	public static final String TITLE = "title";

	public static final String PRIORITY = "priority";

	public static final String DUE_DATE = "due_date";

	public static final String DESCRIPTION = "description";

	public static final String TABLE_CREATE = "create table " + NAME + " ( "
			+ ID + " integer primary key autoincrement, " + TITLE
			+ " text not null, " + DESCRIPTION + " text, " + PRIORITY
			+ " integer not null," + DUE_DATE + " date)";
}
