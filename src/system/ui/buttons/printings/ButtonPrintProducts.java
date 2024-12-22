package system.ui.buttons.printings;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonPrintProducts extends Button{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintProducts() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		setIcon(new ImageIcon(Resource.get("printer.png")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//TODO
	}	
}
