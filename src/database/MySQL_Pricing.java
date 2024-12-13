package database;

import oop.Pricing;

public class MySQL_Pricing {
	public static String 
	table_name = "pricing",
	table_columns[] = {"price_id", "cost", "unit_price", "discount", "unit_amount"};
	
	public static Pricing insert(Pricing pricing) {
		pricing.setPriceId(MySQL.nextUID("price_id", "pricing"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				pricing.getPriceId(),
				pricing.getCost(),
				pricing.getUnitPrice(),
				pricing.getDiscount(),
				pricing.getUnitAmount()
			}
		);
		return pricing;
	}
	public static Pricing[] selectPricing(int price_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where price_id=" + price_id
		);
		Pricing pricing[] = new Pricing[result.length];
		for(int i=0; i<pricing.length; i++) {
			pricing[i] = new Pricing(
				(int)result[i][0],	
				(String)result[i][1],	
				(String)result[i][2],	
				(String)result[i][3],
				(String)result[i][4]
			);
		}
		return pricing;
	}
	public static void deletePricing(int price_id) {
		MySQL.delete(table_name, "where price_id=" + price_id);
	}
	public static void updatePricing(int price_id, Pricing pricing) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						pricing.getPriceId(),
						pricing.getCost(),
						pricing.getUnitPrice(),
						pricing.getDiscount(),
						pricing.getUnitAmount()
				},
				"where price_id=" + price_id);
	}
}
