package system.ui.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;


public class ButtonReturnToInventory extends Button{
	private static final long serialVersionUID = 2163543714902288437L;

	public ButtonReturnToInventory() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("Inventory.png")));
	}
}