package com.vh.tdapp.db.schemas;

public abstract class ToDoItemSchema {

	public static String NAME = "todo_item_schema";

	private static final String ID = "id";

	private static final String TITLE = "title";

	private static final String PRIORITY = "priority";

	private static final String DUE_DATE = "due_date";

	private static String[] COLUMNS = { ID, TITLE, PRIORITY, DUE_DATE };

	public static final String TABLE_CREATE = "create table " + NAME + " ( "
			+ ID + " integer primary key autoincrement, " + TITLE + " text not null, " + PRIORITY + " integer not null,"
			+ DUE_DATE + " date)";

}
