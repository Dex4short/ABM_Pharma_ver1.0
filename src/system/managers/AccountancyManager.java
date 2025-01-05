package system.managers;

import java.math.BigDecimal;

import system.objects.Decimal;
import system.objects.Order;
import system.objects.Packaging;
import system.objects.Percentage;
import system.objects.Pricing;
import system.objects.Product;

public class AccountancyManager {

	public static Decimal calculateDiscountedAmount(Decimal amount, Percentage discount) {
		return new Decimal(
			amount.toBigDecimal().subtract(
				amount.toBigDecimal().multiply(
					discount.toBigDecimal()
				)
			)
		);
	}
	public static Decimal calculateUnitAmount(Decimal unit_price, Percentage discount) {
		return calculateDiscountedAmount(unit_price, discount);
	}
	public static Decimal calculateAmountByQuantity(Decimal amount, int quantity) {
		return new Decimal(amount.toBigDecimal().multiply(new BigDecimal(quantity)));
	}
	public static Decimal calculateNetAmount(Decimal amount, Percentage discount, int quantity) {
		Decimal 
		discounted_amount = calculateDiscountedAmount(amount, discount),
		net_amount = calculateAmountByQuantity(discounted_amount, quantity);
		return net_amount;
	}
	public static Decimal calculateNetAmount(Packaging packaging, Pricing pricing) {
		return calculateNetAmount(pricing.getUnitPrice(), pricing.getDiscount(), packaging.getQty().getAmount());
	}
	public static Decimal calculateNetAmount(Product product) {
		return calculateNetAmount(product.getPackaging(), product.getPricing());
	}	
	public static Decimal calculateNetAmount(Order order) {
		return calculateNetAmount(order.getProduct());
	}
	public static Decimal calculateCostAmount(Order orders[]) {
		Decimal	cost, qty, total_amount = new Decimal();
		
		for(Order order: orders) {
			cost = order.getProduct().getPricing().getCost();
			qty = new Decimal(order.getProduct().getPackaging().getQty().getAmount() + ".00");
			
			total_amount = total_amount.add(cost.multiply(qty));
			System.out.println("(cost: " + cost.toString() + ") * (qty:" + qty.toString() + ") = " + total_amount.toString());
		}
		return total_amount;
	}
	public static Decimal calculateTotalNetAmount(Order orders[]) {
		Decimal total_netAmount = new Decimal();
		for(Order order: orders) {
			total_netAmount = total_netAmount.add(order.getNetAmount());
		}
		return total_netAmount;
	}
	public static Decimal calculateProfit(Decimal total_net_amount, Decimal cost_amount) {
		System.out.println("total_net_amount: " + total_net_amount.toString());
		System.out.println("cost_amount: " + cost_amount.toString());
		return total_net_amount.subtract(cost_amount);
	}
}


