package system.ui.tables;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import components.table.Row;
import system.enumerators.ProductCondition;
import system.objects.Notification;
import system.objects.Order;
import system.ui.cells.labeling.CellLabelDecimal;

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
	public void removeOrder(Order order) {
		int n=0;
		for(Order o: getOrders()) {
			if(o == order) {
				removeProduct(n);
			}
			n++;
		}
	}
	public void removeOrders(int o[]) {
		for(int i=0; i<o.length; i++) {
			removeOrder(o[i]);
		}
	}
	public void removeAllOrders() {
		removeAllRows();
	}
	public Order getOrder(int n) {
		return ((OrderRow)getRow(n)).getOrder();
	}
	public Order[] getOrders() {
		Row rows[] = getRows();
		Order orders[] = new Order[rows.length];
		for(int o=0; o<orders.length; o++) {
			orders[o] = ((OrderRow)rows[o]).getOrder();
		}
		return orders;
	}
	public int getOrderCount() {
		return getRowCount();
	}
	public Order getSelectedOrder() {
		Row row = getSelectedRow();
		if(row != null) {
			return ((OrderRow)row).getOrder();
		}
		return null;
	}
	public Order[] getSelectedOrders() {
		Row rows[] = getSelectedRows();
		Order orders[] = new Order[rows.length];
		for(int o=0; o<orders.length; o++) {
			orders[o] = ((OrderRow)rows[o]).getOrder();
		}
		return orders;
	}
	
	public class OrderRow extends ProductRow{
		private static final long serialVersionUID = 2778698924837158048L;
		private Order order;

		public OrderRow(Order order) {
			super(order.getProduct());
			setOrder(order);
			addCell(new CellLabelDecimal(order.getNetAmount()));
			//getCell(3).setVisible(false);
			//getCell(8).setVisible(false);
			
			getCheckBox().setEnabled(true);
			if(order.getProduct().getProductCondition() == ProductCondition.RETURNED) {
				setDepricated(true);
				getCheckBox().setEnabled(false);
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(getCheckBox().isEnabled()) super.mouseClicked(e);
		}
		@Override
		public void setSelected(boolean isSelected) {
			if(getCheckBox().isEnabled()) {
				super.setSelected(isSelected);
			}
			else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
		@Override
		public void pushNotification() {
			//disabled
		}
		@Override
		public void onPushNotification(Notification notification) {}
	}
}
