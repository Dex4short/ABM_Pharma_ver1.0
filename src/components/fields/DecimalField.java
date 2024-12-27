package components.fields;

import system.objects.Decimal;

public class DecimalField extends StrictTextField{
	private static final long serialVersionUID = 8046081737338819617L;

	public DecimalField(String txt) {
		super(txt);
		includeNumbers(true);
		includeLetters(false);
		includeCharacter('.');
		includeCharacter(',');
		
		setHorizontalAlignment(RIGHT);
	}
	public DecimalField(Decimal decimal) {
		super(decimal.toString());
		includeNumbers(true);
		includeLetters(false);
		includeCharacter('.');
		includeCharacter(',');
	}
	public Decimal getDecimal() {
		return new Decimal(getText());
	}
	public void setDecimal(Decimal decimal) {
		setText(decimal.toString());
	}
}