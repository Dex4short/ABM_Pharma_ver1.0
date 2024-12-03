package system.ui.cells;


import javax.swing.BorderFactory;

import components.Label;
import components.table.Cell;
import oop.Quantity;

public class CellLabelQuantity extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;
	private Quantity quantity;

	public CellLabelQuantity(Quantity quantity) {
		super(new Label(quantity.getQuantity() + "/" + quantity.getSize()));
		setLabel((Label)getComponent(0));
		setQuantity(quantity);
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
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
	public Quantity getQuantity() {
		return quantity;
	}
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
}
