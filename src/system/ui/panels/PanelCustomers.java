package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import components.table.Row;
import system.ui.UI1;
import system.ui.buttons.customer_listing.ButtonAddCustomer;
import system.ui.buttons.customer_listing.ButtonDeleteCustomer;
import system.ui.buttons.customer_listing.ButtonEditCustomer;
import system.ui.panels.searches.SearchPanelCustomers;
import system.ui.tables.TableCustomers;

public class PanelCustomers extends UI1{
	private static final long serialVersionUID = -2756977805365927787L;
		
	public PanelCustomers() {
		SearchPanelCustomers search_panel_customers = new SearchPanelCustomers() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel_customers);

		ButtonDeleteCustomer btn_delete_customer = new ButtonDeleteCustomer();
		btn_delete_customer.setEnabled(false);
		addButton(btn_delete_customer);

		ButtonEditCustomer btn_edit_customer = new ButtonEditCustomer();
		btn_edit_customer.setEnabled(false);
		addButton(btn_edit_customer);
		
		ButtonAddCustomer btn_add_customer = new ButtonAddCustomer();
		addButton(btn_add_customer);
		
		TableCustomers table_customers = new TableCustomers() {
			private static final long serialVersionUID = 1L;
			@Override
			public void addRow(Row row) {
				row.addMouseListener(closeSearchFilterAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
					}
				});
				super.addRow(row);
			}
		};
		setTable(table_customers);
	}
}
