package system._default_;

import database.MySQL_Cart;
import database.MySQL_Counter;
import database.MySQL_Orders;
import database.MySQL_Products;
import database.MySQL_Transactions;
import oop.Cart;
import oop.Counter;
import oop.Order;
import oop.Packaging;
import oop.Product;
import oop.Transaction;
import oop.enumerations.ProductCondition;
import oop.essentials.Accountancy;

public interface Store {
	
	public default void searchFromStore() {
		
		onSearchFromStore();
	}
	public default void addToCart(Cart cart, Product main_product, Packaging[] extracted_packs, Packaging sub_pack) {		
		Product sub_product = new Product(-1, main_product.getItem(), null, null, null);
		
		main_product.getPackaging().setQty(extracted_packs[0].getQty());
		if(main_product.getPackaging().getParentPackId() == -1) MySQL_Products.updateProduct(main_product); //if the main(selected) product is the parent ancestor
		else MySQL_Products.updateProduct(main_product, (main_product.getPackaging().getQty().getAmount() != 0) ? ProductCondition.STORED : ProductCondition.ARCHIVED); //if the main(selected) product is the child ancestor
		
		if(main_product.getPackaging().getUom().getUnitType() == sub_pack.getUom().getUnitType()) {
			sub_pack.setParentPackId(main_product.getPackaging().getPackId());
			sub_product.setPackaging(sub_pack);
			sub_product.setPricing(main_product.getPricing());
		}

		Product product_children[] = MySQL_Products.selectSubProducts(main_product);
		
		for(int n=0; n<product_children.length; n++) {
			product_children[n].getPackaging().getQty().add(extracted_packs[n+1].getQty());
			MySQL_Products.updateProduct(product_children[n], (product_children[n].getPackaging().getQty().getAmount() != 0) ? ProductCondition.STORED : ProductCondition.ARCHIVED);
			
			if(product_children[n].getPackaging().getUom().getUnitType() == sub_pack.getUom().getUnitType()) {
				sub_pack.setParentPackId(product_children[n].getPackaging().getPackId());
				sub_product.setPackaging(sub_pack);
				sub_product.setPricing(product_children[n].getPricing());
			}
		}
		
		Order order = new Order(cart.getOrderNo(), sub_product, Accountancy.calculateNetAmount(sub_product));
		cart.addOrder(order);
		onAddToCart(MySQL_Orders.insertOrder(order));
	}
	public default void removeFromCart() {
		
		onRemoveFromCart();
	}
	public default void checkOutFromStore(Transaction transaction) {
		MySQL_Transactions.instertTransaction(transaction);
		onCheckOutFromStore();
	}
	public default void loadCartFromStore(Counter counter) {
		onLoadCartFromStore(MySQL_Cart.selectCart(counter.getCurrentCartNo(), counter.getCounterNo()));
	}
	public default void loadAisleFromStore() {
		onLoadAisleFromStore(MySQL_Products.selectProducts(ProductCondition.STORED));
	}
	public default void openCart(int currentCart_no, int counter_no) {
		onOpenCart(MySQL_Cart.selectCart(currentCart_no, counter_no));
	}
	public default void openCounter(int counter_no) {
		Counter counter = MySQL_Counter.selectCounter(counter_no);
		MySQL_Counter.updateCounter(counter);
		onOpenCounter(counter);
	}

	public void onSearchFromStore();
	public void onAddToCart(Order order);
	public void onRemoveFromCart();
	public void onCheckOutFromStore();
	public void onLoadAisleFromStore(Product products[]);
	public void onLoadCartFromStore(Cart cart);
	public void onOpenCart(Cart cart);
	public void onOpenCounter(Counter counter);
	
}

