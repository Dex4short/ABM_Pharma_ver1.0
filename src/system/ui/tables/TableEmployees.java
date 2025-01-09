package system.ui.tables;

import components.table.Table;

public abstract class TableEmployees extends Table{
	private static final long serialVersionUID = 5910798367373357936L;
	public static final String fields[] = {"Employee Name", "Address", "Contact Number", "E-mail"};
	
	public TableEmployees() {
		super(fields);
	}
}
