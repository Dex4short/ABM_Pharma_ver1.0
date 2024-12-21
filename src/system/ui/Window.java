package system.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import components.drawables.MessageFloater;
import components.panels.StackPanel;
import extras.Graphix.Shadow;
import oop.interfaces.Theme;
import system.ui.overlays.LoadingScreen;
import system.ui.overlays.LoadingScreenAdapater;
import system.ui.panels.PanelAdmin;
import system.ui.panels.PanelEmployee;
import system.ui.panels.PanelLogin;

public class Window extends JFrame implements Theme{
	private static final long serialVersionUID = -6729595845570486177L;
	private static StackPanel stack_panel;
	private static MessageFloater message_floater;

	public Window() {        
		setTitle("ABM Pharma - DIMS Rev.2");
		setLayout(null);

		message_floater = new MessageFloater();
		
		stack_panel = new StackPanel() {
			private static final long serialVersionUID = 2661178564787006879L;
			private Graphics2D g2d;
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				g2d = (Graphics2D)g;
				message_floater.setBounds(0, 0, getWidth(), getHeight());
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
				load(() -> {
					PanelAdmin panel_admin = new PanelAdmin();
					stack_panel.pushPanel(panel_admin);

					floatMessage("Welcome Admin");
					panel_admin.toInventory();
				});
			}
			@Override
			public void openEmployeeInterface() {
				stack_panel.popPanel();
				load(() -> {
					PanelEmployee panel_employee = new PanelEmployee();
					stack_panel.pushPanel(panel_employee);
				});
			}
		};
		stack_panel.pushPanel(panel_login);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				stack_panel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
				revalidate();
				repaint();
			}
		});
		
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
	public static void load(Runnable runnable) {//needs better approach		
		LoadingScreen loading_screen = new LoadingScreenAdapater();
		getStackPanel().pushPanel(loading_screen);
		
		loading_screen.load(() -> {
			runnable.run();
			getStackPanel().popPanel(loading_screen);
		}, 0, main_color[0], main_color[2], "Loading...");
	}
	public static void load(Runnable runnable, String load_name) {//needs better approach	
		LoadingScreen loading_screen = new LoadingScreenAdapater();
		getStackPanel().pushPanel(loading_screen);
		
		loading_screen.load(() -> {
			runnable.run();
			getStackPanel().popPanel(loading_screen);
		}, 0, Shadow.shadow_color, main_color[2], load_name);
	}
}
