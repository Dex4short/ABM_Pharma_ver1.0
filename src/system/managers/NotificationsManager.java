package system.managers;

import components.drawables.Dot;
import system.objects.Notification;
import system.objects.Product;
import system.ui.lists.NotificationsList;

public class NotificationsManager {
	private static final Dot dot = new Dot();
	private static final NotificationsList notifications_list = new NotificationsList() {
		private static final long serialVersionUID = -3371711643162309127L;
		{
			setItemHeight(100);
		}
	};

	private NotificationsManager() {
		
	}
	public static void pushNotification(Notification notification) {
		notifications_list.addNotification(notification);
		dot.setShow(notifications_list.getItemCount() > 0);
	}
	public static void removeNotificationFor(Product product) {
		int index = notifications_list.findNotificationIndexFor(product);
		notifications_list.removeNotification(index);
		dot.setShow(notifications_list.getItemCount() > 0);
	}
	public static void clearNotifications() {
		notifications_list.removeAllNotifications();
		dot.hide();
	}
	public static NotificationsList getNotificationsList() {
		return notifications_list;
	}
	public static Dot getDot() {
		return dot;
	}
}
