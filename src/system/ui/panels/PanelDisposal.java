package system.ui.panels;

import components.Button;
import components.table.Row;
import system._default_.Disposal;
import system.objects.Product;
import system.objects.Remarks;
import system.ui.UI4;
import system.ui.Window;
import system.ui.buttons.ButtonDeleteProduct;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.buttons.ButtonRestoreProduct;
import system.ui.panels.searches.SearchPanelDisposal;
import system.ui.tables.TableDisposals;

public class PanelDisposal extends UI4 implements Disposal{
	private static final long serialVersionUID = -3497678071480750501L;
	private TableDisposals table_disposals;
	private Button btn_restoreProduct, btn_reserveProduct, btn_disposeProduct;
	
	public PanelDisposal() {
		SearchPanelDisposal search_panel_disposal = new SearchPanelDisposal() {
			private static final long serialVersionUID = -154869804767992665L; 
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel_disposal);
		
		btn_restoreProduct = new ButtonRestoreProduct() {
			private static final long serialVersionUID = 8773399964459745031L;
			@Override
			public void onRestoreProduct(Product product) {	restoreFromDisposal(product);}
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		addButton(btn_restoreProduct);
		
		btn_reserveProduct = new ButtonReserveProduct() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onReserveProduct(Product product) { reserveFromDisposal(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		addButton(btn_reserveProduct);
		
		btn_disposeProduct = new ButtonDeleteProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDeleteProduct(Product product) {	deleteFromDisposal(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		addButton(btn_disposeProduct);

		table_disposals = new TableDisposals() {
			private static final long serialVersionUID = 3129100619823684380L;
			@Override
			public void onSelectRow(Row row) {
				getParagraphField().setText("");
				Row rows[] = getSelectedRows();
				
				boolean enable = rows.length > 0;
				btn_restoreProduct.setEnabled(enable);
				btn_disposeProduct.setEnabled(enable);
				btn_reserveProduct.setEnabled(enable);

				Product product = getSelectedProduct();
				if(product != null) {
					showRemarks(product.getRemarks()); 
				}
			}
			@Override
			public void onPointRow(Row row) {
				search_panel_disposal.closeSearchFilter();
			}
		};
		setTable(table_disposals);
		
		getParagraphField().setText("");
	}
	@Override
	public Product onSelectFromDisposal() {
		return table_disposals.getSelectedProduct();
	}
	@Override
	public Product[] onSelectManyFromDisposal() {
		return table_disposals.getSelectedProducts();
	}
	@Override
	public void onSearchFromDisposal() {
		getParagraphField().setText("");
	}
	@Override
	public void onRestoreFromDisposal(Product product) {
		table_disposals.removeProduct(product);
		getParagraphField().setText("");
		Window.floatMessage(product.getItem().getDescription() + " restored");
	}
	@Override
	public void onReserveFromDisposal(Product product) {
		table_disposals.removeProduct(product);
		getParagraphField().setText("");
		Window.floatMessage(product.getItem().getDescription() + " reserved");
	}
	@Override
	public void onDeleteFromDisposal(Product product) {
		table_disposals.removeProduct(product);
		getParagraphField().setText("");
		Window.floatMessage(product.getItem().getDescription() + " deleted");
	}
	@Override
	public void onShowRemarks(Remarks remarks) {
		getParagraphField().setText(
				"Date:\t" + remarks.getDate().toString() + "\n" +
				"Time:\t" + remarks.getTime().toString() + "\n" + 
				"Remarks:\t" + remarks.getDetails()
		);
	}
	@Override
	public void onLoadAllFromDisposal(Product disposed_products[]) {
		btn_restoreProduct.setEnabled(false);
		btn_reserveProduct.setEnabled(false);
		btn_disposeProduct.setEnabled(false);
		
		table_disposals.removeAllProducts();
		table_disposals.addProducts(disposed_products);

		getParagraphField().setText("");
	}
}