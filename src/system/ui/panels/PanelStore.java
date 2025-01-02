package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Padding;
import system._default_.Store;
import system.objects.Cart;
import system.objects.Counter;
import system.objects.Order;
import system.objects.Packaging;
import system.objects.Product;
import system.objects.Transaction;
import system.ui.UI3;
import system.ui.Window;
import system.ui.bars.BarFieldCart;
import system.ui.buttons.ordering.ButtonCheckOut;
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
	private ButtonCheckOut btn_check_out;
	
	public PanelStore() {
		((GridLayout)getLayout()).setVgap(20);
		
		SearchPanelProduct search_panel_product = new SearchPanelProduct() {
			private static final long serialVersionUID = 2632172827828109880L;
			@Override
			public void onSearchProducts(String category, String word) {
				// TODO Auto-generated method stub
			}
		};
		
		table_product_aisle = new TableAisle() {
			private static final long serialVersionUID = 4853936114446663558L;
			@Override
			public void onAddProductToCart(Packaging[] extracted_packs, Packaging sub_pack) { addToCart(getCart(), getSelectedProduct(), extracted_packs, sub_pack); }
		};
		table_product_aisle.setCheckBoxesEnabled(false);
		getUiTop().setSearchPanel(search_panel_product);
		getUiTop().setTable(table_product_aisle);
		getUiTop().getBarFields().setVisible(false);
		
		table_product_cart = new TableCart() {
			private static final long serialVersionUID = -6609667196159272458L;
			@Override
			public void onRemoveOrderFromCart(Packaging[] extracted_packs, Packaging sub_pack) { removeFromCart(getCart(), getSelectedOrder(),extracted_packs, sub_pack); }
		};
		table_product_cart.setCheckBoxesEnabled(false);
		getUiBottom().setTable(table_product_cart);
		
		bar_field_cart = new BarFieldCart() {
			private static final long serialVersionUID = 7478430299572033345L;
			@Override
			public Order[] getOrders() { return selectOrdersFromStore(); }
		};
		getUiBottom().getSearchPanel().setVisible(false);
		getUiBottom().setBarFields(bar_field_cart);
		
		btn_check_out = new ButtonCheckOut(bar_field_cart) {
			private static final long serialVersionUID = 7693465853429312072L;
			@Override
			public void onCheckOut(Transaction transaction) { checkOutFromStore(transaction); }
			@Override
			public Cart getCart() { return PanelStore.this.getCart(); }
			@Override
			public Counter getCounter() { return PanelStore.this.getCounter(); }
		};
		btn_check_out.setArc(20);
		btn_check_out.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		bar_field_cart.add(new Padding(btn_check_out, 4, 10, 4, 10), BorderLayout.EAST);
		
	}
	@Override
	public Order[] onSelectOrdersFromStore() {
		return table_product_cart.getOrders();
	}
	@Override
	public void onSearchFromStore() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onAddToCart(Order order) {
		cart.addOrder(order);
		loadAisleFromStore();
		loadCartFromStore(counter);
		Window.floatMessage(order.getProduct().getItem().getDescription() + " added to cart");
	}
	@Override
	public void onRemoveFromCart(Order order) {
		loadAisleFromStore();
		loadCartFromStore(counter);
		
		String qty = "";
		if(!order.getProduct().getPackaging().getQty().isOutOfStock()) {
			qty = " (" + order.getProduct().getPackaging().getQty() + ")";
		}
		Window.floatMessage(order.getProduct().getItem().getDescription() + " removed" + qty);
	}
	@Override
	public void onCheckOutFromStore() {
		table_product_cart.removeAllProducts();
		bar_field_cart.clearFields();
		openCounter(getCounter().getCounterNo());//reopen counter
		Window.floatMessage("Check Out Successful!");
	}
	@Override
	public void onLoadCartFromStore(Cart cart) {
		table_product_cart.removeAllProducts();
		table_product_cart.addOrders(cart.getOrders());
		bar_field_cart.calculateTotalAmount(cart.getOrders());
		btn_check_out.setEnabled(cart.getOrders().length > 0);
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
