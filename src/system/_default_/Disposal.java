package system._default_;

import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.objects.Remarks;

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
		product.setProduct_condition(ProductCondition.STORED);
		MySQL_Products.updateProduct(product);
		onRestoreFromDisposal(product);
	}
	public default void reserveFromDisposal(Product product) {
		product.setProduct_condition(ProductCondition.RESERVED);
		MySQL_Products.updateProduct(product);
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

