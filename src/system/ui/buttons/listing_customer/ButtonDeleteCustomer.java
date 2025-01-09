package system.ui.buttons.listing_customer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import components.panels.DialogPanel;
import res.Resource;
import system.objects.Customer;
import system.ui.Window;

public abstract class ButtonDeleteCustomer extends Button implements ActionListener{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDeleteCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("delete.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogPanel("Delete Customer") {
			private static final long serialVersionUID = -5908193837480508966L;
			{
				setText("Delete selected customer(s)?");
			}
			@Override
			public void onOk() {
				deleteCustomer();
				Window.getStackPanel().popPanel(this);
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	public void deleteCustomer() {
		Window.load(() -> {
			for(Customer customer: getSelectedCustomers()) {
				onDeleteCustomer(customer);
			}
		}, "Deleting From the list...");
	}
	
	public abstract void onDeleteCustomer(Customer customer);
	public abstract Customer[] getSelectedCustomers();
	
}
