package components.panels;

import components.Label;
import components.fields.ParagraphField;

public abstract class DialogPanel extends ActionPanel{
	private static final long serialVersionUID = 4965706981712600503L;
	private ParagraphField txt_area;
	
	{
		txt_area = new ParagraphField();
		txt_area.setEditable(false);
		getPanelBody().add(txt_area);
	}
	public DialogPanel(Label title) {
		super(title);
	}
	public DialogPanel(String title) {
		super(title);
	}
	public ParagraphField getParagraphField() {
		return txt_area;
	}
	public String getText() {
		return txt_area.getText();
	}
	public void setParagraphField(ParagraphField txt_area) {
		this.txt_area = txt_area;
	}
	public void setText(String text) {
		txt_area.setText(text);
	}
}
