package system.ui.panels.actions.remarking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import components.Label;
import components.fields.ParagraphField;
import components.fields.TextField;
import components.panels.ActionPanel;
import components.panels.Panel;
import system.objects.Date;
import system.objects.Time;
import system.ui.Window;

public abstract class ActionPanelRemarks extends ActionPanel{
	private static final long serialVersionUID = 9160139962791406360L;
	private TextField title_field;
	private ParagraphField remarks_field;
	
	public ActionPanelRemarks(String title) {
		super(title);
		
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		getPanelBody().add(panel);
		
		Panel timeAndDate_panel = new Panel();
		panel.add(timeAndDate_panel, BorderLayout.NORTH);
		timeAndDate_panel.setLayout(new GridLayout(3, 1));
		timeAndDate_panel.add(new Label("Time: " + new Time()));
		timeAndDate_panel.add(new Label("Date: " + new Date()));
		
		Panel title_panel = new Panel();
		timeAndDate_panel.add(title_panel);
		title_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title_panel.add(new Label("Title: "));
				
		title_panel.add(title_field = new TextField());
		title_field.setColumns(20);
		
		panel.add(remarks_field = new ParagraphField(), BorderLayout.CENTER);	
		remarks_field.setEditable(true);	
	}
	@Override
	public void onOk() {
		remarksOk(new Time(), new Date(), title_field.getText() + "\n" + remarks_field.getText());
		Window.getStackPanel().popPanel(this);
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void remarksOk(Time time, Date date, String details) {
		onRemarksOk(time, date, details);
	}
	
	public abstract void onRemarksOk(Time time, Date date, String details);
}
