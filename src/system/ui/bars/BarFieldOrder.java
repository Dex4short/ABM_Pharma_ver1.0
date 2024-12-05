package system.ui.bars;

import components.fields.DecimalField;
import components.fields.PercentageField;
import components.fields.TextField;

public class BarFieldOrder extends BarField{
	private static final long serialVersionUID = 8424383950064417067L;

	public BarFieldOrder() {
		super(
			new String[] {
				"Discount", 
				"Total Unit Amount"
			}, 
			new TextField[] {
				new PercentageField("0%"),
				new DecimalField("0.00")
			}
		);
		getField(0).setEditable(true);
	}
	public void calculateTotalUnitAmount() {
		
	}
}
