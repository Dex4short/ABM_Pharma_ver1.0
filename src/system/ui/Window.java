package system.ui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import components._misc_.Graphix.Shadow;
import components.drawables.MessageFloater;
import components.panels.DialogPanel;
import components.panels.PopUpPanel;
import components.panels.StackPanel;
import system.ui.appearance.Theme;
import system.ui.overlays.LoadingScreen;
import system.ui.overlays.LoadingScreenAdapater;
import system.ui.panels.PanelAdmin;
import system.ui.panels.PanelEmployee;
import system.ui.panels.PanelLogin;

public class Window extends JFrame implements Theme{
	private static final long serialVersionUID = -6729595845570486177L;
	private static StackPanel stack_panel;
	private static PopUpPanel popup_panel;
	private static MessageFloater message_floater;

	public Window() {        
		setTitle("ABM Pharma - DIMS Rev.2");
		setLayout(null);

		//String theme_mode = InputOutput.read("theme_settings.txt");
		//if(theme_mode.equals("Dark Mode")) darkMode();
		//else lightMode();
		
		message_floater = new MessageFloater();

		popup_panel = new PopUpPanel();
		popup_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		popup_panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
		add(popup_panel);
		
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
		add(stack_panel);
		
		
		PanelLogin panel_login = new PanelLogin() {
			private static final long serialVersionUID = 179005001334507847L;
			@Override
			public void onOpenAdminInterface() {
				stack_panel.popPanel();
				load(() -> {
					PanelAdmin panel_admin = new PanelAdmin();
					stack_panel.pushPanel(panel_admin);

					floatMessage("Welcome Admin");
					panel_admin.toInventory();
				});
			}
			@Override
			public void onOpenEmployeeInterface() {
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
				popup_panel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
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
		}, main_color[0], main_color[2], "Loading...");
	}
	public static void load(Runnable runnable, String load_name) {//needs better approach	
		LoadingScreen loading_screen = new LoadingScreenAdapater();
		getStackPanel().pushPanel(loading_screen);
		
		loading_screen.load(() -> {
			runnable.run();
			
			getStackPanel().popPanel(loading_screen);
		}, Shadow.shadow_color, main_color[2], load_name);
	}
	public static PopUpPanel getPopUpPanel() {
		return popup_panel;
	}
	public static void pushDialog(String title, String message) {
		stack_panel.pushPanel(new DialogPanel(title) {
			private static final long serialVersionUID = -6404723525688230016L;
			{
				setText(message);
				getButtonOk().setVisible(false);
				getButtonCancel().setText("close");
				Toolkit.getDefaultToolkit().beep();
			}
			@Override
			public void onOk() {
				//hidden
			}
			@Override
			public void onCancel() {
				stack_panel.popPanel();
			}
		}, 200, 200);
	}
}
