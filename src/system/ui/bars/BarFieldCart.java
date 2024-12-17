package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import components.fields.PercentageField;

public class BarFieldCart extends BarFields{
	private static final long serialVersionUID = 1918054933901629685L;

	public BarFieldCart() {
		addField("Discount", new PercentageField("0%"), 250, 20);
		addField("Total Amount", new DecimalField("0.00"), 250, 20).setEditable(false);
	}
	public void calculateTotalAmount() {
		
	}
}
