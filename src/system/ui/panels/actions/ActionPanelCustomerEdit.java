package system.ui.panels.actions;

import oop.Customer;

public abstract class ActionPanelCustomerEdit extends ActionPanelCustomer{
	private static final long serialVersionUID = 8600068670153503408L;
	
	public ActionPanelCustomerEdit(Customer customer) {
		setTitle("Edit Customer");
		setCustomer(customer);
	}
}
