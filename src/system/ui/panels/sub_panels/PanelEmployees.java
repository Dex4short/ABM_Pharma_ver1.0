package system.ui.panels.sub_panels;

import components.Button;
import components.table.Row;
import system._default_.Employees;
import system.ui.UI1;
import system.ui.buttons.listing_employee.ButtonAddEmployee;
import system.ui.buttons.listing_employee.ButtonDeleteEmployee;
import system.ui.buttons.listing_employee.ButtonEditEmployee;
import system.ui.panels.searches.SearchPanelEmployees;
import system.ui.tables.TableEmployees;

public class PanelEmployees extends UI1 implements Employees{
	private static final long serialVersionUID = -2756977805365927787L;
	private TableEmployees table_employees;
	private Button btn_delete_employee, btn_edit_employee;
		
	public PanelEmployees() {
		SearchPanelEmployees search_panel_customers = new SearchPanelEmployees() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSearchEmployee(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel_customers);

		btn_delete_employee = new ButtonDeleteEmployee();
		addButton(btn_delete_employee);

		btn_edit_employee = new ButtonEditEmployee();
		addButton(btn_edit_employee);
		
		ButtonAddEmployee btn_add_employee = new ButtonAddEmployee();
		addButton(btn_add_employee);
		
		table_employees = new TableEmployees() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSelectRow(Row row) {}
			@Override
			public void onPointRow(Row row) {
				search_panel_customers.closeSearchFilter();
			}
		};
		setTable(table_employees);
	}

}
