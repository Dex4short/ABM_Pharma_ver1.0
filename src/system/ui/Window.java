package system.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import components.drawables.MessageFloater;
import components.panels.StackPanel;
import extras.Settings;
import oop.interfaces.Theme;
import system.ui.panels.PanelAdmin;
import system.ui.panels.PanelEmployee;
import system.ui.panels.PanelLogin;

public class Window extends JFrame implements Theme{
	private static final long serialVersionUID = -6729595845570486177L;
	private static StackPanel stack_panel;
	private static MessageFloater message_floater;

	public Window() {
		setTitle("ABM Pharma - DIMS Rev.1");
		setLayout(null);
		
		stack_panel = new StackPanel() {
			private static final long serialVersionUID = -1131072990529675644L;
			private Graphics2D g2d;
			@Override
			public void paint(Graphics g) {
				g2d = (Graphics2D)g;
				Settings.rendering_hint(g2d);

				super.paint(g2d);

				message_floater.setBounds(getBounds());
				message_floater.draw(g2d);
			}
		};
		stack_panel.setBackground(main_color[0]);
		stack_panel.setName("abm");
		add(stack_panel, BorderLayout.CENTER);
		
		PanelLogin panel_login = new PanelLogin() {
			private static final long serialVersionUID = 179005001334507847L;
			@Override
			public void openAdminInterface() {
				stack_panel.popPanel();
				stack_panel.pushPanel(new PanelAdmin());
			}
			@Override
			public void openEmployeeInterface() {
				stack_panel.popPanel();
				stack_panel.pushPanel(new PanelEmployee());
			}
		};
		stack_panel.pushPanel(panel_login);
		
		message_floater = new MessageFloater();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				stack_panel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
				revalidate();
				repaint();
			}
		});

		Window.floatMessage("ABM Pharma");
	}
	public static StackPanel getStackPanel() {
		return stack_panel;
	}
	public static void floatMessage(String message) {
		message_floater.floatMessage(message);
	}
	public static void floatMessageAndBeep(String message) {
		message_floater.floatMessageAndBeep(message);
	}
}
