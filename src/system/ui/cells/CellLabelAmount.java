package system.ui.cells;


import java.awt.BorderLayout;

import components.Label;
import components.fields.TextField;
import components.table.Cell;
import extras.Graphix;

public class CellLabelAmount extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;

	public CellLabelAmount(String str) {
		super(new Label(str));
		setLabel((Label)getComponent(0));
		getLabel().setHorizontalAlignment(TextField.RIGHT);
		
		((BorderLayout)getLayout()).setHgap(10);
		
		setArc(10);
		setBackground(Graphix.setAlpha(main_color[3], 128));
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public void setLabelText(String txt) {
		label.setText(txt);
	}
	public String getLabelText() {
		return label.getText();
	}
}
