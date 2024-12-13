package essentials;

import java.time.LocalDate;

import oop.Date;

public class ProductChecker {

	private ProductChecker() {
		
	}
	public static boolean bestBefore(Date date, Date expiry, int month_margin) {		
		LocalDate date_add = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
		LocalDate date_exp = LocalDate.of(expiry.getYear(), expiry.getMonth(), expiry.getDay());
		return date_add.compareTo(date_exp.minusMonths(month_margin)) <= 0;
	}
}
