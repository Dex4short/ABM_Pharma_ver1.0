package system.ui.panels.actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import components.Label;
import components.fields.ParagraphField;
import components.fields.TextField;
import components.panels.ActionPanel;
import components.panels.Panel;
import oop.Remarks;
import system.ui.Window;

public abstract class ActionPanelRemarks extends ActionPanel{
	private static final long serialVersionUID = 9160139962791406360L;
	private TextField title_field;
	private ParagraphField remarks_field;
	private Remarks remarks;
	
	public ActionPanelRemarks(String title) {
		super(title);
		remarks = new Remarks();
		
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		getPanelBody().add(panel);
		
		Panel timeAndDate_panel = new Panel();
		panel.add(timeAndDate_panel, BorderLayout.NORTH);
		timeAndDate_panel.setLayout(new GridLayout(3, 1));
		timeAndDate_panel.add(new Label("Time: " + remarks.getTime()));
		timeAndDate_panel.add(new Label("Date: " + remarks.getDate()));
		
		Panel title_panel = new Panel();
		timeAndDate_panel.add(title_panel);
		title_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title_panel.add(new Label("Title: "));
		
		title_field = new TextField();
		
		title_field.setColumns(20);
		title_panel.add(title_field);
		
		remarks_field = new ParagraphField();
		remarks_field.setEditable(true);
		panel.add(remarks_field, BorderLayout.CENTER);		
	}
	@Override
	public void onOk() {
		remarksOk(remarks);
		Window.getStackPanel().popPanel(this);
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public TextField getTitleField() {
		return title_field;
	}
	public void setTitleField(TextField title_field) {
		this.title_field = title_field;
	}
	public ParagraphField getRemarksField() {
		return remarks_field;
	}
	public void setRemarksField(ParagraphField remarks_field) {
		this.remarks_field = remarks_field;
	}
	public Remarks getRemarks() {
		return remarks;
	}
	public void setRemarks(Remarks remarks) {
		this.remarks = remarks;
	}
	public void remarksOk(Remarks remarks) {
		onRemarksOk(remarks);
	}
	
	public abstract void onRemarksOk(Remarks remarks);
}
