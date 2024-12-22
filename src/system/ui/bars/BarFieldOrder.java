package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import components.fields.PercentageField;

public class BarFieldOrder extends BarFields{
	private static final long serialVersionUID = 8424383950064417067L;

	public BarFieldOrder() {
		addField("Discount", new PercentageField("0%"), 250, 20).setEditable(false);
		addField("Total Net. Amount", new DecimalField("0.00"), 250, 20).setEditable(false);
	}
	public void calculateTotalUnitAmount() {
		
	}
}
