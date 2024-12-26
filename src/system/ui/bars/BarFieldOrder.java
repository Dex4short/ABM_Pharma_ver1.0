package system.ui.bars;

import oop.Transaction;

public abstract class BarFieldOrder extends BarFieldCart{
	private static final long serialVersionUID = 8424383950064417067L;

	public BarFieldOrder() {
		getDiscountField().setEditable(false);
	}
	public void calculateDiscount_and_TotalNetAmount(Transaction transaction) {
		getDiscountField().setPercent(transaction.getDiscount());
		getTotalNetAmountField().setDecimal(transaction.getTotalNetAmount());
	}	
}
