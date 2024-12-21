package database;

import oop.Cart;
import oop.Order;

public class MySQL_Cart {
	public static final String
	table_name = "cart",
	table_columns[] = {"cart_no", "counter_no", "order_no"};

	public static Cart insertCart(Cart cart) {
		cart.setCartNo(MySQL.count(table_name, "where counter_no=" + cart.getCounterNo()));
		
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				cart.getCartNo(),
				cart.getCounterNo(),
				cart.getOrderNo()
			}
		);
		return cart;
	}
	public static Cart selectCart(int currentCart_no, int counter_no) {
		Object result[][] = MySQL.select(
			new String[] {"order_no"},
			table_name,
			" where cart_no=" + currentCart_no + " and " + "counter_no=" + counter_no
		);
		int order_no = (int)result[0][0];
		Order orders[] = MySQL_Orders.selectOrders(order_no);
		return new Cart(currentCart_no, counter_no, order_no, orders);
	}
}
