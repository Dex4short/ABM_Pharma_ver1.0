package components.list;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import components.panels.Panel;
import system._default_.Settings;

public class Item extends Panel implements MouseListener{
	private static final long serialVersionUID = -3861311087822060976L;
	private boolean isSelected;
	private boolean hovered;
	private Graphics2D g2d;

	public Item(Component component) {
		setArc(5);
		setLayout(new BorderLayout());
		setBackground(main_color[2]);
		setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		setName(component.getName());
		
		add(component, BorderLayout.CENTER);
		addMouseListener(this);
	} 
	public Item(String string) {
		setArc(5);
		setLayout(new BorderLayout());
		setBackground(main_color[2]);
		setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		setName(string);
		
		add(new Label(string), BorderLayout.CENTER);
		addMouseListener(this);
	}
	public Item() {
		setArc(5);
		setLayout(new BorderLayout());
		setBackground(main_color[2]);
		setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		
		addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		
		super.paint(g2d);
		
		if(hovered) {
			g2d.setColor(main_color[4]);
			g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getArc(), getArc());
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(isEnabled()) {
			hovered = true;
			repaint();

			if(getRootPane() != null) {
				getRootPane().repaint();
			}
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(isEnabled()) {
			hovered = false;
			repaint();

			if(getRootPane() != null) {
				getRootPane().repaint();
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//overridable function
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//overridable function
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		hovered = false;
		setSelected(true);
	}
	public void setSelected(boolean selected) {
		if(isEnabled()) {
			isSelected = selected;
			if(isSelected) {
				setBackground(main_color[3]);
			}
			else {
				setBackground(main_color[2]);
			}
			
			if(getRootPane() != null) {
				getRootPane().repaint();
			}
		}
	}
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public boolean isHovered() {
		return hovered;
	}
}
