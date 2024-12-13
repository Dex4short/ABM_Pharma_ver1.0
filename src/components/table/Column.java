package components.table;

import java.awt.BorderLayout;


import components.Label;
import components.panels.Panel;
import oop.interfaces.Theme;

public class Column extends Panel implements Theme{
	private static final long serialVersionUID = 4355410927257105403L;

	public Column(Label label) {
		setLayout(new BorderLayout());
		
		label.setFont(font[0]);
		label.setForeground(text_color[1]);
		label.setHorizontalAlignment(Label.LEFT);
		add(label, BorderLayout.CENTER);
	}
	public Column(String str) {
		setLayout(new BorderLayout());
		
		Label label = new Label(str);
		label.setFont(font[0]);
		label.setForeground(text_color[1]);
		label.setHorizontalAlignment(Label.LEFT);
		add(label, BorderLayout.CENTER);
	}
}
