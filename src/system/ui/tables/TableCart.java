package system.ui.tables;

import components.table.Column;
import oop.Order;
import system.ui.cells.CellButton;

public class TableCart extends TableOrders{
	private static final long serialVersionUID = -5292964100747790231L;

	public TableCart() {
		Column btn_column = new Column("...");
		addColumn(btn_column);
	}
	@Override
	public void addOrder(Order order) {
		addRow(new ProductCartRow(order));
	}
	@Override
	public void addOrders(Order orders[]) {
		ProductCartRow rows[] = new ProductCartRow[orders.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductCartRow(orders[r]);
		}
		addRows(rows);
	}
	
	public class ProductCartRow extends TableOrders.OrderRow{
		private static final long serialVersionUID = 2233983822815909563L;
	
		public ProductCartRow(Order order) {
			super(order);
			CellButton cell_btn = new CellButton("- Remove");
			cell_btn.getButton().setArc(20);
			addCell(cell_btn);
		}
		
	}
}
