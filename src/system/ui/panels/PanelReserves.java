package system.ui.panels;

import components.Button;
import components.table.Row;
import system._default_.Reserves;
import system.managers.NotificationsManager;
import system.objects.Product;
import system.objects.Remarks;
import system.ui.UI4;
import system.ui.Window;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonRestoreProduct;
import system.ui.panels.searches.SearchPanelReserves;
import system.ui.tables.TableReserves;

public class PanelReserves extends UI4 implements Reserves{
	private static final long serialVersionUID = 2111188953454121901L;
	private TableReserves table_reserves;
	private Button btn_restoreProduct, btn_disposeProduct;
	
	public PanelReserves() {
		SearchPanelReserves search_panel_reserves = new SearchPanelReserves() {
			private static final long serialVersionUID = 8347657403861608555L;
			@Override
			public void onSearch(String category, String word) {
				//TODO
			}
		};	
		setSearchPanel(search_panel_reserves);
		
		btn_restoreProduct = new ButtonRestoreProduct() {
			private static final long serialVersionUID = 7002474584549803354L;
			@Override
			public void onRestoreProduct(Product product) { restoreFromReserves(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromReserves(); }
		};
		addButton(btn_restoreProduct);
		
		btn_disposeProduct = new ButtonDisposeProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDisposeProduct(Product product) {	disposeFromReserves(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromReserves(); }
		};
		addButton(btn_disposeProduct);

		table_reserves = new TableReserves() {
			private static final long serialVersionUID = 2404680536760409457L;
			@Override
			public void onSelectRow(Row row) {
				getParagraphField().setText("");
				boolean enable = getSelectedRows().length > 0;
				btn_disposeProduct.setEnabled(enable);
				btn_restoreProduct.setEnabled(enable);
			}
			@Override
			public void onPointRow(Row row) {
				search_panel_reserves.closeSearchFilter();
			}
		};
		setTable(table_reserves);
		
		getParagraphField().setText("");
	}
	@Override
	public Product onSelectFromReserves() {
		return table_reserves.getSelectedProduct();
	}
	@Override
	public Product[] onSelectManyFromReserves() {
		return table_reserves.getSelectedProducts();
	}
	@Override
	public void onSearchFromInventory() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onRestoreFromReserves(Product product) {
		table_reserves.removeProduct(product);
		getParagraphField().setText("");
		Window.floatMessage(product.getItem().getDescription() + " restored");
	}
	@Override
	public void onDisposeFromReserves(Product product) {
		table_reserves.removeProduct(product);
		getParagraphField().setText("");
		Window.floatMessage(product.getItem().getDescription() + " disposed");
	}
	@Override
	public void onShowRemarks(Remarks remarks) {
		getParagraphField().setText(remarks.toStringFullDetails());
	}
	@Override
	public void onLoadAllFromReserves(Product[] products) {
		NotificationsManager.clearNotifications();
		PanelAdmin.notifyTab(3, false);
		
		btn_restoreProduct.setEnabled(false);
		btn_disposeProduct.setEnabled(false);
		
		table_reserves.removeAllProducts();
		table_reserves.addProducts(products);
	}
}
	
