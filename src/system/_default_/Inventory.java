package system._default_;

import database.MySQL_Products;
import oop.Product;
import oop.enums.ProductCondition;

public interface Inventory {

	public default Product selectInventory() {		
		return onSelectInventory();
	}
	public default Product[] selectInventorySet() {
		Product product_set[] = new Product[3];
		product_set[0] = selectInventory();
		
		Product product_children[] = MySQL_Products.selectProductsChildren(product_set[0]);
		for(int p=0; p<product_children.length; p++) {
			product_set[p+1] = product_children[p];
		}
		
		return product_set;
	}
	public default void searchFromInventory(String category, String word) {
		//Product products[] = null;
		
		//onSearchFromInventory();
	}
	public default void printFromInventory() {
		
		onPrintFromInventory();
	}
	public default void reserveFromInventory() {
		
		onReserveFromInventory();
	}
	public default void deleteFromInventory() {
		
		onDeleteFromInventory();
	}
	public default void editFromInventory(Product new_product, Product old_product, ProductCondition condition) {
		if(new_product!=null) {
			if(old_product==null) {
				MySQL_Products.insertProduct(new_product, condition);
			}
			else {
				MySQL_Products.updateProduct(new_product);
			}
		}
		else {
			if(old_product!=null) {
				MySQL_Products.deleteProdut(old_product);
			}
			else{
				//no action needed
			}
		}
		
		if(condition == ProductCondition.STORED) onEditFromInventory(new_product, old_product);
	}
	public default void addToInventory(Product product, ProductCondition condition) {
		MySQL_Products.insertProduct(product, condition);
		if(condition == ProductCondition.STORED) onAddToInventory(product);
	}
	public default void loadInventory() {
		onLoadInventory(MySQL_Products.selectProducts(ProductCondition.STORED));
	}

	public Product onSelectInventory();
	public void onSearchFromInventory(Product products[]);
	public void onPrintFromInventory();
	public void onReserveFromInventory();
	public void onDeleteFromInventory();
	public void onEditFromInventory(Product new_product, Product old_product);
	public void onLoadInventory(Product products[]);
	public void onAddToInventory(Product product);
	
}
