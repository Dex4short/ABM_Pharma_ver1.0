package system.objects;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Percentage {
	private String percent_value = "0%";
	/**
	 * 
	 * @param percent_value with % sign, "0%"~"100%".
	 */
	public Percentage(String percent_value){
		if(isFormatValid(percent_value)) {
			this.percent_value = percent_value;
		}
	}
	public Percentage() {
		percent_value = "0%";
	}
	@Override
	public String toString() {
		return percent_value;
	}
	public int toInt() {
		return Integer.parseInt(percent_value.replace("%", ""));
	}
	public BigDecimal toBigDecimal() {
		String str = percent_value.replace("%", "");
		return new BigDecimal(str).multiply(new BigDecimal("0.01"));
	}
	public boolean isFormatValid(String percent_value) {
		String regex = "^(0|[1-9][0-9]?|100)%$";
		
		boolean match = Pattern.matches(regex, percent_value);
		if(!match) {
			throw new RuntimeException("Invalid percentage format (0% ~ 100%)");
		};
		
		return match;
	}
}
