package oop;

public class Customer {
	private int cust_id;
	private String customer_name, address;
	
	public Customer(int cust_id, String customer_name, String address) {
		setCustomerId(cust_id);
		setCustomerName(customer_name);
		setAddress(address);
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
}
