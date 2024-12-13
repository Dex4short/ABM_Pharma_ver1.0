package system.ui.panels;

import oop.Product;
import system.ui.UI4;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonReturnToInventory;
import system.ui.panels.searches.SearchPanelReserves;
import system.ui.tables.TableReserves;

public class PanelReserves extends UI4{
	private static final long serialVersionUID = 2111188953454121901L;
	private TableReserves table_reserves;
	
	public PanelReserves() {
		SearchPanelReserves search_panel_reserves = new SearchPanelReserves() {
			private static final long serialVersionUID = 8347657403861608555L;
			@Override
			public void onSearch(String category, String word) {
				//TODO
			}
		};	
		setSearchPanel(search_panel_reserves);
		
		ButtonReturnToInventory btn_return_to_inventory = new ButtonReturnToInventory() {
			private static final long serialVersionUID = 7002474584549803354L;
			@Override
			public void onReturnProductToInventory(Product product) { }
			@Override
			public Product getSelectedProduct() { return null; }
		};
		btn_return_to_inventory.setEnabled(false);
		addButton(btn_return_to_inventory);
		
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDisposeProduct(Product product) {	table_reserves.removeProduct(product);	}
			@Override
			public Product getSelectedProduct() { return table_reserves.getSelectedProduct(); }
		
		};
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);

		table_reserves = new TableReserves();
		setTable(table_reserves);
	}
}
	
