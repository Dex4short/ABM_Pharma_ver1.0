package system.ui.buttons.printings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.ui.Window;

public class ButtonPrintProducts extends Button implements ActionListener{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintProducts() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		
		setIcon(new ImageIcon(Resource.get("printer.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.floatMessageAndBeep("currently not available");
	}
}
