package system._default_;

import database.MySQL_Packaging;
import database.MySQL_Pricing;
import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.objects.Pricing;
import system.objects.Product;
import system.printers.InventoryPrinter;

public interface Inventory {

	public default Product selectFromInventory() {		
		return onSelectFromInventory();
	}
	public default Product[] selectInventorySet() {
		Product product_parent = selectFromInventory();
		Product product_children[] = MySQL_Products.selectSubProducts(product_parent);
		return onSelectInventorySet(product_parent, product_children);
	}
	public default Product[] selectManyFromInventory() {		
		return onSelectManyFromInventory();
	}
	public default Product[] selectAllProductsFromInventory() {
		return onSelectAllProductsFromInventory();
	}
	public default void searchFromInventory(String category, String word) {
		String keys[][] = {
			{"Item No.", "item_no"},
			{"Description", "description"},
			{"Lot No.", "lot_no"},
			{"Date Added", "date_added"},
			{"Exp Date", "exp_date"},
			{"Brand", "brand"},
			{"Quantity", "qty"},
			{"UOM", "u.name"},
			{"Cost", "cost"},
			{"Unit Price", "unit_price"},
			{"Discount", "discount"},
			{"Unit Amount", "unit_amount"}
		};
		
		int k;
		for(k=0; k<keys.length; k++) {
			if(keys[k][0].equals(category)) break;
		}
		
		if(k == 6) {
			word = " = " + word;
		}
		else {
			word = " like '%" + word + "%'";
		}
		
		onSearchFromInventory(MySQL_Products.selectProducts(keys[k][1], word, ProductCondition.STORED));
	}
	public default void printFromInventory(Product products[]) {
		InventoryPrinter.printInventory(products);
		onPrintFromInventory();
	}
	public default void reserveFromInventory(Product product) {
		product.setProduct_condition(ProductCondition.RESERVED);
		MySQL_Products.updateProduct(product);
		onReserveFromInventory(product);
	}
	public default void disposeFromInventory(Product product) {
		product.setProduct_condition(ProductCondition.DISPOSED);
		MySQL_Products.updateProduct(product);
		onDisposeFromInventory(product);
	}
	public default void editFromInventory(Product new_product, Product old_product, ProductCondition condition) {
		if(new_product!=null) {
			new_product.setProduct_condition(condition);
			if(old_product==null) {
				MySQL_Products.insertProduct(new_product);
			}
			else {
				if(new_product.getPackaging().getQty().isDeducted()) {
					Pricing new_pricing = new_product.getPricing();
					MySQL_Pricing.insertPricing(new_pricing);
					MySQL_Products.updateProduct(
						new_product.getProdId(), 
						new_product.getItem().getItemId(),
						new_product.getPackaging().getPackId(),
						new_product.getPricing().getPriceId(),
						(new_product.getRemarks()!=null) ? new_product.getRemarks().getRemId() : -1,
						condition
					);
				}
				else {
					MySQL_Products.updateProduct(new_product);
				}
			}
		}
		else {
			if(old_product!=null) {
				MySQL_Packaging.deletePackaging(old_product.getPackaging());
				MySQL_Pricing.deletePricing(old_product.getPricing());
			}
		}
		if(condition == ProductCondition.STORED) onEditFromInventory(new_product);
	}
	public default void addToInventory(Product product, ProductCondition condition) {
		product.setProduct_condition(condition);
		MySQL_Products.insertProduct(product);
		if(condition == ProductCondition.STORED) onAddToInventory(product);
	}
	public default void loadAllFromInventory() {
		onLoadAllFromInventory(MySQL_Products.selectProducts(ProductCondition.STORED));
	}

	public Product onSelectFromInventory();
	public Product[] onSelectManyFromInventory();
	public Product[] onSelectInventorySet(Product product_parent, Product product_children[]);
	public Product[] onSelectAllProductsFromInventory();
	public void onSearchFromInventory(Product products[]);
	public void onPrintFromInventory();
	public void onReserveFromInventory(Product product);
	public void onDisposeFromInventory(Product product);
	public void onEditFromInventory(Product product);
	public void onAddToInventory(Product product);
	public void onLoadAllFromInventory(Product products[]);
	
}
