package system.ui.search_panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.Label;
import components.list.Item;
import components.panels.SearchPanel;
import system.ui.tables.TableProducts;

public abstract class SearchPanelInventory extends SearchPanel{
	private static final long serialVersionUID = 5220074127562675499L;

	public SearchPanelInventory() {
		String fields[] = TableProducts.fields;
		for(String field: fields) {
			getComboBox().addItem(new Item(new Label(null, field)));
		}
		getComboBox().setSelectedItem(0);
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
