package components.fields;

import java.math.BigDecimal;

import oop.Decimal;

public class DecimalField extends StrictTextField{
	private static final long serialVersionUID = 8046081737338819617L;

	public DecimalField(String txt) {
		super(txt);
		includeNumbers(true);
		includeLetters(false);
		includeCharacter('.');
		
		setHorizontalAlignment(RIGHT);
	}
	public DecimalField(Decimal decimal) {
		super(decimal.toString());
		includeNumbers(true);
		includeLetters(false);
		includeCharacter('.');
	}
	public Decimal getDecimal() {		
		return new Decimal(new BigDecimal(getText()));
	}
	public void setDecimal(Decimal decimal) {
		setText(decimal.toString());
	}
}