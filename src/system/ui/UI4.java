package system.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import components.fields.ParagraphField;
import oop.interfaces.Theme;

public class UI4 extends UI1 implements Theme{
	private static final long serialVersionUID = 5294758605465387431L;
	private ParagraphField txt_area;

	public UI4() {
		txt_area = new ParagraphField();
		txt_area.setPreferredSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMinimumSize(new Dimension(Integer.MIN_VALUE,100));
		txt_area.setText("info: 1\ninfo: 2\ninfo: 3\ninfo: 4");
		txt_area.setEditable(false);
		add(txt_area, BorderLayout.SOUTH);
	}
	public ParagraphField getTxtArea() {
		return txt_area;
	}
	public void setTxtArea(ParagraphField txt_area) {
		remove(getTxtArea());
		this.txt_area = txt_area;
		txt_area.setPreferredSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMaximumSize(new Dimension(Integer.MAX_VALUE,100));
		txt_area.setMinimumSize(new Dimension(Integer.MIN_VALUE,100));
		txt_area.setEditable(false);
		add(txt_area, BorderLayout.SOUTH);
	}
}
