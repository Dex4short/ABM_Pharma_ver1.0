package system._default_;

import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.managers.NotificationsManager;
import system.objects.Product;
import system.ui.PanelAdmin;

public interface Notifications {

	public static void checkInventoryProducts() {
		for(Product product: MySQL_Products.selectProducts(ProductCondition.STORED)) {
			if(NotificationsManager.pushNotification(product) != null) PanelAdmin.notifyTab(0, true);
		}
	}
	public static void checkReservedProducts() {
		for(Product product: MySQL_Products.selectProducts(ProductCondition.RESERVED)) {
			if(NotificationsManager.pushNotification(product) != null) PanelAdmin.notifyTab(3, true);
		}
	}
}
