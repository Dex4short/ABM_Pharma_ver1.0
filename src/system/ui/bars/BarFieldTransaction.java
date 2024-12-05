package system.ui.bars;

import components.fields.DecimalField;
import components.fields.TextField;

public class BarFieldTransaction extends BarField{
	private static final long serialVersionUID = 8424383950064417067L;

	public BarFieldTransaction() {
		super(
			new String[] {
				"Total Cost Amount",
				"Total Profit"
			}, 
			new TextField[] {
				new DecimalField("0.00"),
				new DecimalField("0.00")
			}
		);
	}
	public void calculateTotalCostAmount() {
		
	}
	public void calculateTotalProfit() {
		
	}
}
