package database;

import oop.Cart;

public class MySQL_Cart {
	public static String 
	table_name = "cart",
	table_columns[] = {"cart_no", "counter_no", "order_no"};
	
	public static Cart insertCart(Cart cart, int counter_no) {
		cart.setCartNo(MySQL.nextUID("cart_no", "cart"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				cart.getCartNo(),
				counter_no,
				cart.getOrders()
			}
		);
		return cart;
	}
	public static Cart selectCart(int cart_no, int counter_no) {
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
