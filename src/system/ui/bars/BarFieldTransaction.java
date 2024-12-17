package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;

public class BarFieldTransaction extends BarFields{
	private static final long serialVersionUID = 8424383950064417067L;

	public BarFieldTransaction() {
		addField("Total Cost Amount", new DecimalField("0.00"), 250, 20).setEditable(false);
		addField("Total Profit", new DecimalField("0.00"), 250, 20).setEditable(false);
	}
	public void calculateTotalCostAmount() {
		
	}
	public void calculateTotalProfit() {
		
	}
}
