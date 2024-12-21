package system.ui.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.table.Row;
import oop.Product;
import oop.Remarks;
import system._default_.Reserves;
import system.ui.UI4;
import system.ui.buttons.ButtonDisposeProduct;
import system.ui.buttons.ButtonRestoreProduct;
import system.ui.panels.searches.SearchPanelReserves;
import system.ui.tables.TableReserves;

public class PanelReserves extends UI4 implements Reserves{
	private static final long serialVersionUID = 2111188953454121901L;
	private TableReserves table_reserves;
	
	public PanelReserves() {
		SearchPanelReserves search_panel_reserves = new SearchPanelReserves() {
			private static final long serialVersionUID = 8347657403861608555L;
			@Override
			public void onSearch(String category, String word) {
				//TODO
			}
		};	
		setSearchPanel(search_panel_reserves);
		
		ButtonRestoreProduct btn_restoreProduct = new ButtonRestoreProduct() {
			private static final long serialVersionUID = 7002474584549803354L;
			@Override
			public void onRestoreProduct(Product product) { restoreFromReserves(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromReserves(); }
		};
		btn_restoreProduct.setEnabled(false);
		addButton(btn_restoreProduct);
		
		ButtonDisposeProduct btn_disposeProduct = new ButtonDisposeProduct() {
			private static final long serialVersionUID = 5138197980623655054L;
			@Override
			public void onDisposeProduct(Product product) {	disposeFromReserves(product); }
			@Override
			public Product[] getSelectedProducts() { return selectManyFromReserves(); }
		
		};
		btn_disposeProduct.setEnabled(false);
		addButton(btn_disposeProduct);

		table_reserves = new TableReserves() {
			private static final long serialVersionUID = 2404680536760409457L;
			@Override
			public void addRow(Row row) {
				listen(row);
				super.addRow(row);
			}
			@Override
			public void addRows(Row[] rows) {
				for(Row row: rows) {
					listen(row);
				}
				super.addRows(rows);
			}
			private void listen(Row row) {
				row.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showRemarks(getSelectedProduct().getRemarks());
					}
				});
				row.addMouseListener(closeSearchFilterAdapter());
				row.getCheckBox().addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean enable = getSelectedRows().length > 0;
						btn_disposeProduct.setEnabled(enable);
						btn_restoreProduct.setEnabled(enable);
					}
				});
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
	}
	@Override
	public void onDisposeFromReserves(Product product) {
		table_reserves.removeProduct(product);
		getParagraphField().setText("");
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
	public void onLoadAllFromReserves(Product[] products) {
		table_reserves.removeAllProducts();
		table_reserves.addProducts(products);
	}
}
	
