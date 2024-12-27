package system._default_;

import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.objects.Remarks;

public interface Reserves {
	
	public default Product selectFromReserves() {
		return onSelectFromReserves();
	}
	public default Product[] selectManyFromReserves() {
		return onSelectManyFromReserves();
	}
	public default void searchFromInventory() {
		
		onSearchFromInventory();
	}
	public default void restoreFromReserves(Product product) {
		product.setProduct_condition(ProductCondition.STORED);
		MySQL_Products.updateProduct(product);
		onRestoreFromReserves(product);
	}
	public default void disposeFromReserves(Product product) {
		product.setProduct_condition(ProductCondition.DISPOSED);
		MySQL_Products.updateProduct(product);
		onDisposeFromReserves(product);
	}
	public default void showRemarks(Remarks remarks) {
		onShowRemarks(remarks);
	}
	public default void loadAllFromReserves() {
		onLoadAllFromReserves(MySQL_Products.selectProducts(ProductCondition.RESERVED));
	}

	public Product onSelectFromReserves();
	public Product[] onSelectManyFromReserves();
	public void onSearchFromInventory();
	public void onRestoreFromReserves(Product product);
	public void onDisposeFromReserves(Product product);
	public void onShowRemarks(Remarks remarks);
	public void onLoadAllFromReserves(Product products[]);

}

