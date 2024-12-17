package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableProducts;

public abstract class SearchPanelDisposal extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelDisposal() {
		String fields[] = TableProducts.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
}
