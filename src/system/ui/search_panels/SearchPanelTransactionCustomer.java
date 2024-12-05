package system.ui.search_panels;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableTransactions;

public abstract class SearchPanelTransactionCustomer extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelTransactionCustomer() {
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
	public MouseAdapter closeSearchAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				getComboBox().close();
			}
		};
	}
}
