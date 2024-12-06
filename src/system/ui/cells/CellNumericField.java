package system.ui.cells;


import java.math.BigInteger;

import components.fields.NumericField;
import components.table.Cell;

public class CellNumericField extends Cell{
	private static final long serialVersionUID = 1103410566052126699L;
	private NumericField numeric_field;

	public CellNumericField(String str) {
		super(new NumericField(str));
		
		numeric_field = (NumericField)getComponent(0);
	}
	public NumericField getNumericField() {
		return numeric_field;
	}
	public void setNumericField(NumericField numeric_field) {
		this.numeric_field = numeric_field;
	}
	public BigInteger getNumber() {
		return numeric_field.getBigNumber();
	}
	public void setNumber(BigInteger number) {
		numeric_field.setBigNumber(number);
	}
}
