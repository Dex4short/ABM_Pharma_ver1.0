package system.objects;

import system.enumerators.CustomerState;

public class Customer {
	private int cust_id;
	private String customer_name, address, contact_no, e_mail, company;
	private CustomerState customer_state;

	public Customer() {
		setCustomerId(-1);
		setCustomerName("none");
		setAddress("none");
		setContactNo("none");
		setEmail("none");
		setCompany("none");
		setCustomerState(CustomerState.Unlisted);
	}
	public Customer(int cust_id, String customer_name, String address, String contact_no, String e_mail, String company, CustomerState customer_state) {
		setCustomerId(cust_id);
		setCustomerName(customer_name);
		setAddress(address);
		setContactNo(contact_no);
		setEmail(e_mail);
		setCompany(company);
		setCustomerState(customer_state);
	}
	@Override
	public String toString() {
		return 
			"Customer:\n" + 
			"cust_id:\t" + getCustomerId() + "\n" +
			"Name:\t" + getCustomerName() + "\n" +
			"Address:\t" + getAddress() + "\n" +
			"Contact No.:\t" + getContactNo() + "\n" +
			"E-mail:\t" + getEmail() + "\n" +
			"Company:\t" + getCompany() + "\n" +
			"CustomerState:\t" + getCustomerState().name() + "\n";
	}
	public void setCustomerId(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getCustomerId() {
		return cust_id;
	}
	public String getCustomerName() {
		return customer_name;
	}
	public void setCustomerName(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contact_no;
	}
	public void setContactNo(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail() {
		return e_mail;
	}
	public void setEmail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public CustomerState getCustomerState() {
		return customer_state;
	}
	public void setCustomerState(CustomerState customer_state) {
		this.customer_state = customer_state;
	}
}
