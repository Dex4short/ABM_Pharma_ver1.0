package system.ui.cells;


import javax.swing.BorderFactory;

import components.Button;
import components.table.Cell;
import oop.Date;
import system.ui.buttons.ButtonDatePicker;

public class CellButtonDatePicker extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private ButtonDatePicker btn_dated;

	public CellButtonDatePicker(String str) {
		super(new ButtonDatePicker(str));
		
		btn_dated = (ButtonDatePicker)getComponent(0);
		btn_dated.setArc(20);
		
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
	}
	public CellButtonDatePicker(Date date) {
		super(new ButtonDatePicker(date));
		
		btn_dated = (ButtonDatePicker)getComponent(0);
		btn_dated.setArc(20);
		
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
	}
	public CellButtonDatePicker() {
		super(new ButtonDatePicker(new Date()));
		
		btn_dated = (ButtonDatePicker)getComponent(0);
		btn_dated.setArc(20);
		
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
	}
	public Button.Notified getButtonDated() {
		return btn_dated;
	}
	public void setButtonDateSelector(ButtonDatePicker btn_dated) {
		this.btn_dated = btn_dated;
	}
	public void setDate(Date date) {
		btn_dated.setDate(date);
	}
	public Date getDate() {
		return btn_dated.getDate();
	}
}
