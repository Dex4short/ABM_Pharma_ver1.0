package components.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import components.panels.Panel;
import oop.implementations.Theme;

public class TabPane extends Panel implements Theme{
	private static final long serialVersionUID = 2593805313851461876L;
	private TabHead tab_head;
	private TabBody tab_body;

	{
		setLayout(new BorderLayout());
		setBackground(new Color(0,0,0,0));
		
		tab_head = new TabHead();
		add(tab_head, BorderLayout.NORTH);
		
		tab_body = new TabBody();
		add(tab_body, BorderLayout.CENTER);
	}
	public Tab addTab(Tab tab) {
		tab.addMouseListener(new TabMouseAdapter());
		tab.addComponentListener(new TabComponentAdapter());
		tab_head.add(tab);
		setSelectedTab(tab);
		return tab;
	}
	public void removeTab(int index) {
		Tab tab = getTab(index);
		MouseListener mouse_listeners[] = tab.getMouseListeners();
		ComponentListener component_listeners[] = tab.getComponentListeners();
		tab.removeMouseListener(mouse_listeners[mouse_listeners.length - 1]);
		tab.removeComponentListener(component_listeners[component_listeners.length - 1]);
		tab_head.remove(tab);
		setSelectedTab(getTabCount()-1);
	}
	public Tab getTab(int index) {
		return (Tab)tab_head.getComponent(index);
	}
	public void setSelectedTab(int index) {
		setSelectedTab((Tab)tab_head.getComponent(index));
	}
	public void setSelectedTab(Tab tab) {
		untoggleAll();
		if(tab_head.getComponents().length > 0) {
			tab.toggle();
		}
		tab_body.removeAll();
		tab_body.add(tab.getTabContent()).setVisible(true);
		tab_body.revalidate();
		tab_body.repaint();
	}
	public int getTabCount() {
		return tab_head.getComponentCount();
	}

	private void untoggleAll() {
		Tab tab;
		for(Component component: tab_head.getComponents()) {
			tab = (Tab)component;
			tab.getTabContent().setVisible(false);
			if(tab.isToggled()) {
				tab.toggle();
			}
		}
	}
	
	private class TabHead extends Panel implements ComponentListener{
		private static final long serialVersionUID = 1L;

		public TabHead() {
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			setBackground(new Color(0,0,0,0));
			addComponentListener(this);
		}
		@Override
		public void componentResized(ComponentEvent e) {		
			int tab_count = tab_head.getComponentCount();
			Tab tab;
			for(int t=0; t<tab_count; t++) {
				tab = (Tab)tab_head.getComponent(t);
				if(t == 0) {
					tab.setCorner(1, false);
				}
				else if(t == (tab_count-1)) {
					tab.setCorner(0, false);
					tab.setCorner(1, true);
				}
				else {
					tab.setCorner(0, false);
					tab.setCorner(1, false);
				}
			}
			tab_head.revalidate();
			tab_head.repaint();
		}
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentShown(ComponentEvent e) {}
		@Override
		public void componentHidden(ComponentEvent e) {}
	}
	private class TabBody extends Panel{
		private static final long serialVersionUID = 1L;

		public TabBody() {
			setArc(10);
			setCorner(0, false);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
	}
	private class TabComponentAdapter extends ComponentAdapter{
		@Override
		public void componentMoved(ComponentEvent e) {
			/*
			Tab tab;
			for(Component component: header.getComponents()) {
				tab = (Tab)component;
				System.out.println(tab.getLabel().getText() + " " + ((tab.getY()==0) ? "visible" : "hidden"));
			}
			*/
		}
	}
	private class TabMouseAdapter extends MouseAdapter{ 
		@Override
		public void mouseClicked(MouseEvent e) {
			setSelectedTab((Tab)e.getSource());
		}
	}
}
