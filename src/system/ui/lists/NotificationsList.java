package system.ui.lists;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.ParagraphField;
import components.list.Item;
import components.list.ListPane;
import system.objects.Notification;
import system.objects.Product;

public class NotificationsList extends ListPane{
	private static final long serialVersionUID = 4135976497237412092L;

	public NotificationsList() {
		setItemHeight(100);
		setSelectionEnabled(false);
	}
	public void addNotification(Notification notification) {
		addItem(new NotificationItem(notification));
	}
	public void addNotifications(Notification notifications[]) {
		NotificationItem items [] = new NotificationItem[notifications.length];
		for(int i=0; i<items.length; i++) {
			items[i] = new NotificationItem(notifications[i]); 
		}
		addItems(items);
	}
	public Notification[] getNotifications() {
		Item items[] = getItems();
		Notification notifications[] = new Notification[items.length];
		for(int n=0; n<notifications.length; n++) {
			notifications[n] = ((NotificationItem)items[n]).getNotification();
		}
		return notifications;
	}
	public void removeNotification(int n) {
		removeItem(n);
	}
	public void removeAllNotifications() {
		removeAllItems();
	}
	public Notification findNotificationFor(Product product) {
		for(Notification notification: getNotifications()) {
			if(notification.getProduct() == product) return notification;
		}
		return null;
	}
	public int findNotificationIndexFor(Product product) {
		Item items[] = getItems();
		for(int n=0; n<items.length; n++) {
			if(((NotificationItem)items[n]).getNotification().getProduct() == product) {
				return n;
			}
		}
		return -1;
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
