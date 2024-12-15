package system.ui.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonDeleteCustomer extends Button{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDeleteCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("delete.png")));
	}
}
