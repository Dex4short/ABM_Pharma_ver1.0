package system.ui.cells;


import javax.swing.BorderFactory;

import system.objects.Uom;
import system.ui.buttons.picking.ButtonUomPicker;

public class CellButtonUomPicker extends CellButton{
	private static final long serialVersionUID = -752158914266118531L;

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
		return (ButtonUomPicker)getButton();
	}
	public void setButtonUomPicker(ButtonUomPicker btn) {
		setButton(btn);
	}
}
