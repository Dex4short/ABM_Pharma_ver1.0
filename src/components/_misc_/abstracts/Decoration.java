package components._misc_.abstracts;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;

public abstract class Decoration {
	private Font font;
	private Color background,foreground,border_color;

	public Decoration(Font font, Color foreground, Color background) {
		setFont(font);
		setForeground(foreground);
		setBackground(background);
	}
	public Decoration(Font font, Color foreground, Color background, Color border_color) {
		setFont(font);
		setForeground(foreground);
		setBackground(background);
		setBorderColor(border_color);
	}
	public void decorate(JComponent jcomponent) {
		setFont(font);
		setForeground(foreground);
		setBackground(background);
		setBorderColor(border_color);
		onDecorate(jcomponent);
	}
	public abstract void onDecorate(JComponent jcomponent);
	
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
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
	public Color getBorderColor() {
		return border_color;
	}
	public void setBorderColor(Color border_color) {
		this.border_color = border_color;
	}
	
	public static class Basic extends Decoration {
		public Basic(Font font, Color foreground, Color background) {
			super(font, foreground, background);
		}
		@Override
		public void onDecorate(JComponent jcomponent) {
			jcomponent.setBackground(getBackground());
			jcomponent.setForeground(getForeground());
			jcomponent.setFont(getFont());
		}
	}
	public static class Bordered extends Decoration {
		public Bordered(Font font, Color foreground, Color background, Color border_color) {
			super(font, foreground, background, border_color);
		}
		@Override
		public void onDecorate(JComponent jcomponent) {
			jcomponent.setBackground(getBackground());
			jcomponent.setForeground(getForeground());
			jcomponent.setFont(getFont());
		}
	}
}
