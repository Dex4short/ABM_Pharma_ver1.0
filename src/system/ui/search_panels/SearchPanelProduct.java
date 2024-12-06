package system.ui.search_panels;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
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
}
