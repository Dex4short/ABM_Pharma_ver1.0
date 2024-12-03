package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

import extras.Graphix;
import extras.Settings;
import oop.abstracts.Decoration;
import oop.interfaces.Theme;

public class Button extends JButton implements Theme, MouseListener{
	public static final int Normal_State=0,Hovered_State=1,Pressed_State=2, Disabled_State=3;
	private static final long serialVersionUID = -5480176998308828496L;
	private int arc, current_state;
	private boolean isHovered, isPressed;
	private Decoration decorations[];

	private Graphics2D g2d;

	{
		setOpaque(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		
		decorations = new Decoration[4];
		(decorations[Normal_State] = new Decoration.Bordered(
			font[0], 
			text_color[3],
			main_color[0], 
			new Color(0,0,0,0)
		)).decorate(this);
		
		decorations[Hovered_State] = new Decoration.Bordered(
			font[0],
			text_color[3],
			main_color[0],
			main_color[1]
		);
		decorations[Pressed_State] = new Decoration.Bordered(
			font[0], 
			text_color[3],
			main_color[0].darker(), 
			main_color[0].darker()
		);
		decorations[Disabled_State] = new Decoration.Bordered(
			font[0], 
			text_color[3], 
			Graphix.setAlpha(main_color[0], 128),
			new Color(0,0,0,0)
		);
		
		setArc(10);
		//setBorder(BorderFactory.createEmptyBorder(6,30,6,30));
		
		addMouseListener(this);
	}
	public Button() {
		
	}
	public Button(String str) {
		super(str);
	}
	public Button(Icon icon) {
		super(icon);
	}
	public Button(String str, Icon icon) {
		super(str, icon);
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);

		g2d.setColor(getBackground());
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

		g2d.setColor(decorations[current_state].getBorderColor());
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arc, arc);
		
