package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import system.ui.Window;

public class ABM_Pharma {
	public static boolean loading=false;

	public static void main(String[]args) {		
		Window window = new Window();	
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
	}
    public static void showHangDialog() {
        JDialog dialog = new JDialog((Frame) null, "Program Hang Detected", true);
        dialog.setContentPane(new JPanel() {
			private static final long serialVersionUID = 7342482304529139736L;
			{
				setBackground(Color.white);
				setBorder(BorderFactory.createEmptyBorder(15,30,30,30));
			}
        });
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JTextArea message_area = new JTextArea(
        	"The program appears to be hanging!\n\n" +
        	"Click 'Dismiss' to close the program and start again..."
        );
        message_area.setWrapStyleWord(true);
        message_area.setLineWrap(true);
        message_area.setEditable(false);
        dialog.add(message_area, BorderLayout.NORTH);
        
        JButton dismissButton = new JButton("Dismiss");
        dismissButton.addActionListener(e -> {
        	dialog.dispose();
        	System.exit(0);
        });
        dialog.add(dismissButton, BorderLayout.SOUTH);

        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
