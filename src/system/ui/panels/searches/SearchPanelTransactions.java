package system.ui.panels.searches;

import java.awt.Dimension;
import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.Window;
import system.ui.tables.TableTransactions;

public abstract class SearchPanelTransactions extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelTransactions() {
		setPreferredSize(new Dimension(400, 30));
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		
		getComboBox().setPreferredSize(new Dimension(150, 30));
		getComboBox().setMinimumSize(getPreferredSize());
		getComboBox().setMaximumSize(getPreferredSize());
		String fields[] = TableTransactions.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
		
		getSearchField().setPreferredSize(new Dimension(250, 30));
		getSearchField().setMinimumSize(getPreferredSize());
		getSearchField().setMaximumSize(getPreferredSize());
	}
	@Override
	public void onSearch(String category, String word) {
		searchTransactions(category, word);
	}
	public void searchTransactions(String category, String word) {
		Window.load(() -> onSearchTransactions(category, word) , "Searching transaction record...");
	}
	
	public abstract void onSearchTransactions(String category, String word);
	
}
