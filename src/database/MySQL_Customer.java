package database;

import oop.Customer;
import oop.enums.CustomerState;

public class MySQL_Customer {
	public static final String
	table_name = "customers",
	table_columns[] = {"cust_id", "customer_name", "address", "contact_no", "e_mail", "company", "customer_state"};

	public static void insertCustomer(Customer customer) {
		customer.setCustomerId(MySQL.nextUID("cust_id", table_name));
		
		MySQL.insert(
			table_name, 
			table_columns,
			new Object[] {
				customer.getCustomerId(),
				customer.getCustomerName(),
				customer.getAddress(),
				customer.getContactNo(),
				customer.getEmail(),
				customer.getCompany(),
				customer.getCustomerState().name()
			}
		);
	}	
	public static Customer selectCustomer(int customer_id) {
		Object result[][] = MySQL.select(
			new String[] {"customer_name", "address", "contact_no", "e_mail", "company", "customer_state"},
			table_name,
			"where cust_id=" + customer_id
		);
		return new Customer(
			customer_id, 
			(String)result[0][0], 
			(String)result[0][1], 
			(String)result[0][2], 
			(String)result[0][3], 
			(String)result[0][4],
			CustomerState.valueOf((String)result[0][5])
		);
	}
	public static Customer[] selectCustomers(CustomerState customer_state) {
		return selectCustomers("where customer_state='" + customer_state + "' ");
	}
	public static Customer[] selectCustomers(String condition) {
		Object result[][] = MySQL.select(table_columns,	table_name,condition);
		Customer customers[] = new Customer[result.length];
		for(int c=0; c<customers.length; c++) {
			customers[c] = new Customer(
				(int)result[c][0],
				(String)result[c][1],
				(String)result[c][2],
				(String)result[c][3],
				(String)result[c][4],
				(String)result[c][5],
				CustomerState.valueOf((String)result[c][6])
			);
		}
		return customers;
	}
	public static void updateCustomer(Customer customer) {
		MySQL.update(
			table_name, 
			new String[] {"customer_name", "address", "contact_no", "e_mail", "company", "customer_state"}, 
			new Object[] {
				customer.getCustomerName(),
				customer.getAddress(),
				customer.getContactNo(),
				customer.getEmail(),
				customer.getCompany(),
				customer.getCustomerState().name()
			},
			"where cust_id=" + customer.getCustomerId()
		);
	}
	public static void deleteCustomer(Customer customer) {
		boolean has_records = MySQL_Transactions.selectFromTransactions(customer).length != 0;
		if(has_records) {
			customer.setCustomerState(CustomerState.Unlisted);
			updateCustomer(customer);
		}
		else{
			MySQL.delete(table_name, "where cust_id=" + customer.getCustomerId());
		}
	}
}
