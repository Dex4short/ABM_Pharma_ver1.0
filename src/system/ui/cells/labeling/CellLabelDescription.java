package system.ui.cells.labeling;


import javax.swing.BorderFactory;

import components.fields.ParagraphField;
import components.table.Cell;

public class CellLabelDescription extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private ParagraphField description;

	public CellLabelDescription(String str) {
		super(new ParagraphField());
		setParagraphField((ParagraphField)getComponent(0));
		
		description.setArc(5);
		description.setText(str);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(false);
		
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public ParagraphField getParagraphField() {
		return description;
	}
	public void setParagraphField(ParagraphField description) {
		this.description = description;
	}
	public void setDescriptionText(String txt) {
		description.setText(txt);
	}
	public String getDescriptionText() {
		return description.getText();
	}
}
