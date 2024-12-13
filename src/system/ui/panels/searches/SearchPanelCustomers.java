package system.ui.panels.searches;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableCustomers;

public class SearchPanelCustomers extends SearchPanel{
	private static final long serialVersionUID = 1520220548320750270L;

	public SearchPanelCustomers() {
		String fields[] = TableCustomers.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
	}
	@Override
	public void onSearch(String category, String word) {
		
	}


}
