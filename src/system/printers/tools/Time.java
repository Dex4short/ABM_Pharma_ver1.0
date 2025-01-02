package system.printers.tools;

import java.util.Calendar;
import system.printers.manipulations.NumberManipulation;


public abstract class Time {

	public static String getClock(){
		return Calendar.getInstance().getTime().toString().substring(11,19);
	}
	public static String getCalendar(){
		Calendar calendar = Calendar.getInstance();
		String
		m = NumberManipulation.setDigits(calendar.get(Calendar.MONTH) + 1,-2),
		d = NumberManipulation.setDigits(calendar.get(Calendar.DATE),-2),
		y = calendar.get(Calendar.YEAR)+"";
		return m+"-"+d+"-"+y;
	}
	public static String getDueDate(int ms,int ds, int yrs){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,ms);
		calendar.add(Calendar.DATE,ds);
		calendar.add(Calendar.YEAR,yrs);
		String
		m = NumberManipulation.setDigits(calendar.get(Calendar.MONTH) + 1,-2),
		d = NumberManipulation.setDigits(calendar.get(Calendar.DATE),-2),
		y = calendar.get(Calendar.YEAR)+"";
		return m+"-"+d+"-"+y;
	}
}
