package system.ui.cells;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Label;
import components._misc_.Graphix;
import components.fields.TextField;
import components.table.Cell;
import oop.Decimal;

public class CellLabelAmount extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;

	public CellLabelAmount(String str) {
		super(new Label(str));
		initialize();
	}
	public CellLabelAmount(Decimal unitAmount) {
		super(new Label(unitAmount.toString()));
		initialize();
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

	private void initialize() {
		setLabel((Label)getComponent(0));
		getLabel().setHorizontalAlignment(TextField.RIGHT);
		
		((BorderLayout)getLayout()).setHgap(10);
		
		setArc(10);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		setBackground(Graphix.setAlpha(main_color[3], 128));
	}
}
