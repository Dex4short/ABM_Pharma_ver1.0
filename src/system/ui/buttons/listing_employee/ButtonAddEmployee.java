package system.ui.buttons.listing_employee;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.ui.Window;

public class ButtonAddEmployee extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddEmployee() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Employee");
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.floatMessageAndBeep("Currently not available");
	}
	
}
