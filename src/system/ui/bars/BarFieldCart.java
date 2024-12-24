package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import components.fields.PercentageField;
import oop.Decimal;
import oop.Order;
import oop.Percentage;
import oop.essentials.Accountancy;

public abstract class BarFieldCart extends BarFields{
	private static final long serialVersionUID = 1918054933901629685L;
	private PercentageField percent_field;
	private DecimalField decimal_field;

	public BarFieldCart() {
		 percent_field = new PercentageField("0%");
		addField("Discount",percent_field, 250, 20);
		
		decimal_field = new DecimalField("0.00");
		decimal_field.setEditable(false);
		addField("Total Net. Amount", decimal_field, 250, 20);
	}
	public void calculateTotalAmount() {
		setTotalNetAmount(Accountancy.calculateTotalNetAmount(getOrders()));
		decimal_field.setDecimal(getTotalNetAmount());
	}
	public Percentage getDiscount() {
		return percent_field.getPercent();
	}
	public void setDiscount(Percentage percent) {
		percent_field.setPercent(percent);
	}
	public Decimal getTotalNetAmount() {
		return decimal_field.getDecimal();
	}
	public void setTotalNetAmount(Decimal total_netAmount) {
		decimal_field.setDecimal(total_netAmount);
	}
	public void clearFields() {
		percent_field.setPercent(new Percentage());
		decimal_field.setDecimal(new Decimal());
	}
	
	public abstract Order[] getOrders();
	
}
