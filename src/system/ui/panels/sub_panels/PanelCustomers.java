package system.ui.panels.sub_panels;

import components.Button;
import components.table.Row;
import system._default_.Customers;
import system.objects.Customer;
import system.ui.UI1;
import system.ui.Window;
import system.ui.buttons.customer_listing.ButtonAddCustomer;
import system.ui.buttons.customer_listing.ButtonDeleteCustomer;
import system.ui.buttons.customer_listing.ButtonEditCustomer;
import system.ui.panels.searches.SearchPanelCustomers;
import system.ui.tables.TableCustomers;

public class PanelCustomers extends UI1 implements Customers{
	private static final long serialVersionUID = -2756977805365927787L;
	private TableCustomers table_customers;
	private Button btn_delete_customer, btn_edit_customer;
		
	public PanelCustomers() {
		SearchPanelCustomers search_panel_customers = new SearchPanelCustomers() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel_customers);

		btn_delete_customer = new ButtonDeleteCustomer() {
			private static final long serialVersionUID = -9013302516856194929L;
			@Override
			public void onDeleteCustomer(Customer customer) { deleteFromCustomers(customer);}
			@Override
			public Customer[] getSelectedCustomers() { return selectMoreFromCustomers(); }
			
		};
		addButton(btn_delete_customer);

		btn_edit_customer = new ButtonEditCustomer() {
			private static final long serialVersionUID = -597842006912315272L;
			@Override
			public void onEditCustomerOk(Customer customer) { editFromCustomers(customer);}
			@Override
			public Customer getSelectedCustomer() { return selectFromCustomers(); }
		};
		addButton(btn_edit_customer);
		
		ButtonAddCustomer btn_add_customer = new ButtonAddCustomer() {
			private static final long serialVersionUID = 2321823320064198028L;
			@Override
			public void onAddCustomerOk(Customer customer) { addFromCustomers(customer); }
			
		};
		addButton(btn_add_customer);
		
		table_customers = new TableCustomers() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSelectRow(Row row) {
				int selected_rows = getSelectedRows().length;
				btn_delete_customer.setEnabled(selected_rows == 1);
				btn_edit_customer.setEnabled(selected_rows > 0);
			}
			@Override
			public void onPointRow(Row row) {
				search_panel_customers.closeSearchFilter();
			}
		};
		setTable(table_customers);
	}

	@Override
	public Customer onSelectFromCustomers() {
		return table_customers.getSelectedCustomer();
	}
	@Override
	public Customer[] onSelectMoreFromCustomers() {
		return table_customers.getSelectedCustomers();
	}
	@Override
	public void onAddFromCustomers(Customer customer) {
		table_customers.addCustomer(customer);
		Window.floatMessage("New customer added (" + customer.getCustomerName() + ")");
	}
	@Override
	public void onEditFromCustomers(Customer customer) {
		loadAllFromCustomers();
		Window.floatMessage("Customer updated (" + customer.getCustomerName() + ")");
	}
	@Override
	public void onDeleteFromCustomers(Customer customer) {
		table_customers.removeCustomer(customer);
	}
	@Override
	public void onLoadAllFromCustomers(Customer[] customers) {
		btn_delete_customer.setEnabled(false);
		btn_edit_customer.setEnabled(false);
		
		table_customers.removeAllCustomers();
		table_customers.addCustomers(customers);
	}

}
