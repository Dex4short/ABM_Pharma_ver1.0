package system.ui.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonPrintProduct extends Button{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("printer.png")));
	}
}
