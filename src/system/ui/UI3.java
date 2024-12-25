package system.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import components.panels.Panel;

public class UI3 extends Panel{
	private static final long serialVersionUID = -682175767748539059L;
	private Panel panel_top, panel_bottom;
	private UI2 ui_top, ui_bottom;

	public UI3() {
		setLayout(new GridLayout(2, 1));
		
		panel_top = new Panel();
		panel_top.setLayout(new BorderLayout());
		add(panel_top);
		
		panel_bottom = new Panel();
		panel_bottom.setLayout(new BorderLayout());
		add(panel_bottom);
		
		ui_top = new UI2();
		panel_top.add(ui_top, BorderLayout.CENTER);
		
		ui_bottom = new UI2();
		panel_bottom.add(ui_bottom, BorderLayout.CENTER);
		
	}
	@Override
	public void setVisible(boolean aFlag) {
		getUiTop().setVisible(aFlag);
		getUiBottom().setVisible(aFlag);
		super.setVisible(aFlag);
	}
	public UI2 getUiTop() {
		return ui_top;
	}
	public void setUiTop(UI2 ui_top) {
		panel_top.remove(ui_top);
		this.ui_top = ui_top;
		panel_top.add(ui_top, BorderLayout.CENTER);
	}
	public UI2 getUiBottom() {
		return ui_bottom;
	}
	public void setUiBottom(UI2 ui_bottom) {
		ui_bottom.remove(ui_bottom);
		this.ui_bottom = ui_bottom;
		ui_bottom.add(ui_bottom, BorderLayout.CENTER);
	}
}
