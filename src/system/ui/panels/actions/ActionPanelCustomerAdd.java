package system.ui.panels.actions;

import oop.Customer;
import oop.enums.CustomerState;

public abstract class ActionPanelCustomerAdd extends ActionPanelCustomer{
	private static final long serialVersionUID = 8600068670153503408L;
	
	public ActionPanelCustomerAdd() {
		setTitle("Add Customer");
		
		Customer customer = new Customer();
		customer.setCustomerState(CustomerState.Listed);
		setCustomer(customer);
	}
}
