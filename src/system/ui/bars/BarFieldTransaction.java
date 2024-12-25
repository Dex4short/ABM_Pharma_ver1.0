package system.ui.bars;

import components.fields.BarFields;
import components.fields.DecimalField;
import oop.Decimal;
import oop.Transaction;

public class BarFieldTransaction extends BarFields{
	private static final long serialVersionUID = 8424383950064417067L;
	private DecimalField total_cost_amount_field, total_profit_field;

	public BarFieldTransaction() {
		total_cost_amount_field = new DecimalField("0.00");
		total_cost_amount_field.setEditable(false);
		addField("Total Cost Amount", total_cost_amount_field, 250, 20);
		
		total_profit_field = new DecimalField("0.00");
		total_profit_field.setEditable(false);
		addField("Total Profit", total_profit_field, 250, 20);
	}
	public void calculateTotalCostAmount_and_Profit(Transaction transactions[]) {
		Decimal 
		total_cost_amount = new Decimal(),
		total_profit = new Decimal();
		
		for(Transaction transaction: transactions) {
			total_cost_amount = total_cost_amount.add(transaction.getCostAmount());
			total_profit = total_profit.add(transaction.getProfit());
		}
		
		total_cost_amount_field.setDecimal(total_cost_amount);
		total_profit_field.setDecimal(total_profit);
	}
	public DecimalField getTotalCostAmountField() {
		return total_cost_amount_field;
	}
	public void setTotalCostAmountField(DecimalField total_cost_amount_field) {
		this.total_cost_amount_field = total_cost_amount_field;
	}
	public DecimalField getTotalProfitField() {
		return total_profit_field;
	}
	public void setTotalProfitField(DecimalField total_profit_field) {
		this.total_profit_field = total_profit_field;
	}
}
