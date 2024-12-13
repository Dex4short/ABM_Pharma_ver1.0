package database;

import oop.Date;
import oop.Item;

public class MySQL_Item {
	public static String 
	table_name = "item",
	table_columns[] = {"item_id", "item_no", "description", "lot_no", "date_added", "exp_date", "brand"};
	
	public static Item insertItem(Item item) {
		item.setItemId(MySQL.nextUID("item_id", "item"));
		MySQL.insert(
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
			}
		);
		return item;
	}
	public static Item selectItem(int item_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where item_id=" + item_id
		);
		Item items = new Item(
				(int)result[0][0],	
				(String)result[0][1],	
				(String)result[0][2],	
				(String)result[0][3],	
				(Date)result[0][4],	
				(Date)result[0][5],
				(String)result[0][6]
		);
		
		return items;
	}
	public static void deleteItem(int item_id) {
		MySQL.delete(table_name, "where item_id=" + item_id);
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
