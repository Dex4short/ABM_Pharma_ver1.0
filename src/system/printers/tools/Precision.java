package system.printers.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Precision {
	public static final int IS_EQUAL=0,IS_GREATER_THAN=1,IS_LESS_THAN=2,IS_GREATER_OR_EQUAL=3,IS_LESS_OR_EQUAL=4,IS_NOT_EQUAL=5;

	public static double add(double a,double b){
		return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double subtract(double a,double b){
		return BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double multiply(double a,double b){
		return BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double divide(double a,double b){
		return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),3,RoundingMode.HALF_EVEN).doubleValue();
	}
	public static boolean compare(double a,double b,int is){
		switch (is) {
		case IS_EQUAL:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) == 0;
		case IS_GREATER_THAN:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) >  0;
		case IS_LESS_THAN:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) <  0;
		case IS_GREATER_OR_EQUAL:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) >= 0;
		case IS_LESS_OR_EQUAL:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) <= 0;
		default:
			return BigDecimal.valueOf(a).compareTo(BigDecimal.valueOf(b)) != 0;
		}
	}
}
