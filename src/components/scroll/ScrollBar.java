package components.scroll;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import components.panels.Panel;
import oop.implementations.Theme;

public abstract class ScrollBar extends Panel implements Theme, ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener{
	private static final long serialVersionUID = 1370122204785526953L;
	public static final int HORIZONTAL=0, VERTICAL=1;
	private int orientation, p[], d[], m_drag, view_length, length,bar_size, m_press, thickness, speed;
	private float percent,ratio,scroll_amount;
	private boolean auto_hide;
	private Panel scroller_bar;

	public ScrollBar(int orientation) {
		setLayout(null);
		setArc(10);
		setBackground(main_color[3]);
		setOrientation(orientation);
		setThickness(10);
		setScrollSpeed(3);
		setAutoHide(true);
		
		p = new int[2];
		d = new int[2];
		
		scroller_bar = new Panel();
		scroller_bar.setArc(10);
		scroller_bar.setBackground(main_color[0]);
		add(scroller_bar);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addComponentListener(this);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(!scroller_bar.getBounds().contains(e.getPoint())) return;
		if(view_length < length) return;
		
		p[HORIZONTAL] = e.getX();
		p[VERTICAL] = e.getY();

		m_press = p[orientation] - m_drag;
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	private int s_x,s_y;
	@Override
	public void mouseDragged(MouseEvent e) {
		if(orientation==HORIZONTAL && e.getX()<0 && e.getX()>length) return;
		if(orientation==VERTICAL   && e.getY()<0 && e.getY()>length) return;
		if(view_length < length) return;
		
		d[HORIZONTAL] = e.getX();
		d[VERTICAL]   = e.getY();
		
		m_drag = d[orientation] - m_press;
		s_x = m_drag * (1-orientation);
		s_y = m_drag *    orientation;
		
		scroll_amount = scroll_condition(-(float)(s_x + s_y) * ratio);
		
		scroller_bar.setLocation(s_x, s_y);
		scroll(Math.round(scroll_amount));
		
		getRootPane().revalidate();
		getRootPane().repaint();
	}
	private int rotation;
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(view_length < length) return;
		
		rotation = e.getWheelRotation() * speed;
		s_x += rotation * (1-orientation);
		s_y += rotation *    orientation;
		
		m_press += rotation;
		m_drag += rotation;
		
		scroll_amount = scroll_condition(-(float)(s_x + s_y) * ratio);
		
		scroller_bar.setLocation(s_x, s_y);
		scroll(Math.round(scroll_amount));
		
		getRootPane().revalidate();
		getRootPane().repaint();
	}
	@Override
	public void componentResized(ComponentEvent e) {
		scroll_resize();
	}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public JPanel getScrollerBar() {
		return scroller_bar;
	}
	public void setScrollerBar(Panel scroller_bar) {
		this.scroller_bar = scroller_bar;
	}
	public void setScrollerBarLocation(int x, int y) {
		s_x = x;
		s_y = y;
		scroller_bar.setLocation(x, y);
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public void setAutoHide(boolean auto_hide) {
		this.auto_hide = auto_hide;
	}
	public boolean isAutoHide() {
		return auto_hide;
	}
	public int getScrollSpeed() {
		return speed;
	}
	public void setScrollSpeed(int speed) {
		this.speed = speed;
	}
	public void update() {
		scroll_resize();
	}
	public void scroll(int scroll_amount) {
		onScroll(scroll_amount);
	}
	public void scrolls(float scroll_amount) {
		onScroll(Math.round(scroll_amount));
	}
	
	public abstract int getViewLength();
	public abstract void onScroll(int scroll_amount);
	
	private void scroll_resize() {
		view_length = getViewLength();			
		length = (orientation==HORIZONTAL) ? getWidth() : getHeight();
		percent = (float)length/(float)view_length;

		int dimension[] = {0,0};
		dimension[orientation] = thickness;

		int size[] = {thickness, thickness};
		
		if(view_length < length) {
			if(auto_hide) {
				setPreferredSize(new Dimension(0, 0));
			}
			else {
				setPreferredSize(new Dimension(dimension[1], dimension[0]));
				
				size[orientation] = length;
				scroller_bar.setSize(size[HORIZONTAL], size[VERTICAL]);
				scroller_bar.setLocation(0,0);
				scroll_amount = 0;
				onScroll(Math.round(scroll_amount));
			}
			setMaximumSize(getPreferredSize());
		}
		else {
			setPreferredSize(new Dimension(dimension[1], dimension[0]));

			size[orientation] = Math.round((float)length * percent);
			scroller_bar.setSize(size[HORIZONTAL], size[VERTICAL]);

			bar_size = size[orientation];
			ratio = (float)(view_length)/(float)(length);
		}
		
		setMinimumSize(getPreferredSize());

		if(getRootPane() != null) {
			getRootPane().revalidate();
			getRootPane().repaint();
		}
	}
	private float scroll_condition(float scroll_amount) {
		if(scroll_amount > 0) {
			scroll_amount = 0;
			s_x = 0;
			s_y = 0;
		}
		else if(scroll_amount < -(view_length-length)) {
			scroll_amount = -(view_length-length);
			s_x = (1-orientation) * (length-bar_size);
			s_y =    orientation  * (length-bar_size);
		}
		return scroll_amount;
	}
}
