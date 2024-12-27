package system.managers;

import components.drawables.Dot;
import system.objects.Notification;
import system.ui.panels.popups.PopUpNotifications;

public class NotificationsManager {
	private static final PopUpNotifications popup_notifications = new PopUpNotifications();
	private static final Dot dot = new Dot();

	private NotificationsManager() {
		
	}
	public static void pushNotification(Notification notification) {
		popup_notifications.getNotificationsList().addNotification(notification);
		dot.setShow(popup_notifications.getNotificationsList().getItemCount() > 0);
	}
	public static void clearNotifications() {
		popup_notifications.getNotificationsList().removeAllNotifications();
	}
	public static PopUpNotifications getPopupNotifications() {
		return popup_notifications;
	}
	public static Dot getDot() {
		return dot;
	}
}
