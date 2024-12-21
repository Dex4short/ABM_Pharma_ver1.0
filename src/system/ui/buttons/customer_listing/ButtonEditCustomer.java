package system.ui.buttons.customer_listing;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonEditCustomer extends Button{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("pencil.png")));
	}

}
