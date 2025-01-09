package system.ui.buttons.listing_customer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.enumerators.CustomerState;
import system.objects.Customer;
import system.ui.Window;
import system.ui.panels.actions.catering.ActionPanelCustomerEdit;

public abstract class ButtonEditCustomer extends Button implements ActionListener{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("pencil.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelCustomerEdit(getSelectedCustomer()) {
			private static final long serialVersionUID = 9033808107673720043L;

			@Override
			public void onCustomerOk(String customer_name, String address, String contact_no, String e_mail, String company) {
				 editCustomerOk(new Customer(-1, customer_name, address, contact_no, e_mail, company, CustomerState.Listed));
			}
		}, 400, 300);
	}
	public void editCustomerOk(Customer customer) { onEditCustomerOk(customer); }
	
	public abstract void onEditCustomerOk(Customer customer);
	public abstract Customer getSelectedCustomer();

}
