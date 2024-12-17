package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import system.ui.tables.TableProducts;

public abstract class SearchPanelProductReturns extends SearchPanelProduct{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelProductReturns() {
		String fields[] = TableProducts.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
}
