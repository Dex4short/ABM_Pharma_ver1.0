package system.ui.panels.sub_panels;

import components.table.Row;
import system._default_.ProductReturns;
import system.objects.Product;
import system.objects.Remarks;
import system.ui.UI4;
import system.ui.panels.searches.SearchPanelProductReturns;
import system.ui.tables.TableProductReturns;

public class PanelProductReturns extends UI4 implements ProductReturns{
	private static final long serialVersionUID = 2528135928531491388L;
	private TableProductReturns table_products;
	
	public PanelProductReturns() {
		SearchPanelProductReturns search_panel = new SearchPanelProductReturns() {
			private static final long serialVersionUID = 8125329913229320000L;
			@Override
			public void onSearchProducts(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel);
		
		table_products = new TableProductReturns() {
			private static final long serialVersionUID = -7923323999448823744L;
			@Override
			public void onSelectRow(Row row) { 
				getParagraphField().setText("");
				Product product = getSelectedProduct();
				if(product != null) {
					showRemarks(product.getRemarks()); 
				}
			}
		};
		setTable(table_products);

		getParagraphField().setText("");
	}
	@Override
	public void onSearchFromReturnedProducts() {
		getParagraphField().setText("");
	}
	@Override
	public void onShowRemarks(Remarks remarks) {
		if(remarks != null) getParagraphField().setText(remarks.toStringFullDetails());
		else getParagraphField().setText("");
	}
	@Override
	public void onLoadAllReturnedProducts(Product products[]) {
		table_products.removeAllProducts();
		table_products.addProducts(products);
		getParagraphField().setText("");
	}
}