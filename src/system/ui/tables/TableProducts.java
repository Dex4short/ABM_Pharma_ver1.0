package system.ui.tables;

import components.Label;
import components.table.Cell;
import components.table.Row;
import components.table.Table;
import oop.Product;
import system.ui.cells.CellLabel;
import system.ui.cells.CellLabelDate;
import system.ui.cells.CellLabelDecimal;
import system.ui.cells.CellLabelPercentage;
import system.ui.cells.CellLabelQuantity;
import system.ui.cells.CellLabelUom;

public class TableProducts extends Table{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount"};

	public TableProducts() {
		super(fields);
		//addInventoryRows();
	}
	public void addProduct(Product product) {
		addRow(new ProductRow(product));
	}
	public void addProducts(Product products[]) {
		ProductRow rows[] = new ProductRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductRow(products[r]);
		}
		addRows(rows);
	}
	public void removeProduct(int n) {
		removeRow(n);
	}
	public void removeProduct(Product product) {
		int n=0;
		for(Product p: getProducts()) {
			if(p == product) {
				removeProduct(n);
				n++;
			}
		}
	}
	public void removeProducts(int p[]) {
		Row rows[] = new Row[p.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = getRow(p[r]);
		}
		for(Row row: rows) {
			remove(row);
		}
	}
	public void removeAllProducts() {
		removeAllRows();
	}
	public Product getProduct(int n) {
		return ((ProductRow)getRow(n)).getProduct();
	}
	public Product[] getProducts() {
		Product products[] = new Product[getProductCount()];
		for(int p=0; p<products.length; p++) {
			products[p] = getProduct(p);
		}
		return products;
	}
	public int getProductCount() {
		return getRowCount();
	}
	
	public class ProductRow extends Row{
		private static final long serialVersionUID = 2778698924837158048L;
		private Product product;

		public ProductRow(Product product) {
			super(new Cell[] {
					new CellLabel(product.getItem().getItemNo()),
					new CellLabel(product.getItem().getDescription()),
					new CellLabel(product.getItem().getLotNo()),
					new CellLabel(product.getItem().getDateAdded().toString()),
					new CellLabelDate(product.getItem().getExpDate()),
					new CellLabel(product.getItem().getBrand()),
					new CellLabelQuantity(product.getPackaging().getQty()),
					new CellLabelUom(product.getPackaging().getUom()),
					new CellLabelDecimal(product.getPricing().getCost()),
					new CellLabelDecimal(product.getPricing().getUnitPrice()),
					new CellLabelPercentage(product.getPricing().getDiscount()),
					new CellLabelDecimal(product.getPricing().getUnitAmount())
			});
			setProduct(product);
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
	}
	
	/*sample method*/
	public void addInventoryRows() {
		new Thread() {
			@Override
			public void run() {
				for(int i=0; i<25; i++) {
					Cell cells[] = new Cell[12];
					for(int c=0; c<12; c++) {
						if(c==4) {
							cells[c] = new CellLabelDate(fields[c]);
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
