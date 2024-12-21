package system.ui.cells;


import javax.swing.BorderFactory;

import components.fields.TextField;
import components.table.Cell;

public class CellTextField extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private TextField txt_field;

	public CellTextField(String str) {
		super(new TextField(str));
		setTextField((TextField)getComponent(0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public TextField getTextField() {
		return txt_field;
	}
	public void setTextField(TextField txt_field) {
		this.txt_field = txt_field;
	}
	public String getText() {
		return txt_field.getText();
	}
	public void setText(String text) {
		txt_field.setText(text);
	}
}
