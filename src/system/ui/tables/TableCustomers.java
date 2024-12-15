package system.ui.tables;

import components.table.Table;

public class TableCustomers extends Table{
	private static final long serialVersionUID = 5910798367373357936L;
	public static final String fields[] = {"Customer Name", "Address", "Contact Number", "E-mail", "Company"};
	
	public TableCustomers() {
		super(fields);
	}
}
