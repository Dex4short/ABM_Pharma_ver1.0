package database;

import oop.Packaging;
import oop.Product;
import oop.enums.ProductCondition;

public class MySQL_Products {
	
	public static String 
	table_name = "products",
	table_columns[] = {"prod_id", "item_id", "pack_id", "price_id", "rem_id", "prod_condition"};
	
	public static Product insertProduct(Product product, ProductCondition condition) {		
		product.setProdId(MySQL.nextUID(table_columns[0], table_name));
		
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				product.getProdId(),
				MySQL_Item.insertItem(product.getItem()).getItemId(),
				MySQL_Packaging.insert(product.getPackaging(), condition).getPackId(),
				MySQL_Pricing.insert(product.getPricing()).getPriceId(),
				-1,
				condition.name()
			}
		);
		
		return product;
	}
	public static Product selectProduct(int prod_id, ProductCondition condition) {
		return selectProducts("where prod_id=" + prod_id + " and prod_condition=" + condition)[0];
	}
	public static Product[] selectProducts(ProductCondition condition) {
		return selectProducts("where prod_condition='" + condition +"' ");
	}
	public static Product[] selectProductsChildren(Product product) {
		Packaging packagings[] = MySQL_Packaging.selectPackagings(" where parentPack_id=" + product.getPackaging().getPackId());
		
		Product products[] = new Product[packagings.length];
		for(int p=0; p<packagings.length; p++) {
			products[p] = selectProducts("where pack_id=" + packagings[p].getPackId())[0];
		}
		return products;
	}
	public static Product[] selectProducts(String condition) {
		Object results[][] = MySQL.select(
				table_columns,
				table_name,
				condition
		);
		
		Product products[] = new Product[results.length];
		for(int r=0; r<results.length; r++) {
			products[r] = new Product(
				(int)results[r][0],
				MySQL_Item.selectItem((int)results[r][1]),
				MySQL_Packaging.selectPackaging((int)results[r][2]),
				MySQL_Pricing.selectPricing((int)results[r][3]),
				MySQL_Remarks.selectRemarks((int)results[r][4])
			);
		}
		
		return products;
	}
	public static void deleteProdut(Product product) {
		MySQL_Item.deleteItem(product.getItem());
		MySQL_Packaging.deletePackaging(product.getPackaging());
		MySQL_Pricing.deletePricing(product.getPricing());
		MySQL_Remarks.deleteRemarks(product.getRemarks());
		
		MySQL.delete(table_name, "where prod_id=" + product.getProdId());
	}
	public static void updateProduct(Product product, ProductCondition condition) {
		MySQL_Item.updateItem(product.getItem());
		MySQL_Packaging.updatePackaging(product.getPackaging());
		MySQL_Pricing.updatePricing(product.getPricing());
		MySQL_Remarks.updateRemarks(product.getRemarks());
		
		MySQL.update(
			table_name,
			new String[] {"item_id", "pack_id", "price_id", "rem_id", "prod_condition"},
			new Object[] {
				product.getItem().getItemId(),
				product.getPackaging().getPackId(),
				product.getPricing().getPriceId(),
				product.getRemarks().getRemId(),
				condition.name()
			},
			"where prod_id=" + product.getProdId()
		);
	}
	public static void updateProduct(Product product) {
		MySQL_Item.updateItem(product.getItem());
		MySQL_Packaging.updatePackaging(product.getPackaging());
		MySQL_Pricing.updatePricing(product.getPricing());
		
		MySQL.update(
			table_name,
			new String[] {"item_id", "pack_id", "price_id", "rem_id"},
			new Object[] {
				product.getItem().getItemId(),
				product.getPackaging().getPackId(),
				product.getPricing().getPriceId(),
				(product.getRemarks()!=null) ? product.getRemarks().getRemId() : -1
			},
			"where prod_id=" + product.getProdId()
		);
	}
}
