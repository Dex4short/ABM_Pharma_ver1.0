package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.Window;
import system.ui.tables.TableProducts;

public abstract class SearchPanelProduct extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelProduct() {
		String fields[] = TableProducts.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
	@Override
	public void onSearch(String category, String word) {
		searchProducts(category, word);
	}
	public void searchProducts(String category, String word) {
		Window.load(() -> onSearchProducts(category, word) , "Searching Inventory...");
	}
	
	public abstract void onSearchProducts(String category, String word);
	
}
