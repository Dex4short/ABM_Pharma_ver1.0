package system._default_;

public interface ProductReturns {
	
	public default void searchFromReturnedProducts() {
		
		onSearchFromReturnedProducts();
	}
	public default void loadAllReturnedProducts() {
		
		onLoadAllReturnedProducts();
	}

	public void onSearchFromReturnedProducts();
	public void onLoadAllReturnedProducts();
}
