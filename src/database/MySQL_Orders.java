package database;

import java.math.BigDecimal;

import system.enumerators.ProductCondition;
import system.objects.Decimal;
import system.objects.Order;

public class MySQL_Orders {
	public static final String 
	table_name = "orders",
	table_columns[] = {"order_no", "prod_id", "net_amount"};

	public static Order insertOrder(Order order) {
		order.getProduct().setProduct_condition(ProductCondition.ORDERED);
		
		MySQL_Products.insertProduct(order.getProduct());
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
	public static Order selectOrder(int order_no, int prod_id) {
		Object results[][] = MySQL.select(
				new String[] {"prod_id", "net_amount"},
				table_name,
				"where order_no=" + order_no
		);
		return new Order(
				order_no,
				MySQL_Products.selectProduct((int)results[0][0]),
				new Decimal((BigDecimal)results[0][1])
		);
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
				MySQL_Products.selectProduct((int)results[r][0]),
				new Decimal((BigDecimal)results[r][1])
			);
		}
		return orders;
	}
	public static Order[] selectOrders(int trans_id, String key, String word) {
		String
		joins = " transactions as t"
				+ " join "
				+ " cart as c"
				+ " join "
				+ " orders as o"
				+ " join "
				+ " products as p"
				+ " join "
				+ " item as i"
				+ " join "
				+ " packaging as k"
				+ " join "
				+ " uom as u"
				+ " join "
				+ " pricing as s",
		on = " on t.cart_no = c.cart_no and"
				+ " c.order_no = o.order_no and"
				+ " o.prod_id = p.prod_id and"
				+ " p.item_id = i.item_id and "
				+ "	p.pack_id = k.pack_id and k.uom_id = u.uom_id and "
				+ "	p.price_id = s.price_id ",
		where = " where (p.prod_condition='" + ProductCondition.ORDERED + "' or p.prod_condition='" + ProductCondition.RETURNED + "') and " + key + word;
		Object results[][] = MySQL.select(new String[] {"o.order_no", "p.prod_id"}, joins, on + where);
		
		Order orders[] = new Order[results.length];
		for(int o=0; o<orders.length; o++) {
			orders[o] = selectOrder((int)results[o][0], (int)results[o][1]);
		}
		
		return orders;
	}
	public static void deleteOrder(Order order) {
		MySQL.delete(table_name, "where order_no=" + order.getOrderNo() + " and prod_id=" + order.getProduct().getProdId());
		MySQL_Products.deleteProduct(order.getProduct());
	}
	public static void updateOrder(Order order, ProductCondition product_condition) {
		order.getProduct().setProduct_condition(product_condition);
		MySQL_Products.updateProduct(order.getProduct());
	}
	public static void updateOrder(Order order) {
		MySQL.update(
			table_name,
			new String[] {"prod_id", "net_amount"},
			new Object[] {
				order.getProduct().getProdId(),
				order.getNetAmount().toBigDecimal()
			},
			"where order_no=" + order.getOrderNo() + " and prod_id=" + order.getProduct().getProdId()
		);
		MySQL_Products.updateProduct(order.getProduct());
	}
}
