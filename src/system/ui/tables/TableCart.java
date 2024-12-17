package system.ui.tables;

import components.table.Column;
import oop.Product;
import system.ui.cells.CellButton;

public class TableCart extends TableProducts{
	private static final long serialVersionUID = -5292964100747790231L;

	public TableCart() {
		Column btn_column = new Column("...");
		addColumn(btn_column);
	}
	@Override
	public void addProduct(Product product) {
		addRow(new ProductCartRow(product));
	}
	@Override
	public void addProducts(Product[] products) {
		ProductCartRow rows[] = new ProductCartRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductCartRow(products[r]);
		}
		addRows(rows);
	}
	
	public class ProductCartRow extends TableProducts.ProductRow{
		private static final long serialVersionUID = 2233983822815909563L;
	
		public ProductCartRow(Product product) {
			super(product);
			CellButton cell_btn = new CellButton("- Remove");
			cell_btn.getButton().setArc(20);
			addCell(cell_btn);
		}
		
	}
}
