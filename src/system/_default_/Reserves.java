package system._default_;

import database.MySQL_Products;
import oop.Product;
import oop.Remarks;
import oop.enumerations.ProductCondition;

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
		MySQL_Products.updateProduct(product, ProductCondition.STORED);
		onRestoreFromReserves(product);
	}
	public default void disposeFromReserves(Product product) {
		MySQL_Products.updateProduct(product, ProductCondition.DISPOSED);
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

