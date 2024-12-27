package system.managers;

import java.time.LocalDate;

import system.enumerators.Quality;
import system.objects.Date;
import system.objects.Product;

public class QualityManager {

	private QualityManager() {
		
	}
	public static boolean bestBefore(Date date, Date expiry, int month_margin) {		
		LocalDate date_add = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
		LocalDate date_exp = LocalDate.of(expiry.getYear(), expiry.getMonth(), expiry.getDay());
		return date_add.compareTo(date_exp.minusMonths(month_margin)) <= 0;
	}
	public static Quality isExpired(Date expiry) {
		Date today = new Date();

		if(QualityManager.bestBefore(today, expiry, 9))return Quality.Good;
		else if(QualityManager.bestBefore(today, expiry, 4)) return Quality.Warning;
		else if(QualityManager.bestBefore(today, expiry, 0)) return Quality.Bad;
		else return Quality.Expired;
	}
	public static Quality isExpired(Product product) {
		return isExpired(product.getItem().getExpDate());
	}
}
