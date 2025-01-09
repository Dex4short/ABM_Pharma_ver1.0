package components.fields;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import system.ui.appearance.Theme;

public class TextField extends JTextField implements Theme{
	private static final long serialVersionUID = 5030767401767931571L;
	private Color line_color;

	{
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0 ,0));
		setCaretColor(text_color[0]);
		
		setFont(font[0]);
		setBackground(new Color(0,0,0,0));
		setForeground(text_color[0]);
		setLineColor(main_color[3]);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isEnabled()) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
	}
	public TextField() {
		super();
	}
	public TextField(String txt) {
		super(txt);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(getLineColor());
		g.drawLine(0, getHeight()-2, getWidth()-5, getHeight()-2);
		super.paint(g);
	}
	public Color getLineColor() {
		return line_color;
	}
	public void setLineColor(Color line_color) {
		this.line_color = line_color;
	}
}
