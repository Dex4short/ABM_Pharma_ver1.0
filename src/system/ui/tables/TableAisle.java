package system.ui.tables;

import components.table.Column;
import oop.Packaging;
import oop.Product;
import system.ui.cells.CellButtonAddToCart;


public abstract class TableAisle extends TableProducts{
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
	public void addProductToCart(Packaging extracted_packs[], Packaging sub_pack) {
		onAddProductToCart(extracted_packs, sub_pack);
	}
	
	public abstract void onAddProductToCart(Packaging extracted_packs[], Packaging sub_pack);

	public class ProductAisleRow extends TableProducts.ProductRow{
		private static final long serialVersionUID = 5279465302007523078L;

		public ProductAisleRow(Product product) {
			super(product);
			CellButtonAddToCart cell_btn = new CellButtonAddToCart(product) {
				private static final long serialVersionUID = 8362326710498493098L;
				@Override
				public void onAddToCart(Packaging extracted_packs[], Packaging sub_pack) {
					addProductToCart(extracted_packs, sub_pack);
				}
			};
			addCell(cell_btn);
			cell_btn.getButton().addActionListener(e -> selectRow(this));
		}
	}
}
