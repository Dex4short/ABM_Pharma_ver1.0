package system._default_;

public interface Store {
	
	public default void searchFromInventory() {
		
		onSearchFromInventory();
	}
	public default void addToCart() {
		
			onAddToCart();
	}
	public default void removeFromCart() {
		
		onRemoveFromCart();
}
	public default void Checkout() {
		
		onCheckout();
}
	public default void loadAllProducts() {
		
		onLoadAllProducts();
	}

	public void onSearchFromInventory();
	public void onAddToCart();
	public void onRemoveFromCart();
	public void onCheckout();
	public void onLoadAllProducts();
}

