package oop;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Decimal {
	private BigDecimal bigdecimal_value;
	
	public Decimal() {
		this.bigdecimal_value = new BigDecimal("0.00");
	}
	public Decimal(BigDecimal bigdecimal_value) {
		this.bigdecimal_value = bigdecimal_value;
	}
	public Decimal(String bigdecimal_value) {
		this.bigdecimal_value = new BigDecimal(bigdecimal_value);
	}
	@Override
	public String toString() {
		return new DecimalFormat("#,##0.00").format(bigdecimal_value.doubleValue());
	}
	public BigDecimal toBigDecimal() {
		return bigdecimal_value;
	}
	public Decimal add(Decimal decimal) {
		return new Decimal(bigdecimal_value.add(decimal.bigdecimal_value));
	}
	public Decimal subtract(Decimal decimal) {
		return new Decimal(bigdecimal_value.subtract(decimal.bigdecimal_value));
	}
	public Decimal multiply(Decimal decimal) {
		return new Decimal(bigdecimal_value.multiply(decimal.bigdecimal_value));
	}
	public Decimal divide(Decimal decimal) {
		return new Decimal(bigdecimal_value.divide(decimal.bigdecimal_value));
	}
}
