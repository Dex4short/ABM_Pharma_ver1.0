package components.panels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import extras.Graphix;


public class StackPanel extends JPanel{
	private static final long serialVersionUID = 4962917535590190005L;

	public StackPanel() {
		setLayout(null);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				refreshStackPanel();
			}
		});
	}
	public void pushPanel(JPanel panel) {
		add(panel, 0);
		refreshStackPanel();
	}
	public void pushPanel(JPanel panel, int w, int h) {
		PanelLayer panel_layer = new PanelLayer(panel, w, h);
		pushPanel(panel_layer);
	}
	public void pushPanel(JPanel panel, float horizontal, float vertical) {
		PanelLayer panel_layer = new PanelLayer(panel, horizontal, vertical);
		pushPanel(panel_layer);
	}
	public void pushPanel(JPanel panel, float top, float left, float bottom, float right) {
		PanelLayer panel_layer = new PanelLayer(panel, top, left, bottom, right);
		pushPanel(panel_layer);
	}
	public void pushPanel(JPanel panel, int left, int right, int height) {
		PanelLayer panel_layer = new PanelLayer(panel, left, right, height);
		pushPanel(panel_layer);
	}
	public void popPanel() {
		remove(0);
		refreshStackPanel();
	}

	private int p,count;
	private void refreshStackPanel() {
		count = getComponentCount();
		for(p=0; p<count; p++) {
			getComponent(p).setBounds(0, 0, getWidth(), getHeight());
		}
		
		if(getRootPane() != null) {
			getRootPane().revalidate();
			getRootPane().repaint();
		}
	}
	
	public static class PanelLayer extends JPanel{
		private static final long serialVersionUID = -7078947632773482648L;
		private int w,h;
		
		{
			setLayout(null);
			setOpaque(false);
			addMouseListener(new MouseAdapter() {});
		}
		public PanelLayer(JPanel panel, int w, int h) {
			add(panel);
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					resize(panel, w, h);
				}
			});
			resize(panel, w, h);
		}
		public PanelLayer(JPanel panel, float top, float left, float bottom, float right) {
			add(panel);		
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					resize(panel, top, left, bottom, right);
				}
			});
			resize(panel, top, left, bottom, right);
		}
		public PanelLayer(JPanel panel, float horizontal, float vertical) {
			add(panel);	
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					resize(panel, horizontal, vertical);
				}
			});
			resize(panel, horizontal, vertical);
		}
		public PanelLayer(JPanel panel, int left, int right, int height) {
			add(panel);	
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					resize(panel, left, right, height);
				}
			});
			resize(panel, left, right, height);
		}
		private Graphics g2d;
		@Override
		public void paint(Graphics g) {
			g2d = (Graphics2D)g;
			g2d.setColor(Graphix.Shadow.shadow_color);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			super.paint(g2d);
		}

		private void resize(JPanel panel, int w, int h) {
			panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
		}
		private void resize(JPanel panel, float top, float left, float bottom, float right) {
			panel.setBounds(
					Math.round(getWidth() * top),
					Math.round(getHeight() * left),
					Math.round(getWidth() * bottom) - panel.getX(),
					Math.round(getHeight() * right) - panel.getY()
			);
		}
		private void resize(JPanel panel, float horizontal, float vertical) {
			w = Math.round(getWidth() * horizontal);
			h = Math.round(getHeight() * vertical);
			panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
		}
		private void resize(JPanel panel, int left, int right, int height) {
			panel.setBounds(left, (getHeight()/2) - (height/2), getWidth() - (left + right), height);
		}
	}
}