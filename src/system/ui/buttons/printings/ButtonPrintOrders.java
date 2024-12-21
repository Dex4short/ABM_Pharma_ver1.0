package system.ui.buttons.printings;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonPrintOrders extends Button{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintOrders() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("printer.png")));
	}
}
