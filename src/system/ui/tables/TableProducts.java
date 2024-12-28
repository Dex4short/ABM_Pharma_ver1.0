package system.ui.tables;

import java.awt.Color;

import components.drawables.Dot;
import components.table.Cell;
import components.table.Row;
import components.table.Table;
import system.enumerators.Quality;
import system.managers.NotificationsManager;
import system.managers.PackagingManager;
import system.managers.QualityManager;
import system.objects.Notification;
import system.objects.Product;
import system.ui.cells.labeling.CellLabel;
import system.ui.cells.labeling.CellLabelDate;
import system.ui.cells.labeling.CellLabelDecimal;
import system.ui.cells.labeling.CellLabelPercentage;
import system.ui.cells.labeling.CellLabelQuantity;
import system.ui.cells.labeling.CellLabelUom;

public class TableProducts extends Table{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount"};

	public TableProducts() {
		super(fields);
	}
	public TableProducts(String fields[]) {
		super(fields);
	}
	@Override
	public void onSelectRow(Row row) {
		//overridable block
	}
	@Override
	public void onPointRow(Row row) {
		//overridable block
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
			}
			n++;
		}
	}
	public void removeProducts(int p[]) {
		for(int i=0; i<p.length; i++) {
			remove(p[i]);
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
	public Product getSelectedProduct() {
		Row row = getSelectedRow();
		if(row != null) return ((ProductRow)getSelectedRow()).getProduct();
		else return null;
	}
	public Product[] getSelectedProducts() {
		Row rows[] = getSelectedRows();
		Product products[] = new Product[rows.length];
		for(int p=0; p<products.length; p++) {
			products[p] = ((ProductRow)rows[p]).getProduct();
		}
		return products;
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
			
			checkStock();
			checkExpiry();
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public void checkStock() {
			boolean 
			warning = PackagingManager.isRunningOut(product.getPackaging()),
			caution = PackagingManager.isOutOfStock(product.getPackaging());
			
			Dot dot = ((CellLabelQuantity)getCell(6)).getDot();
			
			if(caution) {
				dot.setColor(Color.red);
				NotificationsManager.pushNotification(new Notification.OutOfStock(product));
			}
			else if(warning) {
				dot.setColor(Color.orange);
				NotificationsManager.pushNotification(new Notification.RunningOutOfStock(product));
			}
			dot.setShow(warning || caution);
		}
		public void checkExpiry() {
			Quality quality = QualityManager.isExpired(product.getItem().getExpDate());
			if(quality==Quality.Warning || quality==Quality.Bad || quality==Quality.Expired) {
				NotificationsManager.pushNotification(new Notification.ProductQuality(product));
			}
		}
	}
}
