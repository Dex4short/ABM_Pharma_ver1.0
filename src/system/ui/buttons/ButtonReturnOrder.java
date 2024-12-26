package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.ui.Window;

public abstract class ButtonReturnOrder extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonReturnOrder() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("return.png")));
		setText("Return Order");
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.floatMessageAndBeep("currently not available");
	}
	public void returnOrder() {
		onReturnOrder();
	}
	public abstract void onReturnOrder();
}
