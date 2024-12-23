package system.ui.panels.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.TextField;
import components.panels.ActionPanel;
import components.panels.Panel;
import system.ui.Window;

public abstract class ActionPanelCustomer extends ActionPanel{
	private static final long serialVersionUID = -3251443593560045012L;
	private TextField txt_field[];
	
	public ActionPanelCustomer(String customer_name, String address, String contact_no, String e_mail, String company) {
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
		
		setFieldValues(customer_name, address, contact_no, e_mail, company);
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
	public void setFieldValues(String customer_name, String address, String contact_no, String e_mail, String company) {
		txt_field[0].setText(customer_name);
		txt_field[1].setText(address);
		txt_field[2].setText(contact_no);
		txt_field[3].setText(e_mail);
		txt_field[4].setText(company);
	}
	public String[] getFieldValues() {
		return new String[] {
			txt_field[0].getText(),
			txt_field[1].getText(),
			txt_field[2].getText(),
			txt_field[3].getText(), 
			txt_field[4].getText()
		};
	}
	public void customerOk() {
		String 
		str[] = getFieldValues(),
		field[] = {"name", "address", "contact no.", "e-mail", "company name"};
		
		for(int s=0; s<str.length; s++) {
			if(str[s].isEmpty()) {
				throw new RuntimeException("Please input customer " + field[s]);
			}
			else if(str[s].length() > 64) {
				throw new RuntimeException("Input limit is 64 characters only");
			}
		}
		
		onCustomerOk(str[0], str[1], str[2], str[3], str[4]);
	}
	
	public abstract void onCustomerOk(String customer_name, String address, String contact_no, String e_mail, String company);
	
}