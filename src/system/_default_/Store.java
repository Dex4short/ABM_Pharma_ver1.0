package system._default_;

import database.MySQL_Cart;
import database.MySQL_Counter;
import database.MySQL_Orders;
import database.MySQL_Packaging;
import database.MySQL_Products;
import database.MySQL_Transactions;
import system.enumerators.PackagingLine;
import system.enumerators.ProductCondition;
import system.managers.AccountancyManager;
import system.objects.Cart;
import system.objects.Counter;
import system.objects.Order;
import system.objects.Packaging;
import system.objects.Product;
import system.objects.Quantity;
import system.objects.Transaction;
import system.printers.ReciptPrinter;

public interface Store {
	
	public default Order[] selectOrdersFromStore() {
		return onSelectOrdersFromStore();
	}
	public default Cart getCart() {
		return onGetCart();
	}
	public default void searchFromStore(String category, String word) {
		String keys[][] = {
			{"Item No.", "item_no"},
			{"Description", "description"},
			{"Lot No.", "lot_no"},
			{"Date Added", "date_added"},
			{"Exp Date", "exp_date"},
			{"Brand", "brand"},
			{"Quantity", "qty"},
			{"UOM", "u.name"},
			{"Cost", "cost"},
			{"Unit Price", "unit_price"},
			{"Discount", "discount"},
			{"Unit Amount", "unit_amount"}
		};
		
		int k;
		for(k=0; k<keys.length; k++) {
			if(keys[k][0].equals(category)) break;
		}
		
		if(k == 6) {
			word = " = " + word;
		}
		else {
			word = " like '%" + word + "%'";
		}
		onSearchFromStore(MySQL_Products.selectProducts(keys[k][1], word, ProductCondition.STORED));
	}
	public default void addToCart(Product main_product, Packaging[] extracted_packs, Packaging sub_pack) {		
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
		
		Order order = new Order(getCart().getOrderNo(), sub_product, AccountancyManager.calculateNetAmount(sub_product));
		Order orders[] = selectOrdersFromStore();
		
		boolean hassExistingOrder = false;
		if(orders != null) {
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
		}
		
		if(!hassExistingOrder) 
			MySQL_Orders.insertOrder(order);
		else 
			MySQL_Orders.updateOrder(order);
		
		onAddToCart(order);
	}
	public default void removeFromCart(Order main_order, Packaging[] extracted_packs, Packaging sub_pack) {		
		Packaging main_order_pack = main_order.getProduct().getPackaging();
		sub_pack.setParentPackId(main_order_pack.getParentPackId());
		
		Packaging ancestor_packs[] = MySQL_Packaging.selectPackagings(PackagingLine.Ancestor, main_order_pack.getPackagingGroup());
		boolean hasOrigin = false;
		if(ancestor_packs != null) {
			for(Packaging ancestor_pack: ancestor_packs) {
				hasOrigin = ancestor_pack.getPackId() == main_order_pack.getParentPackId();
				
				if(hasOrigin) {
					ancestor_pack.getQty().add(sub_pack.getQty());
					break;
				}
			}
		}
		
		if(!hasOrigin) throw new RuntimeException("The origin no longer exists, unable to return product...");

		extracted_packs[0].setParentPackId(main_order_pack.getParentPackId());
		if(extracted_packs[0].getQty().isOutOfStock()) {
			MySQL_Orders.deleteOrder(main_order);
		}
		else {
			MySQL_Packaging.updatePackaging(extracted_packs[0]);
		}
		
		int	amount, uom_size, modulo, quotient;
		for(int a=ancestor_packs.length-1; a>0; a--) {
			amount = ancestor_packs[a].getQty().getAmount();
			uom_size = ancestor_packs[a].getUom().getUnitSize();
			
			modulo = amount % uom_size;
			quotient = amount / uom_size;
			
			ancestor_packs[a].setQty(new Quantity(modulo));
			ancestor_packs[a-1].getQty().add(new Quantity(quotient));

			MySQL_Packaging.updatePackaging(ancestor_packs[a]);
			MySQL_Products.updateProduct(ancestor_packs[a].getPackId(), (modulo == 0) ? ProductCondition.ARCHIVED : ProductCondition.STORED);
		}
		MySQL_Packaging.updatePackaging(ancestor_packs[0]);
		
		onRemoveFromCart(main_order);
	}
	public default void checkOutFromStore(Transaction transaction) {
		MySQL_Transactions.instertTransaction(transaction);
		ReciptPrinter.printReceipt(transaction);
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
	public Cart onGetCart();
	public void onSearchFromStore(Product products[]);
	public void onAddToCart(Order order);
	public void onRemoveFromCart(Order order);
	public void onCheckOutFromStore();
	public void onLoadAisleFromStore(Product products[]);
	public void onLoadCartFromStore(Cart cart);
	public void onOpenCart(Cart cart);
	public void onOpenCounter(Counter counter);
	
}

