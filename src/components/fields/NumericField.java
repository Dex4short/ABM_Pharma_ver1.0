package components.fields;

import java.math.BigInteger;

public class NumericField extends StrictTextField{
	private static final long serialVersionUID = 8046081737338819617L;

	public NumericField(String txt) {
		super(txt);
		includeNumbers(true);
		includeLetters(false);

		setHorizontalAlignment(RIGHT);
	}
	public NumericField(BigInteger decimal) {
		super(decimal.toString());
		includeNumbers(true);
		includeLetters(false);
	}
	public BigInteger getBigNumber() {		
		return new BigInteger(getText());
	}
	public void setBigNumber(BigInteger number) {
		setText(number.toString());
	}
	public int getNumber() {	
		return Integer.parseInt(getText());
	}
	public void setNumber(int number) {
		setText(number + "");
	}
}