package system.ui.panels.searches;

import components.panels.SearchPanel;
import system.ui.Window;
import system.ui.tables.TableItems;

public abstract class SearchPanelItems extends SearchPanel{
	private static final long serialVersionUID = -2688695572777106576L;

	public SearchPanelItems() {
		setFilters(TableItems.fields);
		getComboBox().setSelectedItem(0);
	}
	@Override
	public void onSearch(String category, String word) {
		searchItem(category, word);
	}
	public void searchItem(String category, String word) {
		Window.load(() -> {
			onSearchItem(category, word);
		}, "Loading Items...");
		
	}
	
	public abstract void onSearchItem(String category, String word);

}
