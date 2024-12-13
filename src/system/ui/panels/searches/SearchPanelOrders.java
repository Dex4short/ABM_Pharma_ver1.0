package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableOrders;

public abstract class SearchPanelOrders extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelOrders() {
		String fields[] = TableOrders.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
}
