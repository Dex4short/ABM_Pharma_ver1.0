package system._default_;

import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.objects.Remarks;

public interface ProductReturns {
	
	public default void searchFromReturnedProducts() {
		
		onSearchFromReturnedProducts();
	}
	public default void showRemarks(Remarks remarks) {
		onShowRemarks(remarks);
	}
	public default void loadAllReturnedProducts() {
		onLoadAllReturnedProducts(MySQL_Products.selectProducts(ProductCondition.RETURNED));
	}

	public void onSearchFromReturnedProducts();
	public void onShowRemarks(Remarks remarks);
	public void onLoadAllReturnedProducts(Product products[]);
}

