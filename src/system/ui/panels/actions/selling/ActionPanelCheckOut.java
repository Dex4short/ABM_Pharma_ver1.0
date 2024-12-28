package system.ui.panels.actions.selling;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;

import components.Button;
import components.Label;
import components.fields.TextField;
import components.panels.Panel;
import system.enumerators.CustomerState;
import system.objects.Customer;
import system.objects.Date;
import system.objects.Transaction;
import system.ui.buttons.picking.ButtonCustomerPicker;
import system.ui.panels.actions.catering.ActionPanelCustomer;

public abstract class ActionPanelCheckOut extends ActionPanelCustomer{
	private static final long serialVersionUID = 4491895076388050006L;
	private CheckOutHeading checkOut_heading;
	private Customer customer;
	
	public ActionPanelCheckOut() {
		super("","","","","");
		
		setTitle("Check Out");

		checkOut_heading = new CheckOutHeading();
		getPanelBody().add(checkOut_heading, BorderLayout.NORTH);		
		
		Button btn_ok =	getButtonOk();
		btn_ok.setText("Check Out");
		btn_ok.setPreferredSize(new Dimension(100, 30));
		btn_ok.setMaximumSize(getPreferredSize());
		btn_ok.setMinimumSize(getPreferredSize());
		
		getPanelBody().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
		
		Panel right_panel = new Panel();
		right_panel.setLayout(new FlowLayout());
		getPanelBody().add(right_panel, BorderLayout.EAST);
		
		ButtonCustomerPicker btn_customer_list = new ButtonCustomerPicker() {
			private static final long serialVersionUID = -8516793740797610951L;
			@Override
			public void onCustomerOk(Customer customer) {
				setCustomer(customer);
			}
		};
		right_panel.add(btn_customer_list);
		
	}
	@Override
	public void onCustomerOk(String customer_name, String address, String contact_no, String e_mail, String company) {
		if(getCustomer() == null) setCustomer(new Customer(-1, customer_name, address, contact_no, e_mail, company, CustomerState.Unlisted));
		checkOutOk();
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		setFieldValues(
			customer.getCustomerName(), 
			customer.getAddress(), 
			customer.getContactNo(), 
			customer.getEmail(), 
			customer.getCompany()
		);
		this.customer = customer;
	}
	public void checkOutOk() {
		Transaction transaction = new Transaction(
			-1,
			null, 
			null, 
			getCustomer(),
			getCheckOutHeading().getTinNo(), 
			getCheckOutHeading().getDate(),
			getCheckOutHeading().getTerms(),
			null,
			null, 
			null, 
			null
		);
		
		onCheckoutOk(transaction);
	}
	public CheckOutHeading getCheckOutHeading() {
		return checkOut_heading;
	}
	public void setCheckOutHeading(CheckOutHeading checkOut_heading) {
		this.checkOut_heading = checkOut_heading;
	}
	
	public abstract void onCheckoutOk(Transaction transaction);

	public static class CheckOutHeading extends Panel{
		private static final long serialVersionUID = -6244360414248075540L;
		private TextField tinNo_filed, date_field, terms_field;
		
		public CheckOutHeading() {
			setLayout(new BorderLayout(10, 0));
			setPreferredSize(new Dimension(Integer.MAX_VALUE, 70));
			
			Panel top_panel = new Panel();
			top_panel.setLayout(new BorderLayout(10, 0));
			add(top_panel, BorderLayout.NORTH);

			Panel tin_panel = new Panel();
			tin_panel.setLayout(new BorderLayout(10, 0));		
			tin_panel.add(new Label("TIN No.:"), BorderLayout.WEST);
			tinNo_filed = new TextField("0000");
			tinNo_filed.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			tin_panel.add(tinNo_filed, BorderLayout.CENTER);
			top_panel.add(tin_panel, BorderLayout.CENTER);
			
			Panel date_panel = new Panel();
			date_panel.setLayout(new BorderLayout(10, 0));
			date_panel.add(new Label("Date:"), BorderLayout.WEST);
			date_field = new TextField(new Date().toString());
			date_field.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			date_field.setEditable(false);
			date_panel.add(date_field, BorderLayout.CENTER);
			top_panel.add(date_panel, BorderLayout.EAST);	
			
			Panel terms_panel = new Panel();
			terms_panel.setLayout(new BorderLayout(10, 0));
			terms_panel.add(new Label("Terms:"), BorderLayout.WEST);
			terms_field = new TextField();
			terms_field.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			terms_panel.add(terms_field, BorderLayout.CENTER);
			add(terms_panel, BorderLayout.SOUTH);
		}
		public void setTinNo(String tin_no) {
			tinNo_filed.setText(tin_no);
		}
		public String getTinNo() {
			return tinNo_filed.getText();
		}
		public void setDate(Date date) {
			date_field.setText(date.toString());
		}
		public Date getDate() {
			return new Date(date_field.getText());
		}
		public void setTerms(String terms) {
			terms_field.setText(terms);
		}
		public String getTerms() {
			return terms_field.getText();
		}
	}
}
