package system.ui.tables;

import components.table.Row;
import oop.Order;
import system.ui.cells.CellLabelDecimal;

public class TableOrders extends TableProducts{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount", "Net. Amount"};

	public TableOrders() {
		super(fields);
	}
	public void addOrder(Order order) {
		addRow(new OrderRow(order));
	}
	public void addOrders(Order orders[]) {
		OrderRow rows[] = new OrderRow[orders.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new OrderRow(orders[r]);
		}
		addRows(rows);
	}
	public void removeOrder(int n) {
		removeRow(n);
	}
	public void removeProduct(Order order) {
		int n=0;
		for(Order o: getOrders()) {
			if(o == order) {
				removeProduct(n);
				n++;
			}
		}
	}
	public void removeProducts(int p[]) {
		Row rows[] = new Row[p.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = getRow(p[r]);
		}
		for(Row row: rows) {
			remove(row);
		}
	}
	public void removeAllProducts() {
		removeAllRows();
	}
	public Order getOrder(int n) {
		return ((OrderRow)getRow(n)).getOrder();
	}
	public Order[] getOrders() {
		Order orders[] = new Order[getProductCount()];
		for(int o=0; o<orders.length; o++) {
			orders[o] = getOrder(o);
		}
		return orders;
	}
	public int getProductCount() {
		return getRowCount();
	}
	
	public class OrderRow extends ProductRow{
		private static final long serialVersionUID = 2778698924837158048L;
		private Order order;

		public OrderRow(Order order) {
			super(order.getProduct());
			setOrder(order);
			addCell(new CellLabelDecimal());
			//getCell(3).setVisible(false);
			//getCell(8).setVisible(false);
		}
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
	}
}
