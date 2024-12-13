package system.ui.cells;

import javax.swing.BorderFactory;

import components.fields.PercentageField;
import components.table.Cell;
import oop.Percentage;

public class CellPercentageField extends Cell{
	private static final long serialVersionUID = 2493091177359096476L;
	private PercentageField percentage_field;

	public CellPercentageField(String str) {
		super(new PercentageField(str));
		percentage_field = (PercentageField)getComponent(0);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public CellPercentageField(Percentage discount) {
		super(new PercentageField(discount));
		percentage_field = (PercentageField)getComponent(0);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public PercentageField getPercentageField() {
		return percentage_field;
	}
	public void setPercentageField(PercentageField percentage_field) {
		this.percentage_field = percentage_field;
	}
	public Percentage getPercentage() {
		return percentage_field.getPercent();
	}
	public void setPercentage(Percentage percentage) {
		percentage_field.setPercent(percentage);
	}
}
