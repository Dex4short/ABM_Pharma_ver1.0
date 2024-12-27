package system.ui.buttons.accessibility;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import components._misc_.Graphix;
import res.Resource;
import system._default_.Settings;
import system.managers.NotificationsManager;
import system.ui.Window;

public class ButtonNotifications extends Button.Tertiary implements ActionListener{
	private static final long serialVersionUID = -617514616688765454L;
	
	public ButtonNotifications() {
		setArc(30);
		setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
		
		ImageIcon
		img_ico = Resource.getAsImageIcon("notifications.png");
		img_ico = Graphix.alterImageIcon(img_ico, main_color[0], getFocusCycleRootAncestor());
		setIcon(img_ico);
		
		addActionListener(this);
	}
	private Graphics2D g2d;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		NotificationsManager.getDot().draw(g2d);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getPopUpPanel().popUp(NotificationsManager.getPopupNotifications());
	}
}
