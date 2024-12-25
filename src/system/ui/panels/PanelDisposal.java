package system.ui.panels;

import components.table.Row;
import oop.Product;
import oop.Remarks;
import system._default_.Disposal;
import system.ui.UI4;
import system.ui.buttons.ButtonDeleteProduct;
import system.ui.buttons.ButtonReserveProduct;
import system.ui.buttons.ButtonRestoreProduct;
import system.ui.panels.searches.SearchPanelDisposal;
import system.ui.tables.TableDisposals;

public class PanelDisposal extends UI4 implements Disposal{
	private static final long serialVersionUID = -3497678071480750501L;
	private TableDisposals table_disposals;
	
	public PanelDisposal() {
		SearchPanelDisposal search_panel_disposal = new SearchPanelDisposal() {
			private static final long serialVersionUID = -154869804767992665L; 
			@Override
			public void onSearch(String category, String word) {
				
			}
		};	
		setSearchPanel(search_panel_disposal);
		
		ButtonRestoreProduct btn_restoreProduct = new ButtonRestoreProduct() {
			private static final long serialVersionUID = 8773399964459745031L;
			@Override
			public void onRestoreProduct(Product product) {	restoreFromDisposal(product);}
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		btn_restoreProduct.setEnabled(false);
		addButton(btn_restoreProduct);
		
		ButtonReserveProduct btn_reserveProduct = new ButtonReserveProduct() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onReserveProduct(Product product) { reserveFromDisposal(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		btn_reserveProduct.setEnabled(false);
		addButton(btn_reserveProduct);
		
		ButtonDeleteProduct btn_disposeProduct = new ButtonDeleteProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDeleteProduct(Product product) {	deleteFromDisposal(); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromDisposal(); }
		};
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);

		table_disposals = new TableDisposals() {
			private static final long serialVersionUID = 3129100619823684380L;
			@Override
			public void onSelectRow(Row row) {
				Row rows[] = getSelectedRows();
				boolean enable = rows.length > 0;
				btn_restoreProduct.setEnabled(enable);
				btn_disposeProduct.setEnabled(enable);
				btn_reserveProduct.setEnabled(enable);
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
		// TODO Auto-generated method stub
	}
	@Override
	public void onRestoreFromDisposal(Product product) {
		table_disposals.removeProduct(product);
		getParagraphField().setText("");
	}
	@Override
	public void onReserveFromDisposal(Product product) {
		table_disposals.removeProduct(product);
		getParagraphField().setText("");
	}
	@Override
	public void onDeleteFromDisposal() {
		// TODO Auto-generated method stub
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
		table_disposals.removeAllProducts();
		table_disposals.addProducts(disposed_products);
	}
}