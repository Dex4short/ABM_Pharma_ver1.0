package database;


import oop.Date;
import oop.Transaction;

public class MySQL_Transaction {
	public static String 
	table_name = "transaction",
	table_columns[] = {"trans_id", "counter_no", "cart_no", "cust_id", "tin_no", "date", "terms", "cost_amount", "profit", "discount", "total_amount"};
	
	public static Transaction insertTransaction(Transaction transaction) {
		transaction.setTransId(MySQL.nextUID("trans_id", "transaction"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				transaction.getTransId(),
				transaction.getCounterNo(),
				transaction.getCartNo(),
				transaction.getCustId(),
				transaction.getTinNo(),
				transaction.getDate(),
				transaction.getCostAmount(),
				transaction.getProfit(),
				transaction.getDiscount(),
				transaction.getTotalAmount()
				
			}
		);
		return transaction;
	}
	public static Transaction[] selectItem(int trans_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where trans_id=" + trans_id
		);
		Transaction items[] = new Transaction[result.length];
		for(int i=0; i<transaction.length; i++) {
			items[i] = new Transaction(
				(int)result[i][0],	
				(int)result[i][1],	
				(int)result[i][2],	
				(int)result[i][3],	
				(int)result[i][4],	
				(Date)result[i][5],
				(String)result[i][6],
				(String)result[i][6],
				
			);
		}
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
