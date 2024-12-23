package system.ui.buttons.customer_listing;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Button;
import oop.Customer;
import res.Resource;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelCustomerEdit;

public abstract class ButtonEditCustomer extends Button{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("pencil.png")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelCustomerEdit(getSelectedCustomer()) {
			private static final long serialVersionUID = 9033808107673720043L;
			@Override
			public void onCustomerOk(Customer customer) { editCustomerOk(customer);}
		}, 400, 300);
	}
	public void editCustomerOk(Customer customer) { onEditCustomerOk(customer); }
	
	public abstract void onEditCustomerOk(Customer customer);
	public abstract Customer getSelectedCustomer();

}
