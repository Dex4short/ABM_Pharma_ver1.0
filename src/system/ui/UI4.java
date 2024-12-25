package system.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import components.fields.ParagraphField;
import oop.implementations.Theme;

public class UI4 extends UI1 implements Theme{
	private static final long serialVersionUID = 5294758605465387431L;
	private ParagraphField paragraph_field;

	public UI4() {
		paragraph_field = new ParagraphField();
		paragraph_field.setPreferredSize(new Dimension(Integer.MAX_VALUE,100));
		paragraph_field.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		paragraph_field.setMinimumSize(new Dimension(Integer.MIN_VALUE,100));
		paragraph_field.setText("info: 1\ninfo: 2\ninfo: 3\ninfo: 4");
		paragraph_field.setEditable(false);
		add(paragraph_field, BorderLayout.SOUTH);
	}
	public ParagraphField getParagraphField() {
		return paragraph_field;
	}
	public void setParagraphField(ParagraphField txt_area) {
		remove(getParagraphField());
		this.paragraph_field = txt_area;
		txt_area.setPreferredSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMinimumSize(new Dimension(Integer.MIN_VALUE,100));
		txt_area.setEditable(false);
		add(txt_area, BorderLayout.SOUTH);
	}
}
