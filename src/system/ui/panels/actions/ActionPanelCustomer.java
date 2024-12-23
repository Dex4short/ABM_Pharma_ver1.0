package system.ui.panels.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.TextField;
import components.panels.ActionPanel;
import components.panels.Panel;
import oop.Customer;
import oop.enums.CustomerState;
import system.ui.Window;

public abstract class ActionPanelCustomer extends ActionPanel{
	private static final long serialVersionUID = -3251443593560045012L;
	private TextField txt_field[];
	private Customer customer;

	public ActionPanelCustomer() {
		super("Customer");
		
		getPanelBody().setLayout(new BorderLayout(10, 20));
		getPanelBody().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		Label labels[] = {
			new Label("Customer Name:"),
			new Label("Address:"),
			new Label("Contact Number:"),
			new Label("E-mail:"),
			new Label("Company:"),
		};
		int rows = labels.length;
		txt_field = new TextField[rows];
		
		
		Panel left_panel = new Panel();
		Panel center_panel = new Panel();
		
		left_panel.setLayout(new GridLayout(rows, 1));
		center_panel.setLayout(new GridLayout(rows, 1));
		
		getPanelBody().add(center_panel, BorderLayout.CENTER);
		getPanelBody().add(left_panel, BorderLayout.WEST);
		
		for(int r=0; r<rows; r++) {
			left_panel.add(labels[r]);
			center_panel.add(txt_field[r] = new TextField());
		}
		
	}
	@Override
	public void onOk() {
		try {
			customerOk();
			Window.getStackPanel().popPanel(this);
		} catch (Exception e) {
			Window.floatMessageAndBeep(e.getMessage());
		}
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void setCustomer(Customer customer) {
		txt_field[0].setText(customer.getCustomerName());
		txt_field[1].setText(customer.getAddress());
		txt_field[2].setText(customer.getContactNo());
		txt_field[3].setText(customer.getEmail());
		txt_field[4].setText(customer.getCompany());
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void customerOk() {
		String 
		str[] = {
			txt_field[0].getText(),
			txt_field[1].getText(),
			txt_field[2].getText(),
			txt_field[3].getText(), 
			txt_field[4].getText()
		},
		field[] = {"name", "address", "contact no.", "e-mail", "company name"};
		
		for(int s=0; s<str.length; s++) {
			if(str[s].isEmpty()) {
				throw new RuntimeException("Please input customer " + field[s]);
			}
			else if(str[s].length() > 64) {
				throw new RuntimeException("Input limit is 64 characters only");
			}
		}
		
		onCustomerOk(new Customer(
			getCustomer().getCustomerId(),
			str[0], 
			str[1], 
			str[2], 
			str[3], 
			str[4], 
			getCustomer().getCustomerState()
		));
	}
	
	public abstract void onCustomerOk(Customer customer);
	
}
