package components;

import javax.swing.Icon;
import javax.swing.JLabel;

import oop.interfaces.Theme;
public class Label extends JLabel implements Theme{
	private static final long serialVersionUID = -1236396874946398354L;

	public Label() {
		super("", null, JLabel.LEFT);
		
		setOpaque(false);
		setForeground(text_color[0]);
		setFont(font[0]);
	}	
	public Label(String str) {
		super(str, null, JLabel.LEFT);
		
		setOpaque(false);
		setForeground(text_color[0]);
		setFont(font[0]);
		
		setName(str);
	}
	public Label(Icon icon) {
		super("", icon, JLabel.LEFT);
		
		setOpaque(false);
		setForeground(text_color[0]);
		setFont(font[0]);
	}
	public Label(Icon icon, String str) {
		super(str, icon, JLabel.LEFT);
		
		setOpaque(false);
		setForeground(text_color[0]);
		setFont(font[0]);
		
		setName(str);
	}
}
