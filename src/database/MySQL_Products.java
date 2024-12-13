package database;
import oop.Date;
import oop.Item;
import oop.Product;
import oop.enums.ProductCondition;

public class MySQL_Products {
	
	public static String 
	product_name = "product",
	product_columns[] = {"prod_id", "item_id", "pack_id", "price_id", "rem_id", "prod_condition"};
	
	public static Product insertProduct(Product product) {
		product.setProId(MySQL.nextUID("prod_id", "product"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				product.getProdId(),
				product.getItemId(),
				product.getPackId(),
				product.getPriceId(),
				product.getRemId(),
				product.getProdCondition()

			}
		);
		return product;
	}
	public static Product[] selectProduct(int prod_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where prod_id=" + prod_id
		);
		Product product[] = new Product[result.length];
		for(int i=0; i<.length; i++) {
			product[i] = new Product(
				(int)result[i][0],	
				(int)result[i][1],	
				(int)result[i][2],	
				(int)result[i][3],	
				(int)result[i][4],	
				(String)result[i][5]
			);
		}
		return product;
	}
	public static void deleteProdut(int prod_id) {
		MySQL.delete(table_name, "where prod_id=" + prod_id);
	}
	public static void updateItem(int item_id, Item item) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
					item.getItemId(),
					item.getItemNo(),
					item.getDescription(),
					item.getLotNo(),
					item.getDateAdded(),
					item.getExpDate(),
					item.getBrand()
				},
				"where item_id=" + item_id);
	}

}
