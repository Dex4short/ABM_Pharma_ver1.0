package database;

import java.util.ArrayList;

import system.enumerators.PackagingLine;
import system.enumerators.ProductCondition;
import system.objects.Packaging;
import system.objects.Product;
import system.objects.Uom;

public class MySQL_Products {
	
	public static final String 
	table_name = "products",
	table_columns[] = {"prod_id", "item_id", "pack_id", "price_id", "rem_id", "prod_condition"};
	
	public static Product insertProduct(Product product) {		
		product.setProdId(MySQL.nextUID(table_columns[0], table_name));
		
		if(product.getProductCondition() == ProductCondition.STORED) MySQL_Item.insertItem(product.getItem());
		MySQL_Packaging.insertPackaging(product.getPackaging());
		if(product.getProductCondition() != ProductCondition.ORDERED) MySQL_Pricing.insertPricing(product.getPricing());
		
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				product.getProdId(),
				product.getItem().getItemId(),
				product.getPackaging().getPackId(),
				product.getPricing().getPriceId(),
				-1,
				product.getProductCondition().name()
			}
		);
		
		return product;
	}
	public static Product selectProduct(int prod_id) {
		return selectProducts("where prod_id=" + prod_id)[0];
	}
	public static Product[] selectProducts(ProductCondition condition) {
		return selectProducts("where prod_condition='" + condition +"' ");
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
				MySQL_Remarks.selectRemarks((int)results[r][4]),
				ProductCondition.valueOf((String)results[r][5])
			);
		}		
		return products;
	}
	public static Product[] selectProducts(String key, String word, ProductCondition product_condition) {
		String
		joins = " products as p join "
				+ "item as i join "
				+ "packaging as k join "
				+ "uom as u join "
				+ "pricing as s join "
				+ "remarks as r ",
		on = " on p.item_id=i.item_id and "
				+ "	p.pack_id=k.pack_id and k.uom_id=u.uom_id and "
				+ "	p.price_id=s.price_id ",
		where = " where prod_condition='" + product_condition + "' and " + key + " like '%" + word + "%' ";
		Object results[][] = MySQL.select(new String[] {"p.prod_id"}, joins, on + where);
		
		Product products[] = new Product[results.length];
		for(int p=0; p<products.length; p++) {
			products[p] = selectProduct((int)results[p][0]);
		}
		
		return products;
	}
	public static Product[] selectSubProducts(Product product) {
		Packaging packagings[] = MySQL_Packaging.selectPackagings(
			product.getPackaging().getPackagingLine(),
			product.getPackaging().getPackagingGroup()
		);
		
		ArrayList<Product> products = new ArrayList<Product>();
		Uom uom = product.getPackaging().getUom().getSubUom();
		int p=0;
		while(uom!=null && p<packagings.length) {
			if(uom.getUnitType() == packagings[p].getUom().getUnitType()) {
				products.add(selectProducts("where pack_id=" + packagings[p].getPackId())[0]);
				uom = uom.getSubUom();
			}
			p++;
		}
		
		return products.toArray(new Product[0]);
	}
	public static void deleteProduct(Product product) {
		if(product.getPackaging().getParentPackId() == -1) MySQL_Item.deleteItem(product.getItem());
		MySQL_Packaging.deletePackaging(product.getPackaging());
		
		if(product.getPackaging().getPackagingLine() != PackagingLine.Descendant) MySQL_Pricing.deletePricing(product.getPricing());
		MySQL_Remarks.deleteRemarks(product.getRemarks());

		MySQL.delete(table_name, "where prod_id=" + product.getProdId());
	}
	public static void updateProduct(int prod_id, int item_id, int pack_id, int price_id, int rem_id, ProductCondition product_condition) {
		MySQL.update(
				table_name,
				new String[] {"item_id", "pack_id", "price_id", "rem_id", "prod_condition"},
				new Object[] {item_id, pack_id, price_id, rem_id, product_condition.name()},
				"where prod_id=" + prod_id
			);
	}
	public static void updateProduct(Product product) {
		MySQL_Item.updateItem(product.getItem());
		MySQL_Packaging.updatePackaging(product.getPackaging());
		MySQL_Pricing.updatePricing(product.getPricing());
		MySQL_Remarks.updateRemarks(product.getRemarks());
		
		updateProduct(
			product.getProdId(),
			product.getItem().getItemId(),
			product.getPackaging().getPackId(),
			product.getPricing().getPriceId(),
			(product.getRemarks() != null) ? product.getRemarks().getRemId() : -1,
			product.getProductCondition()
		);
	}
	public static void updateProduct(int pack_id, ProductCondition product_condition) {
		MySQL.update(
			table_name,
			new String[] {"prod_condition"},
			new Object[] {product_condition.name()}, 
			"where pack_id=" + pack_id
		);
	}
}
