package system.ui.tables;

import components.table.Column;
import oop.Product;
import system.ui.cells.CellButton;


public class TableAisle extends TableProducts{
	private static final long serialVersionUID = -8928666190282594688L;

	public TableAisle() {
		Column btn_column = new Column("...");
		addColumn(btn_column);
	}
	@Override
	public void addProduct(Product product) {
		addRow(new ProductAisleRow(product));
	}
	@Override
	public void addProducts(Product[] products) {
		ProductAisleRow rows[] = new ProductAisleRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductAisleRow(products[r]);
		}
		addRows(rows);
	}

	public class ProductAisleRow extends TableProducts.ProductRow{
		private static final long serialVersionUID = 5279465302007523078L;

		public ProductAisleRow(Product product) {
			super(product);
			CellButton cell_btn = new CellButton("+ Add to Cart");
			cell_btn.getButton().setArc(20);
			addCell(cell_btn);
		}
	}
}
