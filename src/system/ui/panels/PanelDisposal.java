package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import components.table.Row;
import system.ui.UI1;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.buttons.ButtonReturnToInventory;
import system.ui.search_panels.SearchPanelProduct;
import system.ui.tables.TableProducts;

public class PanelDisposal extends UI1{
	private static final long serialVersionUID = -3497678071480750501L;
	private TableProducts table_products;
	
	public PanelDisposal() {
		SearchPanelProduct search_panel = new SearchPanelProduct() {
			private static final long serialVersionUID = -154869804767992665L; 
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel);
		
		ButtonReturnToInventory btn_return_to_inventory = new ButtonReturnToInventory();
		btn_return_to_inventory.setEnabled(false);
		addButton(btn_return_to_inventory);
		ButtonReserveProduct btn_reserveProduct = new ButtonReserveProduct();
		btn_reserveProduct.setEnabled(false);
		addButton(btn_reserveProduct);
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct();
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);

		table_products = new TableProducts() {
			private static final long serialVersionUID = 4585520177078296255L;
			@Override
			public void addRow(Row row) {
				row.addMouseListener(closeSearchFilterAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean enabled = e.getStateChange() == ItemEvent.SELECTED;
						btn_disposeProduct.setEnabled(enabled);
					}
				});
				super.addRow(row);
			}
		};
		setTable(table_products);
	}
}