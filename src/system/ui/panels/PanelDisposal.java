package system.ui.panels;

import oop.Product;
import system.ui.UI4;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.buttons.ButtonReturnToInventory;
import system.ui.panels.searches.SearchPanelDisposal;
import system.ui.tables.TableDisposals;

public class PanelDisposal extends UI4{
	private static final long serialVersionUID = -3497678071480750501L;
	private TableDisposals table_disposals;
	
	public PanelDisposal() {
		SearchPanelDisposal search_panel = new SearchPanelDisposal() {
			private static final long serialVersionUID = -154869804767992665L; 
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel);
		
		ButtonReturnToInventory btn_return_to_inventory = new ButtonReturnToInventory() {
			private static final long serialVersionUID = 8773399964459745031L;
			@Override
			public void onReturnProductToInventory(Product product) {
				// TODO Auto-generated method stub
			}
			@Override
			public Product getSelectedProduct() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		btn_return_to_inventory.setEnabled(false);
		addButton(btn_return_to_inventory);
		ButtonReserveProduct btn_reserveProduct = new ButtonReserveProduct() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onReserveProduct(Product product) { table_disposals.removeProduct(product); }
			@Override
			public Product getSelectedProduct() { return table_disposals.getSelectedProduct(); }
		};
		btn_reserveProduct.setEnabled(false);
		addButton(btn_reserveProduct);
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDisposeProduct(Product product) {	table_disposals.removeProduct(product);	}
			@Override
			public Product getSelectedProduct() { return table_disposals.getSelectedProduct(); }
		};
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);

		table_disposals = new TableDisposals();
		setTable(table_disposals);
	}
}