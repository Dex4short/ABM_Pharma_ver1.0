package components.fields;

import java.math.BigDecimal;

import system.objects.Percentage;

public class PercentageField extends NumericField{
	private static final long serialVersionUID = 8046081737338819617L;

	public PercentageField(String txt) {
		super(txt);
		includeCharacter('%');
	}
	public PercentageField(Percentage percentage) {
		super(percentage.toString());
		includeCharacter('%');
	}
	public Percentage getPercent() {		
		return new Percentage(getText());
	}
	public void setPercent(Percentage percentage) {
		setText(percentage.toString());
	}
	public BigDecimal getPercentDecimal() {
		return new BigDecimal(getText().replace("%", "")).divide(new BigDecimal("100"));
	}
	public void setPercentDecimal(BigDecimal percent_decimal) {
		setText(percent_decimal.toString());
	}
}