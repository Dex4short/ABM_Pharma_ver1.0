package system.ui.tables;

import system.objects.Notification;
import system.objects.Product;
import system.ui.cells.labeling.CellLabel;
import system.ui.panels.PanelAdmin;

public class TableReserves extends TableProducts{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount", "Remarks"};

	public TableReserves() {
		super();
		setColumns(fields);
	}
	@Override
	public void addProduct(Product product) {
		addRow(new ProductReservesRow(product));
	}
	@Override
	public void addProducts(Product products[]) {
		ProductReservesRow rows[] = new ProductReservesRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductReservesRow(products[r]);
		}
		addRows(rows);
	}
	@Override
	public Product getProduct(int n) {
		return ((ProductReservesRow)getRow(n)).getProduct();
	}
	
	public class ProductReservesRow extends ProductRow{
		private static final long serialVersionUID = 2778698924837158048L;

		public ProductReservesRow(Product product) {
			super(product);
			addCell(new CellLabel(product.getRemarks().toStringTitleDetail()));
		}
		@Override
		public void onPushNotification(Notification notification) {
			PanelAdmin.notifyTab(3, true);
		}
	}
}
