package oop;

import java.math.BigDecimal;

import extras.Utilities;

public class Percentage {
	private String percent_value = "0%";
	/**
	 * 
	 * @param percent_value with % sign, "0%"~"100%".
	 */
	public Percentage(String percent_value){
		if(percent_value.charAt(percent_value.length() - 1) == '%') {
			this.percent_value = percent_value;
		}
		else {
			Utilities.printStackTraceAsWarning("percent value must have a percent symbol", Thread.currentThread());
			this.percent_value = null;
		}
	}
	@Override
	public String toString() {
		return percent_value;
	}
	public BigDecimal toBigDecimal() {
		String str = percent_value.replace("%", "");
		return new BigDecimal(str).multiply(new BigDecimal("0.01"));
	}
}
