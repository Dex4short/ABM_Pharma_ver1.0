package system.ui.buttons.picking;

import java.awt.event.MouseEvent;

import components.Button;
import oop.Customer;
import res.Resource;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelCustomerList;

public abstract class ButtonCustomerPicker extends Button{
	private static final long serialVersionUID = -7885493071011496579L;

	public ButtonCustomerPicker() {
		super(Resource.getAsImageIcon("customers.png"));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelCustomerList() {
			private static final long serialVersionUID = 732374242146259948L;
			@Override
			public void onCustomerListOk(Customer customer) {
				customerOk(customer);
			}
		}, 750, 400);
	}
	public void customerOk(Customer customer) {
		onCustomerOk(customer);
	}
	
	public abstract void onCustomerOk(Customer customer);
	
}
