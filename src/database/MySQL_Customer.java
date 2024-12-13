package database;

import oop.Cart;
import oop.Customer;

public class MySQL_Customer {
	public static String 
	table_name = "customer",
	table_columns[] = {"cust_id", "customer_name", "address", "contact_no", "e_mail", "company"};
	
	public static Customer insertCustomer(Customer customer, int counter_no) {
		customer.setCustomerId(MySQL.nextUID("cust_id", "customer"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				customer.getCustomerId(),
				customer.getCustomerName(),
				customer.getAddress(),
				customer.getCont
			}
		);
		return customer;
	}
	public static Customer selectCustomer(int car, int counter_no) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where cart_no=" + cart_no
		);
		Cart cart = new Cart(
				(int)result[i][0],	
				(int)result[i][1],
				(int)result[i][2]
			);
		return cart;
		}

	public static void deleteCart(int cart_no) {
		MySQL.delete(table_name, "where cart_no=" + cart_no);
	}
	public static void updateCart(int cart_no, Cart cart, int counter_no) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						cart.getCartNo(),
						counter_no,
						cart.getOrders()
				},
				"where cart_no=" + cart_no);
	}

}
