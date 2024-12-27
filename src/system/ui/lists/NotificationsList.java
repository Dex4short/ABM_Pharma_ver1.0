package system.ui.lists;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.ParagraphField;
import components.list.Item;
import components.list.ListPane;
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
	public void addNotifications(Notification notifications[]) {
		NotificationItem items [] = new NotificationItem[notifications.length];
		for(int i=0; i<items.length; i++) {
			items[i] = new NotificationItem(notifications[i]); 
		}
		addItems(items);
	}
	public void removeAllNotifications() {
		removeAllItems();
	}
	
	public static class NotificationItem extends Item{
		private static final long serialVersionUID = -4883264937949635701L;
		
		public NotificationItem(Notification notification) {
			setArc(10);
			setLayout(new BorderLayout(5, 5));
			setForeground(main_color[3]);
			
			add(new Label(notification.getImageIcon(), notification.getTitle()), BorderLayout.NORTH);
			
			ParagraphField paragraph_field = new ParagraphField();
			paragraph_field.setText(notification.getDetails());
			add(paragraph_field);
			
			setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		}
	}
	
}
