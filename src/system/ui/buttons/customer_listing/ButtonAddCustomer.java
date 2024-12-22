package system.ui.buttons.customer_listing;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;

public class ButtonAddCustomer extends Button{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Customer");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//Window.getStackPanel().pushPanel();
	}
}
