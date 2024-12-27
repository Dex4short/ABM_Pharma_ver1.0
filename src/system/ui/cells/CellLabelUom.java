package system.ui.cells;


import javax.swing.BorderFactory;

import components.Label;
import components.table.Cell;
import system.objects.Uom;

public class CellLabelUom extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;
	private Uom uom;

	public CellLabelUom(Uom uom) {
		super(new Label(uom.getUnitType().name()));
		setLabel((Label)getComponent(0));
		setUom(uom);
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
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
}
