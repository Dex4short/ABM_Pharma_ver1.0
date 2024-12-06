package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import components.table.Row;
import system.ui.UI1;
import system.ui.search_panels.SearchPanelProduct;
import system.ui.tables.TableProducts;

public class PanelCustomers extends UI1{
	private static final long serialVersionUID = -2756977805365927787L;
	private TableProducts table_products;
		
	public PanelCustomers() {
		SearchPanelProduct search_panel = new SearchPanelProduct() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onSearch(String category, String word) {
			}
		};	
		setSearchPanel(search_panel);
			
		table_products = new TableProducts() {
			private static final long serialVersionUID = 1L;
			@Override
			public void addRow(Row row) {
				row.addMouseListener(closeSearchFilterAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
					}
				});
				super.addRow(row);
			}
		};
		setTable(table_products);
	}
}
