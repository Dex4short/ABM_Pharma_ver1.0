package oop.interfaces;

import java.awt.Graphics2D;

public interface Drawable {

	public default void draw(Graphics2D g2d) {
		onDraw(g2d);
	}
	public void onDraw(Graphics2D g2d);
}
