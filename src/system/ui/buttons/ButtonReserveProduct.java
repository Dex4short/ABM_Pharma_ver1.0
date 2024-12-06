package system.ui.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonReserveProduct extends Button{
	private static final long serialVersionUID = -2701202578036756483L;

	public ButtonReserveProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("reserve.png")));
	}

}
