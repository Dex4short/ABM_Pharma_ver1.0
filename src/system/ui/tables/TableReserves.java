package system.ui.tables;

import components.table.Cell;
import components.table.Row;
import oop.Product;
import system.ui.cells.CellLabel;
import system.ui.cells.CellLabelDate;
import system.ui.cells.CellLabelDecimal;
import system.ui.cells.CellLabelPercentage;
import system.ui.cells.CellLabelQuantity;
import system.ui.cells.CellLabelUom;

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
	
	public class ProductReservesRow extends Row{
		private static final long serialVersionUID = 2778698924837158048L;
		private Product product;

		public ProductReservesRow(Product product) {
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
					new CellLabelDecimal(product.getPricing().getUnitAmount()),
					new CellLabel(product.getRemarks().getDetails())
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
}
