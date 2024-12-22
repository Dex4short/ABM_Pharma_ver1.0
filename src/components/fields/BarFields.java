package components.fields;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.panels.Panel;

public class BarFields extends Panel{
	private static final long serialVersionUID = 8424383950064417067L;
	private Panel panel;

	public BarFields() {
		setLayout(new BorderLayout(5, 5));
		setArc(5);
		setBackground(main_color[0]);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		panel = new Panel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		add(panel, BorderLayout.CENTER);
		
	}
	public TextField addField(String str, TextField field, int width, int height) {
		field.setName(str);
		
		Panel field_panel = new Panel();
		field_panel.setLayout(new GridLayout(1, 2, 10, 10));
		field_panel.setBackground(new Color(0,0,0,0));
		field_panel.setPreferredSize(new Dimension(width, height));
		field_panel.setMaximumSize(field_panel.getPreferredSize());
		field_panel.setMinimumSize(field_panel.getPreferredSize());
		field_panel.add(create_label(str));
		field_panel.add(create_wrapping(field));
		panel.add(field_panel);
		return field;
	}
	
	private static Label create_label(String str) {
		Label label = new Label(str);
		label.setHorizontalAlignment(Label.RIGHT);
		label.setForeground(text_color[3]);
		return label;
	}
	private static Panel create_wrapping(TextField text_field) {		
		Panel panel = new Panel();
		panel.setArc(20);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
		panel.add(text_field);
		return panel;
	}
}
