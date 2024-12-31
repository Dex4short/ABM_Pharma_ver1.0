package system.ui.tables;

import components.table.Column;
import system.objects.Order;
import system.objects.Packaging;
import system.ui.cells.clickable.CellButtonRemoveFromCart;

public abstract class TableCart extends TableOrders{
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
	public void removeOrderFromCart(Packaging[] extracted_packs, Packaging sub_pack) {
		onRemoveOrderFromCart(extracted_packs, sub_pack);
	}
	
	public abstract void onRemoveOrderFromCart(Packaging[] extracted_packs, Packaging sub_pack);
	
	public class ProductCartRow extends OrderRow{
		private static final long serialVersionUID = 2233983822815909563L;
	
		public ProductCartRow(Order order) {
			super(order);
			CellButtonRemoveFromCart cell_btn = new CellButtonRemoveFromCart(order) {
				private static final long serialVersionUID = -351539179842848737L;
				@Override
				public void onRemoveFromCart(Packaging[] extracted_packs, Packaging sub_pack) {
					removeOrderFromCart(extracted_packs, sub_pack);
				}
			};
			cell_btn.getButton().addActionListener(e -> selectRow(this));
			addCell(cell_btn);

			getCheckBox().setEnabled(true);
		}
		@Override
		public void pushNotification() {
			//disabled
		}
	}
}
