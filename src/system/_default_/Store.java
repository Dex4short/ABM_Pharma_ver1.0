package system._default_;

import database.MySQL_Cart;
import database.MySQL_Counter;
import database.MySQL_Orders;
import database.MySQL_Packaging;
import database.MySQL_Products;
import database.MySQL_Transactions;
import system.enumerators.ProductCondition;
import system.managers.AccountancyManager;
import system.objects.Cart;
import system.objects.Counter;
import system.objects.Order;
import system.objects.Packaging;
import system.objects.Product;
import system.objects.Transaction;

public interface Store {
	
	public default Order[] selectOrdersFromStore() {
		return onSelectOrdersFromStore();
	}
	public default void searchFromStore() {
		
		onSearchFromStore();
	}
	public default void addToCart(Cart cart, Product main_product, Packaging[] extracted_packs, Packaging sub_pack) {		
		Product sub_product = new Product(-1, main_product.getItem(), null, null, null, null);
		
		main_product.getPackaging().setQty(extracted_packs[0].getQty());
		if(main_product.getPackaging().getParentPackId() == -1) { //if the main(selected) product is the parent ancestor
			MySQL_Products.updateProduct(main_product);
		}
		else { //if the main(selected) product is the child ancestor
			main_product.setProduct_condition((main_product.getPackaging().getQty().getAmount() != 0) ? ProductCondition.STORED : ProductCondition.ARCHIVED);
			MySQL_Products.updateProduct(main_product);
		}
		
		if(main_product.getPackaging().getUom().getUnitType() == sub_pack.getUom().getUnitType()) {
			sub_pack.setParentPackId(main_product.getPackaging().getPackId());
			sub_product.setPackaging(sub_pack);
			sub_product.setPricing(main_product.getPricing());
		}

		Product product_children[] = MySQL_Products.selectSubProducts(main_product);
		
		for(int n=0; n<product_children.length; n++) {
			product_children[n].getPackaging().getQty().add(extracted_packs[n+1].getQty());
			product_children[n].setProduct_condition((product_children[n].getPackaging().getQty().getAmount() != 0) ? ProductCondition.STORED : ProductCondition.ARCHIVED);
			MySQL_Products.updateProduct(product_children[n]);
			
			if(product_children[n].getPackaging().getUom().getUnitType() == sub_pack.getUom().getUnitType()) {
				sub_pack.setParentPackId(product_children[n].getPackaging().getPackId());
				sub_product.setPackaging(sub_pack);
				sub_product.setPricing(product_children[n].getPricing());
			}
		}
		
		sub_product.getPackaging().setPackagingGroup(main_product.getPackaging().getPackagingGroup());
		
		Order order = new Order(cart.getOrderNo(), sub_product, AccountancyManager.calculateNetAmount(sub_product));
		Order orders[] = selectOrdersFromStore();
		boolean hassExistingOrder = false;
		for(Order next_order: orders) {
			hassExistingOrder = next_order.getProduct().getPackaging().getParentPackId() == order.getProduct().getPackaging().getParentPackId();
			
			if(hassExistingOrder) {
				next_order.getProduct().getPackaging().getQty().add(order.getProduct().getPackaging().getQty());
				next_order.getNetAmount().add(order.getNetAmount());
				
				order.setProduct(next_order.getProduct());
				order.setNetAmount(AccountancyManager.calculateNetAmount(order));
				break;
			}
		}
		
		if(order.getProduct().getProdId()==-1) 
			MySQL_Orders.insertOrder(order);
		else 
			MySQL_Orders.updateOrder(order);
		
		onAddToCart(order);
	}
	public default void removeFromCart(Cart cart, Product main_product,Packaging[] extracted_packs, Packaging sub_pack) {
		System.out.println("extracted packs------------------------------------------");
		for(Packaging extracted_pack: extracted_packs) {
			System.out.println(extracted_pack.toString());
		}
		System.out.println("sub pack-------------------------------------------------");
		System.out.println(sub_pack.toString());
		
		Packaging ancestor_packs[]= MySQL_Packaging.selectPackagings(main_product.getPackaging().getPackagingLine(), main_product.getPackaging().getPackagingGroup());
		for(Packaging ancestor_pack: ancestor_packs) {
			if(ancestor_pack.getPackId() == main_product.getPackaging().getParentPackId()) {
				System.out.println("match");
			}
		}
		
		onRemoveFromCart(extracted_packs, sub_pack);
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

	public Order[] onSelectOrdersFromStore();
	public void onSearchFromStore();
	public void onAddToCart(Order order);
	public void onRemoveFromCart(Packaging[] extracted_packs, Packaging sub_pack);
	public void onCheckOutFromStore();
	public void onLoadAisleFromStore(Product products[]);
	public void onLoadCartFromStore(Cart cart);
	public void onOpenCart(Cart cart);
	public void onOpenCounter(Counter counter);
	
}

