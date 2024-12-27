package database;

import system.objects.Date;
import system.objects.Item;

public class MySQL_Item {
	public static final String 
	table_name = "item",
	table_columns[] = {"item_id", "item_no", "description", "lot_no", "date_added", "exp_date", "brand"};
	
	public static Item insertItem(Item item) {		
		item.setItemId(MySQL.nextUID(table_columns[0], table_name));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				item.getItemId(),
				item.getItemNo(),
				item.getDescription(),
				item.getLotNo(),
				item.getDateAdded().toSQLDate(),
				item.getExpDate().toSQLDate(),
				item.getBrand()
			}
		);
		return item;
	}
	public static Item selectItem(int item_id) {
		Object results[][] = MySQL.select(
			table_columns,
			table_name,
			"where item_id=" + item_id
		);
		if(results.length > 0) {
			Item items = new Item(
				(int)results[0][0],	
				(String)results[0][1],	
				(String)results[0][2],	
				(String)results[0][3],	
				Date.parseDate((java.sql.Date)results[0][4]),
				Date.parseDate((java.sql.Date)results[0][5]),
				(String)results[0][6]
			);
			return items;
		}
		else {
			return null;
		}
	}
	public static void deleteItem(Item item) {
		MySQL.delete(table_name, "where item_id=" + item.getItemId());
	}
	public static void updateItem(Item item) {
		MySQL.update(
			table_name,
			new String[]{"item_no", "description", "lot_no", "date_added", "exp_date", "brand"},
			new Object[] {
				item.getItemNo(),
				item.getDescription(),
				item.getLotNo(),
				item.getDateAdded().toSQLDate(),
				item.getExpDate().toSQLDate(),
				item.getBrand()
			},
			"where item_id=" + item.getItemId()
		);
	}

}
