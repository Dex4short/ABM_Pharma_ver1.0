package system.ui.tables;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import components.drawables.Dot;
import components.table.Cell;
import components.table.Row;
import components.table.Table;
import system.managers.NotificationsManager;
import system.objects.Notification;
import system.objects.Product;
import system.ui.PanelAdmin;
import system.ui.cells.labeling.CellLabel;
import system.ui.cells.labeling.CellLabelDate;
import system.ui.cells.labeling.CellLabelDecimal;
import system.ui.cells.labeling.CellLabelDescription;
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
		addRow(new ProductRow(product) {
			private static final long serialVersionUID = 7656116187104838283L;
			@Override
			public void onPushNotification(Notification notification) {
				PanelAdmin.notifyTab(0, true);
			}
		});
	}
	public void addProducts(Product products[]) {
		ProductRow rows[] = new ProductRow[products.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new ProductRow(products[r]) {
				private static final long serialVersionUID = 7656116187104838283L;
				@Override
				public void onPushNotification(Notification notification) {
					PanelAdmin.notifyTab(0, true);
				}
			};
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
	
	public abstract class ProductRow extends Row{
		private static final long serialVersionUID = 2778698924837158048L;
		private Product product;

		public ProductRow(Product product) {
			super(new Cell[] {
				new CellLabel(product.getItem().getItemNo()),
				new CellLabelDescription(product.getItem().getDescription()),
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
			
			//if(product.getPackaging().getParentPackId() != -1) {
			//	getCheckBox().setEnabled(false);
			//}
			
			pushNotification();
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(getCheckBox().isEnabled()) super.mouseClicked(e);
		}
		@Override
		public void setSelected(boolean isSelected) {
			if(getCheckBox().isEnabled()) {
				super.setSelected(isSelected);
			}
			else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public void pushNotification() {
			Notification notification = NotificationsManager.pushNotification(getProduct());
			if(notification == null) return;
			
			Dot dot = ((CellLabelQuantity)getCell(6)).getDot();
			if(notification instanceof Notification.OutOfStock) {
				dot.setColor(Color.red);
				dot.show();
			}
			else if(notification instanceof Notification.RunningOutOfStock) {
				dot.setColor(Color.orange);
				dot.show();
			}
			
			onPushNotification(notification);
		}
		
		public abstract void onPushNotification(Notification notification);
		
	}
}
