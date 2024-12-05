package system.ui.cells;

import oop.Date;

public class CellLabelDate extends CellButtonDatePicker{
	private static final long serialVersionUID = -752158914266118531L;

	public CellLabelDate(String str) {
		super(str);
		setEnabled(false);
		getButtonDated().setDatePicker(null);//remove date picker
	}
	public CellLabelDate(Date date) {
		super(date);
		setEnabled(false);
		getButtonDated().setDatePicker(null);//remove date picker
	}
}
