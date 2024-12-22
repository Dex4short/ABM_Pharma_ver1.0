package components.fields;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import oop.essentials.Settings;
import oop.interfaces.Theme;

public class PasswordField  extends JPasswordField implements Theme{
	private static final long serialVersionUID = -6007197876089778096L;
	private String message;
	private Graphics2D g2d;
	
	{
		setOpaque(false);
		
		Dimension dimension = new Dimension(160, 30);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
		setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		setForeground(text_color[1]);
		
		message = "Password";
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		
		g2d.setColor(main_color[3]);
		g2d.fill(g2d.getClip());
		
		if(getPassword().length == 0) {
			g2d.setFont(font[0]);
			g2d.setColor(text_color[1]);
			g2d.drawString(
					message,
					10,
					(getHeight()/2) + (g2d.getFontMetrics().getAscent()/2)
			);
		}
		
		super.paint(g2d);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
		repaint();
	}
}
