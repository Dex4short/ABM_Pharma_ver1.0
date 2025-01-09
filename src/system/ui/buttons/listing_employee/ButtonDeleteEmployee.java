package system.ui.buttons.listing_employee;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.ui.Window;

public class ButtonDeleteEmployee extends Button implements ActionListener{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDeleteEmployee() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("delete.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.floatMessageAndBeep("Currently not avialable");
	}
	
}
