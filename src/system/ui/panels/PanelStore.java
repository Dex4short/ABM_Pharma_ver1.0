package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Button;
import components.Padding;
import oop.Cart;
import oop.Counter;
import oop.Order;
import oop.Packaging;
import oop.Product;
import system._default_.Store;
import system.ui.UI3;
import system.ui.Window;
import system.ui.bars.BarFieldCart;
import system.ui.panels.searches.SearchPanelProduct;
import system.ui.tables.TableAisle;
import system.ui.tables.TableCart;

public class PanelStore extends UI3 implements Store{
	private static final long serialVersionUID = 169037234144357398L;
	private TableAisle table_product_aisle;
	private TableCart table_product_cart;
	private BarFieldCart bar_field_cart;
	private Counter counter;
	private Cart cart;

	public PanelStore() {
		((GridLayout)getLayout()).setVgap(20);
		
		SearchPanelProduct search_panel_product = new SearchPanelProduct() {
			private static final long serialVersionUID = 2632172827828109880L;
			@Override
			public void onSearch(String category, String word) {
				// TODO Auto-generated method stub
			}
		};
		
		table_product_aisle = new TableAisle() {
			private static final long serialVersionUID = 4853936114446663558L;
			@Override
			public void onAddProductToCart(Packaging[] extracted_packs, Packaging sub_pack) {
				addToCart(getCart(), getSelectedProduct(), extracted_packs, sub_pack);
			}
		};
		getUiTop().setSearchPanel(search_panel_product);
		getUiTop().setTable(table_product_aisle);
		getUiTop().getBarFields().setVisible(false);
		
		table_product_cart = new TableCart();
		getUiBottom().setTable(table_product_cart);
		
		bar_field_cart = new BarFieldCart();
		getUiBottom().getSearchPanel().setVisible(false);
		getUiBottom().setBarFields(bar_field_cart);
		
		Button.Quaternary btn_check_out = new Button.Quaternary("Check Out");
		btn_check_out.setArc(20);
		btn_check_out.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		bar_field_cart.add(new Padding(btn_check_out, 4, 10, 4, 10), BorderLayout.EAST);
		
	}
	@Override
	public void onSearchFromStore() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onAddToCart(Order order) {
		loadAisleFromStore();
		
		table_product_cart.addOrder(order);
		Window.floatMessage(order.getProduct().getItem().getDescription() + " added to cart");
	}
	@Override
	public void onRemoveFromCart() {
		// TODO Auto-generated method
	}
	@Override
	public void onCheckout() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onLoadCartFromStore(Cart cart) {
		table_product_cart.removeAllProducts();
		table_product_cart.addOrders(cart.getOrders());
	}
	@Override
	public void onLoadAisleFromStore(Product products[]) {
		table_product_aisle.removeAllProducts();
		table_product_aisle.addProducts(products);
	}
	@Override
	public void onOpenCart(Cart cart) {
		setCart(cart);
	}
	@Override
	public void onOpenCounter(Counter counter) {
		setCounter(counter);
	}
	public Counter getCounter() {
		return counter;
	}
	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
}
