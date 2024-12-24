package system.ui.panels.actions;

import java.awt.BorderLayout;

import components.panels.ActionPanel;
import oop.Customer;
import system.ui.Window;
import system.ui.panels.PanelCustomers;
import system.ui.tables.TableCustomers;

public abstract class ActionPanelCustomerList extends ActionPanel{
	private static final long serialVersionUID = 5936784197775829338L;
	private TableCustomers table_customers;
	
	public ActionPanelCustomerList() {
		super("Customer List");
		
		getPanelBody().setLayout(new BorderLayout());
		
		PanelCustomers panel_customers = new PanelCustomers();
		getPanelBody().add(panel_customers, BorderLayout.CENTER);
		panel_customers.getButton(0).setVisible(false);
		panel_customers.getButton(1).setVisible(false);
		panel_customers.getButton(2).setVisible(false);
		panel_customers.loadAllFromCustomers();
		
		setTableCustomers((TableCustomers)panel_customers.getTable());
		getTableCustomers().setCheckBoxesEnabled(false);
	}
	@Override
	public void onOk() {
		try {
			customerListOk();
			Window.getStackPanel().popPanel();
		} catch (Exception e) {
			Window.floatMessageAndBeep(e.getMessage());
		}
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public TableCustomers getTableCustomers() {
		return table_customers;
	}
	public void setTableCustomers(TableCustomers table_customers) {
		this.table_customers = table_customers;
	}
	public void customerListOk() {
		Customer customer =  getTableCustomers().getSelectedCustomer();
		
		if(customer != null) {
			onCustomerListOk(customer);
		}
		else throw new RuntimeException("Plese select from customer list");
	}
	
	public abstract void onCustomerListOk(Customer customer);
	
}