		super.paint(g2d);
	}
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if(enabled) {
			decorations[Normal_State].decorate(this);
			addMouseListener(this);
		}
		else {
			decorations[Disabled_State].decorate(this);
			removeMouseListener(this);
		}
	}
	@Override
	public void setIcon(Icon defaultIcon) {
		super.setIcon(defaultIcon);
		setDisabledIcon(getIcon());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		isHovered = true;
		current_state = Hovered_State;
		decorations[Hovered_State].decorate(this);
		getRootPane().repaint();
	}
	@Override
	public void mouseExited(MouseEvent e) {
		isHovered = false;
		current_state = Normal_State;
		decorations[Normal_State].decorate(this);
		getRootPane().repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		current_state = Pressed_State;
		decorations[Pressed_State].decorate(this);
		getRootPane().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
		current_state = Hovered_State;
		decorations[Hovered_State].decorate(this);
		
		if(getRootPane() != null) {
			getRootPane().repaint();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//Overridable Content
	}
	public int getArc() {
		return arc;
	}
	public void setArc(int arc) {
		this.arc = arc;
		setBorder(BorderFactory.createEmptyBorder(arc/2, arc/2, arc/2, arc/2));
	}
	public boolean isHovered() {
		return isHovered;
	}
	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}
	public boolean isPressed() {
		return isPressed;
	}
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	public void setDecorationState(int state) {
		current_state = state;
		decorations[state].decorate(this);
		repaint();
	}
	public int getDecorationState() {
		return current_state;
	}
	public Decoration getDecoration(int button_state) {
		return decorations[button_state];
	}
	public void setDecoration(int button_state, Decoration new_decoration) {
		decorations[button_state] = new_decoration;
		repaint();
	}
	public Decoration[] getDecorations() {
		return decorations;
	}
	public void setDecorations(Decoration new_decorations[]) {
		decorations = new_decorations;
		repaint();
	}

	public static class Secondary extends Button {
		private static final long serialVersionUID = 2414752880840942749L;
		
		{
			Decoration decorations[] = new Decoration[4];
			(decorations[Normal_State] = new Decoration.Bordered(
					font[0],
					main_color[1],
					new Color(0,0,0,0),
					new Color(0,0,0,0))
			).decorate(this);
			decorations[Hovered_State] = new Decoration.Bordered(
					font[0],
					main_color[1].brighter(),
					new Color(0,0,0,0),
					new Color(0,0,0,0)
			);
			decorations[Pressed_State] = new Decoration.Bordered(
					font[0],
					main_color[0],
					new Color(0,0,0,0),
					new Color(0,0,0,0)
			);
			decorations[Disabled_State] = new Decoration.Bordered(
					font[0], 
					main_color[4],
					new Color(0,0,0,0),
					new Color(0,0,0,0)
			);
			setDecorations(decorations);
		}
		public Secondary() {
			
		}
		public Secondary(String str) {
			super(str);
		}
		public Secondary(Icon icon) {
			super(icon);
		}
		public Secondary(String str, Icon icon) {
			super(str, icon);
		}
	}
	public static class Tertiary extends Button {
		private static final long serialVersionUID = 2414752880840942749L;
		{
			Decoration decorations[] = new Decoration[4];
			(decorations[Normal_State] = new Decoration.Bordered(
					font[0], 
					text_color[1],
					main_color[2],
					main_color[3]
			)).decorate(this);
			decorations[Hovered_State] = new Decoration.Bordered(
					font[0], 
					text_color[1],
					main_color[2],
					main_color[4]
			);
			decorations[Pressed_State] = new Decoration.Bordered(
					font[0], 
					text_color[1],
					main_color[3],
					main_color[4]
			);
			decorations[Disabled_State] = new Decoration.Bordered(
					font[0], 
					text_color[2],
					main_color[2],
					main_color[3]
			);
			setDecorations(decorations);
		}
		public Tertiary() {
			
		}
		public Tertiary(String str) {
			super(str);
		}
		public Tertiary(Icon icon) {
			super(icon);
		}
		public Tertiary(String str, Icon icon) {
			super(str, icon);
		}
	}
	public static class Quaternary extends Button {
		private static final long serialVersionUID = 2414752880840942749L;
		
		{
			Decoration decorations[] = new Decoration[4];
			(decorations[Normal_State] = new Decoration.Bordered(
					font[0], 
					main_color[0],
					main_color[3],
					new Color(0,0,0,0)
			)).decorate(this);
			decorations[Hovered_State] = new Decoration.Bordered(
					font[0], 
					main_color[0],
					main_color[3],
					main_color[2]
			);
			decorations[Pressed_State] = new Decoration.Bordered(
					font[0], 
					main_color[0],
					main_color[4],
					main_color[2]
			);
			decorations[Disabled_State] = new Decoration.Bordered(
					font[0], 
					main_color[0].brighter(),
					main_color[2].brighter(),
					new Color(0,0,0,0)
			);
			setDecorations(decorations);
		}
		public Quaternary() {
			
		}
		public Quaternary(String str) {
			super(str);
		}
		public Quaternary(Icon icon) {
			super(icon);
		}
		public Quaternary(String str, Icon icon) {
			super(str, icon);
		}
	}
	public static class Notified extends Button{
		private static final long serialVersionUID = -8547132927351392240L;
		private Graphics2D g2d;
		private Color notification_color;
		private boolean isNotified;
		
		{			
			setHorizontalAlignment(JButton.LEFT);
			
			setNotificationColor(Color.red);
			setNotified(false);
		}
		public Notified() {
			
		}
		public Notified(String str) {
			super(str);
		}
		public Notified(Icon icon) {
			super(icon);
		}
		public Notified(String str, Icon icon) {
			super(str, icon);
		}
		@Override
		public void paint(Graphics g) {
			g2d = (Graphics2D)g;
			
			super.paint(g2d);
			
			if(isNotified) {
				g2d.setColor(getNotificationColor());
				g2d.fillOval(5, 5, 10, 10);
			}
		}
		@Override
		public void setArc(int arc) {
			super.setArc(arc);
			setBorder(BorderFactory.createEmptyBorder(0,getArc(),0,0));
		}
		public void setNotified(boolean isNotified) {
			this.isNotified = isNotified;
			
			if(getRootPane() != null) {
				getRootPane().repaint();
			}
		}
		public boolean isNotified() {
			return isNotified;
		}
		public Color getNotificationColor() {
			return notification_color;
		}
		public void setNotificationColor(Color notification_color) {
			this.notification_color = notification_color;
		}

	}
}
