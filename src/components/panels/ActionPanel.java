package components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import components.Button;
import components.Label;

public abstract class ActionPanel extends TitledPanel{
	private static final long serialVersionUID = 7853217988420344926L;
	private PanelFooter footer;
	private Button ok, cancel;

	{
		footer = new PanelFooter();
		add(footer, BorderLayout.SOUTH);

		cancel = new Button.Secondary();
		cancel.setText("cancel");
		cancel.setPreferredSize(new Dimension(50, 30));
		cancel.setMaximumSize(getPreferredSize());
		cancel.setMinimumSize(getPreferredSize());
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});
		footer.add(cancel);
		
		ok = new Button();
		ok.setText("ok");
		ok.setPreferredSize(new Dimension(50, 30));
		ok.setMaximumSize(getPreferredSize());
		ok.setMinimumSize(getPreferredSize());
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onOk();
			}
		});
		footer.add(ok);
	}
	public ActionPanel(Label title) {
		super(title);
	}
	public ActionPanel(String title) {
		super(title);
	}
	public PanelFooter getPanelFooter() {
		return footer;
	}
	public void setPanelFooter(PanelFooter footer) {
		this.footer = footer;
	}
	public Button getButtonOk() {
		return ok;
	}
	public void setButtonOk(Button ok) {
		this.ok = ok;
	}
	public Button getButtonCancel() {
		return cancel;
	}
	public void setButtonCancel(Button cancel) {
		this.cancel = cancel;
	}
	
	public abstract void onOk();
	public abstract void onCancel();
	
	public class PanelFooter extends JPanel{
		private static final long serialVersionUID = 7536440022885055052L;
		public PanelFooter() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			setOpaque(false);
			
		}
	}
}
