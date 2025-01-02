package system.ui.cells.clickable;


import javax.swing.BorderFactory;

import system.objects.Date;
import system.ui.buttons.picking.ButtonDatePicker;

public class CellButtonDatePicker extends CellButton{
	private static final long serialVersionUID = -752158914266118531L;
	private ButtonDatePicker btn_date_picker;

	public CellButtonDatePicker(String str) {
		super(new ButtonDatePicker(str));
		
		btn_date_picker = (ButtonDatePicker)getComponent(0);
		btn_date_picker.setArc(20);
		setBorder(BorderFactory.createEmptyBorder(4,0,4,5));
	}
	public CellButtonDatePicker(Date date) {
		super(new ButtonDatePicker(date));
		
		btn_date_picker = (ButtonDatePicker)getComponent(0);
		btn_date_picker.setArc(20);
		setBorder(BorderFactory.createEmptyBorder(4,0,4,5));
	}
	public CellButtonDatePicker() {
		super(new ButtonDatePicker(new Date()));
		
		btn_date_picker = (ButtonDatePicker)getComponent(0);
		btn_date_picker.setArc(20);
		setBorder(BorderFactory.createEmptyBorder(4,0,4,5));
	}
	public ButtonDatePicker getButtonDatePicker() {
		return btn_date_picker;
	}
	public void setButtonDatePicker(ButtonDatePicker btn_date_picker) {
		this.btn_date_picker = btn_date_picker;
	}
	public void setDate(Date date) {
		btn_date_picker.setDate(date);
	}
	public Date getDate() {
		return btn_date_picker.getDate();
	}
}
