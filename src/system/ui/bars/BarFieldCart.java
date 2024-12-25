package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import components.fields.PercentageField;
import oop.Decimal;
import oop.Order;
import oop.Percentage;
import oop.essentials.Accountancy;

public class BarFieldCart extends BarFields{
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
	public PercentageField getDiscountField() {
		return percent_field;
	}
	public void setDiscountField(PercentageField percent_field) {
		this.percent_field = percent_field;
	}
	public DecimalField getTotalNetAmountField() {
		return decimal_field;
	}
	public void setTotalNetAmountField(DecimalField decimal_field) {
		this.decimal_field = decimal_field;
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
	public void calculateTotalAmount(Order orders[]) {
		setTotalNetAmount(Accountancy.calculateTotalNetAmount(orders));
		decimal_field.setDecimal(getTotalNetAmount());
	}
	public void clearFields() {
		percent_field.setPercent(new Percentage());
		decimal_field.setDecimal(new Decimal());
	}	
}
