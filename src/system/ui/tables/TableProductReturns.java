package system.ui.tables;

import system.objects.Product;
import system.ui.cells.labeling.CellLabel;

public class TableProductReturns extends TableProducts{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount", "Remarks"};

	public TableProductReturns() {
		setColumns(fields);
	}
	@Override
	public void addProduct(Product product) {
		addRow(new ProductReturnedRow(product));
	}
	@Override
	public void addProducts(Product products[]) {
		ProductReturnedRow rows[] = new ProductReturnedRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductReturnedRow(products[r]);
		}
		addRows(rows);
	}
	@Override
	public Product getProduct(int n) {
		return ((ProductReturnedRow)getRow(n)).getProduct();
	}
	
	public class ProductReturnedRow extends ProductRow{
		private static final long serialVersionUID = 2778698924837158048L;

		public ProductReturnedRow(Product product) {
			super(product);
			addCell(new CellLabel(product.getRemarks().toStringTitleDetail()));
		}
		@Override
		public void checkExpiry() {
			//disabled
		}
		@Override
		public void checkStock() {
			//disabled
		}
	}
}
