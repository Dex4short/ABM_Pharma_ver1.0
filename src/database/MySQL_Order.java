package database;

import oop.Order;

public class MySQL_Order {
	public static String 
	table_name = "order",
	table_columns[] = {"order_no", "prod_id"};
	
	public static Order insertOrder(Order order) {
		order.setOrderNo(MySQL.nextUID("order_no", "order"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				order.getOrderNo(),
				order.getProduct()
			}
		);
		return order;
	}
	public static Order[] selectOrder(int order_no) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where oreder_no=" + order_no
		);
		Order order[] = new Order[result.length];
		for(int i=0; i<order.length; i++) {
			order[i] = new Order(
				(int)result[i][0],	
				(int)result[i][1]
			);
		}
		return order;
	}
	public static void deleteOrder(int order_no) {
		MySQL.delete(table_name, "where order_no=" + order_no);
	}
	public static void updateOrder(int order_no, Order order) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						order.getOrderNo(),
						order.getProduct()
				},
				"where order_no=" + order_no);
	}

}
