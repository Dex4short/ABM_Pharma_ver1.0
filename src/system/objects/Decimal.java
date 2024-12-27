package system.objects;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decimal {
	private BigDecimal bigdecimal_value;
	
	public Decimal() {
		this.bigdecimal_value = new BigDecimal("0.00");
	}
	public Decimal(BigDecimal bigdecimal_value) {
		this.bigdecimal_value = bigdecimal_value;
	}
	public Decimal(String bigdecimal_value) {
		if(isFormatValid(bigdecimal_value)) {
			this.bigdecimal_value = new BigDecimal(bigdecimal_value.replace(",", ""));
		}
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
	
	public static boolean isFormatValid(String bigdecimal_value) {
		String regex = "^(?:\\d{1,3})(?:,\\d{3})*\\.\\d{2}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(bigdecimal_value);
		if(!matcher.matches()) {
			throw new RuntimeException("Invalid decimal format (0,000.00) for " + bigdecimal_value);
		}
		return matcher.matches();
	}
}
