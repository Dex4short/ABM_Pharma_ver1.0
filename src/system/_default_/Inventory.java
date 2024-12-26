package system._default_;

import database.MySQL_Products;
import oop.Product;
import oop.enumerations.ProductCondition;

public interface Inventory {

	public default Product selectFromInventory() {		
		return onSelectFromInventory();
	}
	public default Product[] selectManyFromInventory() {		
		return onSelectManyFromInventory();
	}
	public default Product[] selectInventorySet() {
		Product product_parent = selectFromInventory();
		Product product_children[] = MySQL_Products.selectSubProducts(product_parent);
		return onSelectInventorySet(product_parent, product_children);
	}
	public default void searchFromInventory(String category, String word) {
		String set[][] = {
			{"Item No.", "item_no"},
			{"Description", "description"},
			{"Lot No.", "lot_no"},
			{"Date Added", "date_added"},
			{"Exp Date", "exp_date"},
			{"Brand", "brand"},
			{"Quantity", "qty"},
			{"UOM", "uom"},
			{"Cost", "cost"},
			{"Unit Price", "unit_price"},
			{"Discount", "discount"},
			{"Unit Amount", "unit_amount"}
		};
		//onSearchFromInventory(MySQL_Products.selectProducts("where "));
	}
	public default void printFromInventory() {
		
		onPrintFromInventory();
	}
	public default void reserveFromInventory(Product product) {
		MySQL_Products.updateProduct(product, ProductCondition.RESERVED);
		onReserveFromInventory(product);
	}
	public default void disposeFromInventory(Product product) {
		MySQL_Products.updateProduct(product, ProductCondition.DISPOSED);
		onDisposeFromInventory(product);
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
		if(condition == ProductCondition.STORED) onEditFromInventory(new_product);
	}
	public default void addToInventory(Product product, ProductCondition condition) {
		MySQL_Products.insertProduct(product, condition);
		if(condition == ProductCondition.STORED) onAddToInventory(product);
	}
	public default void loadAllFromInventory() {
		onLoadAllFromInventory(MySQL_Products.selectProducts(ProductCondition.STORED));
	}

	public Product onSelectFromInventory();
	public Product[] onSelectManyFromInventory();
	public Product[] onSelectInventorySet(Product product_parent, Product product_children[]);
	public void onSearchFromInventory(Product products[]);
	public void onPrintFromInventory();
	public void onReserveFromInventory(Product product);
	public void onDisposeFromInventory(Product product);
	public void onEditFromInventory(Product product);
	public void onAddToInventory(Product product);
	public void onLoadAllFromInventory(Product products[]);
	
}
