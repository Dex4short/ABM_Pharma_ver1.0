package system.ui.panels;

import system.ui.UI4;
import system.ui.panels.searches.SearchPanelProductReturns;
import system.ui.tables.TableProductReturns;

public class PanelProductReturns extends UI4{
	private static final long serialVersionUID = 2528135928531491388L;
	private TableProductReturns table_products;
	
	public PanelProductReturns() {
		SearchPanelProductReturns search_panel = new SearchPanelProductReturns() {
			private static final long serialVersionUID = 8125329913229320000L;
			@Override
			public void onSearch(String category, String word) {
			
			}
		};	
		setSearchPanel(search_panel);
		
		table_products = new TableProductReturns();
		setTable(table_products);
	}
}