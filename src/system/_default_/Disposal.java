package system._default_;

import database.MySQL_Products;
import oop.Product;
import oop.Remarks;
import oop.enumerations.ProductCondition;

public interface Disposal {
	
	public default Product selectFromDisposal() {
		return onSelectFromDisposal();
	}
	public default Product[] selectManyFromDisposal() {
		return onSelectManyFromDisposal();
	}
	public default void searchFromDisposal() {
		onSearchFromDisposal();
	}
	public default void restoreFromDisposal(Product product) {
		MySQL_Products.updateProduct(product, ProductCondition.STORED);
		onRestoreFromDisposal(product);
	}
	public default void reserveFromDisposal(Product product) {
		MySQL_Products.updateProduct(product, ProductCondition.RESERVED);
		onReserveFromDisposal(product);
	}
	public default void deleteFromDisposal() {
		
		onDeleteFromDisposal();
	}
	public default void showRemarks(Remarks remarks) {
		onShowRemarks(remarks);
	}
	public default void loadAllFromDisposal() {
		Product disposed_products[] = MySQL_Products.selectProducts(ProductCondition.DISPOSED);
		onLoadAllFromDisposal(disposed_products);
	}
	
	public Product onSelectFromDisposal();
	public Product[] onSelectManyFromDisposal();
	public void onSearchFromDisposal();
	public void onRestoreFromDisposal(Product product);
	public void onReserveFromDisposal(Product product);
	public void onDeleteFromDisposal();
	public void onShowRemarks(Remarks remarks);
	public void onLoadAllFromDisposal(Product disposed_products[]);
}

