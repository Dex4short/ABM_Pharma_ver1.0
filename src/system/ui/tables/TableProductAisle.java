package system.ui.tables;

import components.Label;
import components.table.Cell;
import components.table.Column;
import components.table.Row;
import oop.Product;
import system.ui.cells.CellButton;
import system.ui.cells.CellLabelDate;


public class TableProductAisle extends TableProducts{
	private static final long serialVersionUID = -8928666190282594688L;

	public TableProductAisle() {
		Column btn_column = new Column("...");
		addColumn(btn_column);
		addInventoryRows();
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

	@Override
	public void addInventoryRows() {
		new Thread() {
			@Override
			public void run() {
				for(int i=0; i<25; i++) {
					Cell cells[] = new Cell[13];
					for(int c=0; c<=12; c++) {
						if(c==4) {
							cells[c] = new CellLabelDate(fields[c]);
						}
						if (c == 12) {
							cells[c] = new CellButton("+ Add to Cart");
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
