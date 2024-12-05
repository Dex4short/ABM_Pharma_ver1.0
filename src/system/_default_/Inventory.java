package system._default_;

import database.MySQL_Products;
import oop.Product;
import oop.enums.ProductCondition;

public interface Inventory {
	
	public default void searchFromInventory(String category, String word) {
		//Product products[] = null;
		
		//onSearchFromInventory(products);
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
	public default void editFromInventory() {
		
		onEditFromInventory();
	}
	public default void addToInventory(Product product, ProductCondition condition) {
		MySQL_Products.insert(product, condition);
		if(condition == ProductCondition.STORED) {
			onAddToInventory(product);
		}
	}
	public default void loadAllProducts() {
		
		onLoadAllProducts(null);
	}

	public void onSearchFromInventory(Product products[]);
	public void onPrintFromInventory();
	public void onReserveFromInventory();
	public void onDeleteFromInventory();
	public void onEditFromInventory();
	public void onAddToInventory(Product product);
	public void onLoadAllProducts(Product products[]);
}
