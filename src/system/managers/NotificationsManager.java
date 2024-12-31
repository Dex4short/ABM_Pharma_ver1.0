package system.managers;

import components.drawables.Dot;
import system.enumerators.Quality;
import system.objects.Notification;
import system.objects.Product;
import system.ui.lists.NotificationsList;

public class NotificationsManager {
	public static NotificationsList notifications_list;
	public static Dot dot; 

	private NotificationsManager() {
	}
	public static void pushNotification(Notification notification) {
		notifications_list.removeNotification(notification.getProduct().getProdId());
		notifications_list.addNotification(notification);
		
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
}
