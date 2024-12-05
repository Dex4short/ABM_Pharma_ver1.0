package system.ui.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonPrintTransactions extends Button{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintTransactions() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("printer.png")));
	}
}
