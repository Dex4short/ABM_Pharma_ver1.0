package system;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import system.managers.NotificationsManager;
import system.ui.Window;

public class ABM_Pharma {
	
	public static void main(String[]args) {
		system_initialization();
		
		Window window = new Window();	
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
	}
	public static void showError(Exception e, int close_operation) {
		JFrame window_error = new JFrame("Error") {
			private static final long serialVersionUID = 4255022013756630053L;
			{
				setLayout(new BorderLayout());
				
				StringWriter sw = new StringWriter();
			    PrintWriter pw = new PrintWriter(sw);
			    e.printStackTrace(pw);
			    
				TextArea txt_area = new TextArea(pw.toString());
				txt_area.setEditable(false);
				add(new JScrollPane(txt_area));
			}
		};
		window_error.setSize(400, 400);
		window_error.setLocationRelativeTo(null);
		window_error.setDefaultCloseOperation(close_operation);
		window_error.setVisible(true);
	}
	
	private static void system_initialization() {
		NotificationsManager.initializeNotifications();
	}
	
}
