package system.ui.cells;


import javax.swing.BorderFactory;

import components.table.Cell;
import oop.Uom;
import system.ui.buttons.ButtonUomPicker;

public class CellButtonUomPicker extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private ButtonUomPicker btn;

	public CellButtonUomPicker(String str) {
		super(new ButtonUomPicker(str));

		setButtonUomPicker((ButtonUomPicker)getComponent(0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public CellButtonUomPicker(Uom uom) {
		super(new ButtonUomPicker(uom.getUnitType().name()));

		setButtonUomPicker((ButtonUomPicker)getComponent(0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public ButtonUomPicker getButtonUomPicker() {
		return btn;
	}
	public void setButtonUomPicker(ButtonUomPicker btn) {
		this.btn = btn;
	}
}
