package system.printers.tools;

import java.math.BigDecimal;

public abstract class Accountancy {

	public static BigDecimal discountedAmount(BigDecimal amount,int discountPercent){
		BigDecimal
		quotient = BigDecimal.valueOf(Precision.multiply(discountPercent,0.01d)),
		discount = amount.multiply(quotient);
		return amount.subtract(discount).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	public static BigDecimal sum(BigDecimal[] sums){
		BigDecimal sum = BigDecimal.valueOf(0);
		for(int i=0;i<sums.length;i++){
			sum = sum.add(sums[i]);
		}
		return sum;
	}
}
