package system;

import javax.swing.JFrame;

import system.ui.Window;

public class ABM_Pharma {

	public static void main(String[]args) {	
		Window window = new Window();	
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
	}
}
