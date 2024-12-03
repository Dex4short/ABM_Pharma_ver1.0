package system.ui.cells;


import javax.swing.BorderFactory;

import components.fields.DecimalField;
import components.table.Cell;
import oop.Decimal;

public class CellDecimalField extends Cell{
	private static final long serialVersionUID = 1103410566052126699L;
	private DecimalField decimal_field;

	public CellDecimalField(String str) {
		super(new DecimalField(str));
		decimal_field = (DecimalField)getComponent(0);
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
	}
	public DecimalField getDecimalField() {
		return decimal_field;
	}
	public void setDecimalField(DecimalField decimal_field) {
		this.decimal_field = decimal_field;
	}
	public void setDecimal(Decimal decimal) {
		decimal_field.setText(decimal.toString());
	}
	public Decimal getDecimal() {
		return decimal_field.getDecimal();
	}
}
