package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import components.table.Row;
import oop.Product;
import oop.enums.ProductCondition;
import system._default_.Inventory;
import system.ui.UI1;
import system.ui.Window;
import system.ui.buttons.ButtonAddProduct;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonEditProduct;
import system.ui.buttons.ButtonPrintProducts;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.panels.searches.SearchPanelProduct;
import system.ui.tables.TableProducts;

public class PanelInventory extends UI1 implements Inventory{
	private static final long serialVersionUID = 5294758605465387431L;
	private TableProducts table_products;

	public PanelInventory() {		
		SearchPanelProduct search_panel = new SearchPanelProduct() {
			private static final long serialVersionUID = -1256506246091903002L;
			@Override
			public void onSearch(String category, String word) {
				searchFromInventory(category, word);
			}
		};
		setSearchPanel(search_panel);
		
		ButtonPrintProducts btn_printProduct = new ButtonPrintProducts();
		addButton(btn_printProduct);
		
		ButtonReserveProduct btn_reserveProduct = new ButtonReserveProduct() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onReserveProduct(Product product) { table_products.removeProduct(product); }
			@Override
			public Product getSelectedProduct() { return table_products.getSelectedProduct(); }
		};
		btn_reserveProduct.setEnabled(false);
		addButton(btn_reserveProduct);
		
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDisposeProduct(Product product) {	table_products.removeProduct(product);	}
			@Override
			public Product getSelectedProduct() { return table_products.getSelectedProduct(); }
		
		};
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);
		
		ButtonEditProduct btn_editProduct = new ButtonEditProduct() {
			private static final long serialVersionUID = 4118216613740322570L;
			@Override
			public void onEditProduct(Product product, ProductCondition condition) { /*TODO*/ } 
			@Override
			public Product getSelectedProduct() { return table_products.getSelectedProduct(); }
		};
		btn_editProduct.setEnabled(false);
		addButton(btn_editProduct);

		ButtonAddProduct btn_addProduct = new ButtonAddProduct() {
			private static final long serialVersionUID = -634618781892783590L;
			@Override
			public void onAddProduct(Product product, ProductCondition condition) {	addToInventory(product, condition); }
		};
		addButton(btn_addProduct);
		
		table_products = new TableProducts() {
			private static final long serialVersionUID = 3085703898743340238L;
			@Override
			public void addRow(Row row) {
				row.addMouseListener(closeSearchFilterAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean enabled = e.getStateChange() == ItemEvent.SELECTED;
						btn_reserveProduct.setEnabled(enabled);
						btn_disposeProduct.setEnabled(enabled);
						btn_editProduct.setEnabled(enabled);
					}
				});
				super.addRow(row);
			}
		};
		setTable(table_products);
	}
	@Override
	public void onSearchFromInventory(Product products[]) {
		table_products.removeAllProducts();
		table_products.addProducts(products);
	}
	@Override
	public void onPrintFromInventory() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onReserveFromInventory() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onDeleteFromInventory() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onEditFromInventory() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onAddToInventory(Product product) {
		table_products.addProduct(product);
		Window.floatMessage(product.getItem().getDescription() + " added");
	}
	@Override
	public void onLoadAllProducts(Product[] products) {
		table_products.removeAllProducts();
		table_products.addProducts(products);
	}

}
