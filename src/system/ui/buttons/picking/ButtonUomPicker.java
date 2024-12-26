package system.ui.buttons.picking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Button;
import oop.Uom;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelUOMPicker;

public class ButtonUomPicker extends Button implements ActionListener{
	private static final long serialVersionUID = -4419033569562646499L;
	private ActionPanelUOMPicker uom_picker;

	public ButtonUomPicker(String str) {
		super(str);
		uom_picker = new ActionPanelUOMPicker() {
			private static final long serialVersionUID = -6441141868908668844L;
			@Override
			public void onUomOk(Uom selectedUom) {
				setUom(selectedUom);
				Window.getStackPanel().popPanel();
			}
			@Override
			public void onUomCancel() {
				Window.getStackPanel().popPanel();
			}
		};
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(uom_picker, 400, 300);
	}
	public ActionPanelUOMPicker getUomPicker() {
		return uom_picker;
	}
	public void setUomPicker(ActionPanelUOMPicker uom_picker) {
		this.uom_picker = uom_picker;
	}
	public Uom getUom() {
		return getUomPicker().getSelectedUom();
	}
	public void setUom(Uom uom) {
		getUomPicker().setSelectedUom(uom);
	}
}
