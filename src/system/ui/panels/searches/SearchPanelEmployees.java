package system.ui.panels.searches;

import components.panels.SearchPanel;
import system.ui.tables.TableEmployees;

public abstract class SearchPanelEmployees extends SearchPanel{
	private static final long serialVersionUID = 1520220548320750270L;

	public SearchPanelEmployees() {
		setFilters(TableEmployees.fields);
		getComboBox().setSelectedItem(0);
	}
	@Override
	public void onSearch(String category, String word) {
		searchEmployee(category, word);;
	}
	public void searchEmployee(String category, String word) {
		onSearchEmployee(category, word);
	}

	public abstract void onSearchEmployee(String category, String word);

}
