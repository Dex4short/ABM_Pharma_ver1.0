package system.ui.lists;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.ParagraphField;
import components.list.Item;
import components.list.ListPane;
import system.enumerators.NotificationType;
import system.objects.Notification;

public class NotificationsList extends ListPane{
	private static final long serialVersionUID = 4135976497237412092L;

	public NotificationsList() {
		setItemHeight(100);
		setSelectionEnabled(false);
	}
	public void addNotification(Notification notification) {
		addItem(new NotificationItem(notification));
	}
	public Notification getNotification(NotificationType notification_type, int notification_id) {
		for(Notification notification: getNotifications()) {
			if(notification.getNotificationType()==notification_type && notification.getNotificationId()==notification_id) return notification;
		}
		return null;
	}
	public Notification[] getNotifications() {
		Item items[] = getItems();
		Notification notifications[] = new Notification[items.length];
		for(int n=0; n<notifications.length; n++) {
			notifications[n] = ((NotificationItem)items[n]).getNotification();
		}
		return notifications;
	}
	public int getNotificationsCount() {
		return getItemCount();
	}
	public void removeNotification(NotificationType notification_type, int notification_id) {
		Notification notification;
		for(Item item: getItems()) {
			notification = ((NotificationItem)item).getNotification();
			if(notification.getNotificationType()==notification_type && notification.getNotificationId()==notification_id) {
				removeItem(item);
				return;
			}
		}
	}
	public void removeAllNotifications() {
		removeAllItems();
	}
	
	public static class NotificationItem extends Item{
		private static final long serialVersionUID = -4883264937949635701L;
		private Notification notification;

		public NotificationItem(Notification notification) {
			setArc(10);
			setForeground(main_color[3]);
			setLayout(new BorderLayout(5, 5));
			setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			
			add(new Label(notification.getImageIcon(), notification.getTitle()), BorderLayout.NORTH);
			
			ParagraphField paragraph_field = new ParagraphField();
			paragraph_field.setText(notification.getDetails());
			add(paragraph_field);
			
			setNotification(notification);
		}
		public Notification getNotification() {
			return notification;
		}
		public void setNotification(Notification notification) {
			this.notification = notification;
		}
	}
	
}
