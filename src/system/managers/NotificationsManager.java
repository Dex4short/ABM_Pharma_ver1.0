package system.managers;

import components.drawables.Dot;
import system._default_.Notifications;
import system.enumerators.NotificationType;
import system.enumerators.Quality;
import system.objects.Notification;
import system.objects.Product;
import system.ui.lists.NotificationsList;

public class NotificationsManager {
	public static NotificationsList notifications_list;
	public static Dot dot; 

	private NotificationsManager() {
		
	}
	public static Notification pushNotification(Product product) {
		NotificationsManager.pullNotification(NotificationType.Stocks, product.getProdId());
		NotificationsManager.pullNotification(NotificationType.Quality, product.getProdId());
		Notification notification = createNotification(product);
		if(notification != null) {
			pushNotification(notification);
			dot.setShow(true);
		}
		return notification;
	}
	public static void pushNotification(Notification notification) {
		notifications_list.addNotification(notification);
		dot.setShow(notifications_list.getNotificationsCount() > 0);
	}
	public static void pullNotification(NotificationType notification_type,int notification_id) {
		notifications_list.removeNotification(notification_type ,notification_id);
		dot.setShow(notifications_list.getNotificationsCount() > 0);
	}
	public static void clearNotifications() {
		notifications_list.removeAllNotifications();
		dot.setShow(notifications_list.getNotificationsCount() > 0);
	}
	public static void initializeNotifications() {
		notifications_list = new NotificationsList();
		notifications_list.setItemHeight(100);
		dot = new Dot();
	}
	public static Notification createNotification(Product product) {
		boolean 
		warning = PackagingManager.isRunningOut(product.getPackaging()),
		caution = PackagingManager.isOutOfStock(product.getPackaging());
		if(caution) {
			return new Notification.OutOfStock(product);
		}
		else if(warning) {
			return new Notification.RunningOutOfStock(product);
		}
		else {
			Quality quality = QualityManager.checkQuality(product.getItem().getExpDate());
			if(quality==Quality.Warning || quality==Quality.Bad || quality==Quality.Expired) {
				return new Notification.ProductQuality(product);
			}
		}
		return null;
	}
	public static void checkInventoryProducts() {
		Notifications.checkInventoryProducts();
	}
	public static void checkReservedProducts() {
		Notifications.checkReservedProducts();
	}
	public static void checkProducts() {
		checkInventoryProducts();
		checkReservedProducts();
	}
}
