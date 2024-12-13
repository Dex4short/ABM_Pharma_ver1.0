package system.ui.cells;


import javax.swing.BorderFactory;

import components.Label;
import components.table.Cell;
import oop.Decimal;

public class CellLabelDecimal extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;
	private Decimal decimal;

	public CellLabelDecimal(Decimal decimal) {
		super(new Label(decimal.toString()));
		
		setLabel((Label)getComponent(0));
		setDecimal(decimal);
		
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
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
	public void getLabelText() {
		label.getText();
	}
	public Decimal getDecimal() {
		return decimal;
	}
	public void setDecimal(Decimal decimal) {
		this.decimal = decimal;
	}
}
