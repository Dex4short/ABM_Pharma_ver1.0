package system._default_;

import oop.Customer;

public interface Customers {

	public default void addCustomer(Customer customer) {
		
	}
	public default void editCustomer(Customer customer) {
		
	}
	public default void deleteCustomer(Customer customer) {
		
	}

	public void onAddCustomer(Customer customer);
	public void onEditCustomer(Customer customer);
	public void onDeleteCustomer(Customer customer);
	
}
