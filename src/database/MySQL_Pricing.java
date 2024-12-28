package database;

import java.math.BigDecimal;

import system.objects.Decimal;
import system.objects.Percentage;
import system.objects.Pricing;

public class MySQL_Pricing {
	public static final String 
	table_name = "pricing",
	table_columns[] = {"price_id", "cost", "unit_price", "discount", "unit_amount"};
	
	public static Pricing insertPricing(Pricing pricing) {		
		pricing.setPriceId(MySQL.nextUID(table_columns[0], table_name));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				pricing.getPriceId(),
				pricing.getCost().toBigDecimal(),
				pricing.getUnitPrice().toBigDecimal(),
				pricing.getDiscount().toString(),
				pricing.getUnitAmount().toBigDecimal()
			}
		);
		return pricing;
	}
	public static Pricing selectPricing(int price_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where price_id=" + price_id
		);
		Pricing pricing = new Pricing(
			(int)result[0][0],
			new Decimal((BigDecimal)result[0][1]),
			new Decimal((BigDecimal)result[0][2]),
			new Percentage((String)result[0][3]),
			new Decimal((BigDecimal)result[0][4])
		);
		return pricing;
	}
	public static void deletePricing(Pricing pricing) {
		MySQL.delete(table_name, "where price_id=" + pricing.getPriceId());
	}
	public static void updatePricing(Pricing pricing) {
		MySQL.update(
			table_name,
			new String[] {"cost", "unit_price", "discount", "unit_amount"},
			new Object[] {
				pricing.getCost().toBigDecimal(),
				pricing.getUnitPrice().toBigDecimal(),
				pricing.getDiscount().toString(),
				pricing.getUnitAmount().toBigDecimal()
			},
			"where price_id=" + pricing.getPriceId());
	}
}
