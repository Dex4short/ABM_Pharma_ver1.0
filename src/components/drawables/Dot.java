package components.drawables;

import java.awt.Color;
import java.awt.Graphics2D;

import components._misc_.interfaces.Drawable;

public class Dot implements Drawable{
	private Color color = Color.red;
	private boolean show = false;
	
	@Override
	public void onDraw(Graphics2D g2d) {
		if(!show) return;
		g2d.setColor(color);
		g2d.fillOval(0, 0, 5, 5);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public void show() {
		setShow(true);
	}
	public void hide() {
		setShow(false);
	}
	
}
