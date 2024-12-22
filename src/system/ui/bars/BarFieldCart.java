package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import components.fields.PercentageField;
import oop.Decimal;
import oop.Order;
import oop.essentials.Accountancy;

public abstract class BarFieldCart extends BarFields{
	private static final long serialVersionUID = 1918054933901629685L;
	private DecimalField decimal_field;

	public BarFieldCart() {
		addField("Discount", new PercentageField("0%"), 250, 20);
		addField("Total Net. Amount", decimal_field = new DecimalField("0.00"), 250, 20).setEditable(false);
	}
	public void calculateTotalAmount() {
		setTotalNetAmount(Accountancy.calculateTotalNetAmount(getOrders()));
		decimal_field.setDecimal(getTotalNetAmount());
	}
	public Decimal getTotalNetAmount() {
		return decimal_field.getDecimal();
	}
	public void setTotalNetAmount(Decimal total_netAmount) {
		decimal_field.setDecimal(total_netAmount);
	}
	
	public abstract Order[] getOrders();
	
}
