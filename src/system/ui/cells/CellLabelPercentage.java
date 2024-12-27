package system.ui.cells;


import javax.swing.BorderFactory;

import components.Label;
import components.table.Cell;
import system.objects.Percentage;

public class CellLabelPercentage extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;
	private Percentage percentage;

	public CellLabelPercentage(Percentage percentage) {
		super(new Label(percentage.toString()));
		
		setLabel((Label)getComponent(0));
		setPercentage(percentage);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public void setLabelText(String txt) {
		label.setText(txt);
	}
	public void getLabelText() {
		label.getText();
	}
	public Percentage getPercentage() {
		return percentage;
	}
	public void setPercentage(Percentage percentage) {
		this.percentage = percentage;
	}
}
