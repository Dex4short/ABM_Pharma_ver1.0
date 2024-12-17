package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Button;
import components.Padding;
import system.ui.UI3;
import system.ui.bars.BarFieldCart;
import system.ui.panels.searches.SearchPanelProduct;
import system.ui.tables.TableAisle;
import system.ui.tables.TableCart;

public class PanelStore extends UI3 {
	private static final long serialVersionUID = 169037234144357398L;

	public PanelStore() {
		((GridLayout)getLayout()).setVgap(20);
		
		SearchPanelProduct search_panel_product = new SearchPanelProduct() {
			private static final long serialVersionUID = 2632172827828109880L;
			@Override
			public void onSearch(String category, String word) {
				// TODO Auto-generated method stub
			}
		};
		TableAisle table_product_aisle = new TableAisle ();
		getUiTop().setSearchPanel(search_panel_product);
		getUiTop().setTable(table_product_aisle);
		getUiTop().getBarFields().setVisible(false);
		
		TableCart table_product_cart = new TableCart();
		BarFieldCart bar_field_cart = new BarFieldCart();
		getUiBottom().getSearchPanel().setVisible(false);
		getUiBottom().setTable(table_product_cart);	
		getUiBottom().setBarFields(bar_field_cart);
		
		Button.Quaternary btn_check_out = new Button.Quaternary("Check Out");
		btn_check_out.setArc(20);
		btn_check_out.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		bar_field_cart.add(new Padding(btn_check_out, 4, 10, 4, 10), BorderLayout.EAST);
	}
	
}
