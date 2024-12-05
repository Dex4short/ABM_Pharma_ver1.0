package system.ui.bars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import components.Label;
import components.Panel;
import components.fields.TextField;

public class BarField extends Panel{
	private static final long serialVersionUID = 8424383950064417067L;
	private TextField text_fields[];

	public BarField(String string_labels[], TextField text_fields[]) {
		setLayout(new BorderLayout(5, 5));
		setArc(5);
		setBackground(main_color[0]);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		add(panel, BorderLayout.CENTER);
		
		this.text_fields = new TextField[string_labels.length];
		for(int n=0; n<string_labels.length; n++) {
			Label totalCostAmount_label = create_label(string_labels[n]);
			panel.add(totalCostAmount_label);
			
			this.text_fields[n] = create_field(text_fields[n]);
			panel.add(create_wrapping(text_fields[n]));
		}
	}
	public TextField[] getField() {
		return text_fields;
	}
	public void setField(TextField[] text_fields) {
		this.text_fields = text_fields;
	}
	public TextField getField(int n) {
		return text_fields[n];
	}
	public void setField(TextField text_field, int n) {
		this.text_fields[n] = text_field;
	}
	
	public static Label create_label(String str) {
		Label label = new Label(str);
		label.setPreferredSize(new Dimension(150, 10));
		label.setMaximumSize(label.getPreferredSize());
		label.setMinimumSize(label.getPreferredSize());
		label.setHorizontalAlignment(Label.RIGHT);
		label.setForeground(text_color[3]);
		return label;
	}
	public static TextField create_field(TextField text_field) {
		text_field.setColumns(10);
		text_field.setHorizontalAlignment(TextField.LEFT);
		text_field.setEditable(false);
		return text_field;
	}
	public static Panel create_wrapping(JComponent component) {
		Panel panel = new Panel();
		panel.setArc(20);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		panel.add(component);
		return panel;
	}
}
