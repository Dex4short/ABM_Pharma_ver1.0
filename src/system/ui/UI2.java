package system.ui;

import java.awt.BorderLayout;

import components.fields.BarFields;
import components.fields.TextField;

public class UI2 extends UI1{
	private static final long serialVersionUID = -322640036003537607L;
	private BarFields bar_fields;

	public UI2() {
		bar_fields = new BarFields();
		bar_fields.addField("Field1:", new TextField(), 200, 20);
		bar_fields.addField("Field2:", new TextField(), 200, 20);
		add(bar_fields, BorderLayout.SOUTH);
	}
	public BarFields getBarFields() {
		return bar_fields;
	}
	public void setBarFields(BarFields bar_fields) {
		remove(getBarFields());
		this.bar_fields = bar_fields;
		add(bar_fields, BorderLayout.SOUTH);
	}
}
