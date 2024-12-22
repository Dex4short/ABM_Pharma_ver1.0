package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableProducts;

public abstract class SearchPanelReserves extends SearchPanel{
	private static final long serialVersionUID = -4349221592322887330L;

	public SearchPanelReserves() {
		String fields[] = TableProducts.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
}

