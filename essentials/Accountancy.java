package essentials;

import java.math.BigDecimal;

import oop.Decimal;
import oop.Packaging;
import oop.Percentage;
import oop.Pricing;

public class Accountancy {

	public static Decimal calculateUnitAmount(Decimal unit_price, Percentage discount) {
		return new Decimal(
				unit_price.toBigDecimal().subtract(
						unit_price.toBigDecimal().multiply(
								discount.toBigDecimal()
						)
				)
		);
	}
	public static Decimal calculateNetAmount(Packaging packaging, Pricing pricing) {
		int quantity = packaging.getQty().getQuantity();
		
		Decimal 
		unit_amount = calculateUnitAmount(pricing.getUnitPrice(), pricing.getDiscount()),
		net_amount = new Decimal(unit_amount.toBigDecimal().multiply(new BigDecimal(quantity)));
		
		return net_amount;
	}
}
