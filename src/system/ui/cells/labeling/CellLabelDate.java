package system.ui.cells.labeling;

import javax.swing.BorderFactory;

import system.objects.Date;
import system.ui.cells.clickable.CellButtonDatePicker;

public class CellLabelDate extends CellButtonDatePicker{
	private static final long serialVersionUID = -752158914266118531L;

	public CellLabelDate(String str) {
		super(str);
		setEnabled(false);
		getButtonDatePicker().setDatePicker(null);//remove date picker
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public CellLabelDate(Date date) {
		super(date);
		setEnabled(false);
		getButtonDatePicker().setDatePicker(null);//remove date picker
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
}
