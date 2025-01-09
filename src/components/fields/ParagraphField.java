package components.fields;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import components.drawables.RoundRect;
import system._default_.Settings;
import system.ui.appearance.Theme;

public class ParagraphField extends JTextArea implements Theme{
	private static final long serialVersionUID = 7124779313820186668L;
	private RoundRect round_rect;

	public ParagraphField() {
		setOpaque(false);
		setAutoscrolls(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		setCaretColor(text_color[0]);
		
		round_rect = new RoundRect();
		
		setArc(10);
		setFont(font[0]);
		setBackground(main_color[3]);
		setForeground(text_color[0]);
	}
	private Graphics2D g2d;
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		
		round_rect.setBounds(0,0,getWidth(),getHeight());
		round_rect.draw(g2d);
		
		super.paint(g2d);
	}
	public RoundRect getRoundRect() {
		return round_rect;
	}
	public void setRoundRect(RoundRect round_rect) {
		this.round_rect = round_rect;
	}
	public void setArc(int arc) {
		round_rect.setArc(arc);
		setBorder(BorderFactory.createEmptyBorder(arc/2, arc/2, arc/2, arc/2));
	}
	public int getArc() {
		return round_rect.getArc();
	}
}
