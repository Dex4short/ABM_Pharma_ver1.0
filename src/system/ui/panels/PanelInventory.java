package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.table.Row;
import oop.Product;
import oop.enums.ProductCondition;
import oop.interfaces.Theme;
import system._default_.Inventory;
import system.ui.Window;
import system.ui.buttons.ButtonAddProduct;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonEditProduct;
import system.ui.buttons.ButtonPrintProduct;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.search_panels.InventorySearchPanel;
import system.ui.tables.TableInventory;

public class PanelInventory extends JPanel implements Inventory, Theme{
	private static final long serialVersionUID = 5294758605465387431L;
	private JPanel panel;
	private TableInventory table_inventory;

	public PanelInventory() {
		setOpaque(false);
		setLayout(null);
		
		panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		add(panel);
		
		JPanel header = new JPanel(new BorderLayout(0,0));
		header.setOpaque(false);
		header.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.add(header, BorderLayout.NORTH);
		
		JPanel header_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header_left.setOpaque(false);
		header.add(header_left, BorderLayout.WEST);
		
		JPanel header_right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		header_right.setOpaque(false);
		header.add(header_right, BorderLayout.EAST);
		
		InventorySearchPanel search_panel = new InventorySearchPanel() {
			private static final long serialVersionUID = -1256506246091903002L;
			@Override
			public void onSearch(String category, String word) {
				searchFromInventory(category, word);
			}
		};
		header_left.add(search_panel);
		
		ButtonPrintProduct btn_printProduct = new ButtonPrintProduct();
		btn_printProduct.addMouseListener(search_panel.closeSearchAdapter());
		btn_printProduct.setEnabled(false);
		header_right.add(btn_printProduct);
		
		ButtonReserveProduct btn_reserveProduct = new ButtonReserveProduct();
		btn_reserveProduct.addMouseListener(search_panel.closeSearchAdapter());
		btn_reserveProduct.setEnabled(false);
		header_right.add(btn_reserveProduct);
		
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct();
		btn_disposeProduct.addMouseListener(search_panel.closeSearchAdapter());
		btn_disposeProduct.setEnabled(false);
		header_right.add(btn_disposeProduct);
		
		ButtonEditProduct btn_editProduct = new ButtonEditProduct();
		btn_editProduct.addMouseListener(search_panel.closeSearchAdapter());
		btn_editProduct.setEnabled(false);
		header_right.add(btn_editProduct);

		ButtonAddProduct btn_addProduct = new ButtonAddProduct() {
			private static final long serialVersionUID = -634618781892783590L;
			@Override
			public void onAddProduct(Product product, ProductCondition condition) {
				addToInventory(product, condition);
			}
		};
		btn_addProduct.addMouseListener(search_panel.closeSearchAdapter());
		header_right.add(btn_addProduct);
		
		table_inventory = new TableInventory() {
			private static final long serialVersionUID = 3085703898743340238L;
			@Override
			public void addRow(Row row) {
				row.addMouseListener(search_panel.closeSearchAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean enabled = e.getStateChange() == ItemEvent.SELECTED;
						btn_printProduct.setEnabled(enabled);
						btn_reserveProduct.setEnabled(enabled);
						btn_disposeProduct.setEnabled(enabled);
						btn_editProduct.setEnabled(enabled);
					}
				});
				super.addRow(row);
			}
		};
		table_inventory.addMouseListener(search_panel.closeSearchAdapter());
		panel.add(table_inventory, BorderLayout.CENTER);
		
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		panel.setBounds(0, 0, getWidth(), getHeight());
	}
	@Override
	public void onSearchFromInventory(Product products[]) {
		table_inventory.removeAllProducts();
		table_inventory.addProducts(products);
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
		table_inventory.addProduct(product);
		Window.floatMessage(product.getItem().getDescription() + " added");
	}
	@Override
	public void onLoadAllProducts(Product[] products) {
		table_inventory.removeAllProducts();
		table_inventory.addProducts(products);
	}

}
