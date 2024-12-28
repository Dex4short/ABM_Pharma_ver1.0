package system.ui.panels.actions.catering;

import system.objects.Customer;

public abstract class ActionPanelCustomerEdit extends ActionPanelCustomer{
	private static final long serialVersionUID = 8600068670153503408L;
	
	public ActionPanelCustomerEdit(Customer customer) {
		super(
			customer.getCustomerName(),
			customer.getAddress(),
			customer.getContactNo(),
			customer.getEmail(),
			customer.getCompany()	
		);
		setTitle("Edit Customer");
	}
}
