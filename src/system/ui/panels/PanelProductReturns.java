package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import components.table.Row;
import system.ui.UI1;
import system.ui.search_panels.SearchPanelProduct;
import system.ui.tables.TableProducts;

public class PanelProductReturns extends UI1{
	private static final long serialVersionUID = 2528135928531491388L;
	private TableProducts table_products;
	
	public PanelProductReturns() {
		SearchPanelProduct search_panel = new SearchPanelProduct() {
			private static final long serialVersionUID = 8125329913229320000L;
			@Override
			public void onSearch(String category, String word) {
			}
		};	
		setSearchPanel(search_panel);
		
		table_products = new TableProducts() {
			private static final long serialVersionUID = 4585520177078296255L;
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