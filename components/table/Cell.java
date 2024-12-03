package components.table;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;

import components.Panel;
import oop.interfaces.Theme;

public class Cell extends Panel implements Theme{
	private static final long serialVersionUID = -1471486271615938005L;

	public Cell(JComponent jcomponent) {
		setArc(0);
		setLayout(new BorderLayout());
		setBackground(new Color(0,0,0,0));
		
		jcomponent.setFont(font[0]);
		add(jcomponent, BorderLayout.CENTER);
	}
	public Cell() {
		setOpaque(false);
		setLayout(new BorderLayout());
		
		add(new Panel(), BorderLayout.CENTER);
	}
}
