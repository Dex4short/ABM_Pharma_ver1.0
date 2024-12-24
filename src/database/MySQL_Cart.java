package database;

import oop.Cart;
import oop.Order;

public class MySQL_Cart {
	public static final String
	table_name = "cart",
	table_columns[] = {"cart_no", "counter_no", "order_no"};

	public static Cart insertCart(Cart cart) {
		cart.setCartNo(nextCartNo(cart.getCounterNo()));
		cart.setOrderNo(nextOrderNo());
		
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
	public static Cart insertCart(int counter_no) {
		return insertCart(new Cart(-1, counter_no, -1, new Order[0]));
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
	public static int nextCartNo(int counter_no) {
		return MySQL.count(table_name, "where counter_no=" + counter_no);
	}
	public static int nextOrderNo() {
		return MySQL.nextUID("order_no", table_name);
	}
}
