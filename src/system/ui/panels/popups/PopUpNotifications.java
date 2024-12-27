package system.ui.panels.popups;

import java.awt.BorderLayout;
import java.awt.Dimension;

import components.panels.TitledPanel;
import system._default_.Notifications;
import system.ui.lists.NotificationsList;

public class PopUpNotifications extends TitledPanel implements Notifications{
	private static final long serialVersionUID = -2122393070139823198L;
	private NotificationsList notifications_list;

	public PopUpNotifications() {
		super("Notifications");
		
		setPreferredSize(new Dimension(300,300));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		
		getPanelBody().setLayout(new BorderLayout());
		
		getPanelBody().add(notifications_list = new NotificationsList());
		notifications_list.setItemHeight(100);
		
	}
	public NotificationsList getNotificationsList() {
		return notifications_list;
	}	
}
