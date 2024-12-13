package system.ui.tables;

import components.Label;
import components.table.Cell;
import components.table.Column;
import components.table.Row;
import oop.Product;
import system.ui.cells.CellButton;
import system.ui.cells.CellLabelDate;

public class TableCart extends TableProducts{
	private static final long serialVersionUID = -5292964100747790231L;

	public TableCart() {
		Column btn_column = new Column("...");
		addColumn(btn_column);
		addInventoryRows();
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
	
	@Override
	public void addInventoryRows() {
		new Thread() {
			@Override
			public void run() {
				for(int i=0; i<25; i++) {
					Cell cells[] = new Cell[13];
					for(int c=0; c<=12; c++) {
						if(c==4) {
							cells[c] = new CellLabelDate("yyyy-mm-dd");
						}
						if (c == 12) {
							cells[c] = new CellButton("- Remove");
							((CellButton)cells[c]).getButton().setArc(20);
						}
						else{
							cells[c] = new Cell(new Label(fields[c]));
						}
					}
					addRow(new Row(cells));
				}
			}
		}.start();
	}
}
