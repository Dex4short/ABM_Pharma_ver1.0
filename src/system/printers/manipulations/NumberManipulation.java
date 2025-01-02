package system.printers.manipulations;

import java.math.BigDecimal;

public class NumberManipulation {
	private BigDecimal number;
	
	public NumberManipulation(BigDecimal num) {
		number = num;
	}
	public String newDecimalValue(int deciamlPlaces){
		return number.setScale(deciamlPlaces, BigDecimal.ROUND_HALF_EVEN).toString();
	}
	public static BigDecimal newDecimalValue(BigDecimal num,int deciamlPlaces){
		return num.setScale(deciamlPlaces, BigDecimal.ROUND_HALF_EVEN);
	}
	public static String setDigits(int num,int digits){
		String newNum = num+"";
		int length = Math.abs(digits) - newNum.length();
		for(int n=0;n<length;n++){
			if(digits < 0){
				newNum = "0" + newNum;
			}
			else{
				newNum += "0";
			}
		}
		return newNum;
	}
	public static String toAccountancyFormat(BigDecimal value){
		String str_double = value.toString(),newValue = "";
		int length = str_double.indexOf(".") - 1;
		for(int i=length,ii=0;i>=0;i--){
			if(ii < 3){
				newValue = str_double.substring(i,i+1) + newValue;
			}
			else{
				newValue = str_double.substring(i,i+1) + "," + newValue;
				ii=0;
			}
			ii++;
		}
		return newValue + str_double.substring(length + 1);
	}
}
