package components.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import oop.interfaces.Drawable;
import oop.interfaces.Theme;

public class RoundRect extends Rectangle implements Drawable, Theme{
	private static final long serialVersionUID = 4030863947786279493L;
	private Color background, foreground;
	private int arc=0;

	public RoundRect() {
		background = main_color[2];
		foreground = main_color[3];
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		g2d.setColor(background);
		g2d.fillRoundRect(x, y, width, height, arc, arc);
		
		g2d.setColor(foreground);
		g2d.drawRoundRect(x, y, width-1, height-1, arc, arc);
	}
	public Color getBackground() {
		return background;
	}
	public void setBackground(Color background) {
		this.background = background;
	}
	public Color getForeground() {
		return foreground;
	}
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	public int getArc() {
		return arc;
	}
	public void setArc(int arc) {
		this.arc = arc;
	}
}
