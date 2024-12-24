package database;

import java.math.BigDecimal;

import oop.Decimal;
import oop.Order;
import oop.enums.ProductCondition;

public class MySQL_Orders {
	public static final String 
	table_name = "orders",
	table_columns[] = {"order_no", "prod_id", "net_amount"};

	public static Order insertOrder(Order order) {
		MySQL_Products.insertProduct(order.getProduct(), ProductCondition.ORDERED);
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				order.getOrderNo(),
				order.getProduct().getProdId(),
				order.getNetAmount().toBigDecimal()
			}
		);
		return order;
	}
	public static Order[] selectOrders(int order_no) {
		Object results[][] = MySQL.select(
				new String[] {"prod_id", "net_amount"},
				table_name,
				"where order_no=" + order_no
		);
		
		Order orders[] = new Order[results.length];
		for(int r=0; r<results.length; r++) {
			orders[r] = new Order(
				order_no,
				MySQL_Products.selectProduct((int)results[r][0], ProductCondition.ORDERED),
				new Decimal((BigDecimal)results[r][1])
			);
		}
		
		return orders;
	}
}
