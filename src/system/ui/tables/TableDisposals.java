package system.ui.tables;

import system.objects.Product;
import system.ui.cells.CellLabel;

public class TableDisposals extends TableProducts{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount", "Remarks"};

	
	public TableDisposals() {
		super(fields);
	}
	public void addProduct(Product product) {
		addRow(new ProductDisposalRow(product));
	}
	public void addProducts(Product products[]) {
		ProductDisposalRow rows[] = new ProductDisposalRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductDisposalRow(products[r]);
		}
		addRows(rows);
	}
	
	public class ProductDisposalRow extends ProductRow{
		private static final long serialVersionUID = 2778698924837158048L;

		public ProductDisposalRow(Product product) {
			super(product);
			addCell(new CellLabel(product.getRemarks().toStringTitleDetail()));
			setProduct(product);
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
