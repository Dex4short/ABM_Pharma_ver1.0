package system._default_;

import database.MySQL_Customer;
import oop.Customer;

public interface Customers {
	
	public default Customer selectFromCustomers() {
		return onSelectFromCustomers();
	}
	public default Customer[] selectMoreFromCustomers() {
		return onSelectMoreFromCustomers();
	}
	public default void addFromCustomers(Customer customer) {
		MySQL_Customer.insertCustomer(customer);
		onAddFromCustomers(customer);
	}
	public default void editFromCustomers(Customer customer) {
		MySQL_Customer.updateCustomer(customer);
		onEditFromCustomers(customer);
	}
	public default void deleteFromCustomers(Customer customer) {
		
		onDeleteFromCustomers(customer);
	}
	public default void loadAllFromCustomers() {
		onLoadAllFromCustomers(MySQL_Customer.selectCustomers());
	}
	
	public Customer onSelectFromCustomers();
	public Customer[] onSelectMoreFromCustomers();
	public void onAddFromCustomers(Customer customer);
	public void onEditFromCustomers(Customer customer);
	public void onDeleteFromCustomers(Customer customer);
	public void onLoadAllFromCustomers(Customer customers[]);

}
