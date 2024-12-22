package system.ui.cells;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Button;
import components.table.Cell;

public class CellButton extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Button button;

	public CellButton() {
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	}
	public CellButton(String str) {
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setButton(new Button(str));
	}
	public CellButton(Button btn) {
		super(btn);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setButton(btn);
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		removeAll();
		add(button, BorderLayout.CENTER);
		this.button = button;
	}
	public void setButtonText(String txt) {
		button.setText(txt);
	}
	public String getButtonText() {
		return button.getText();
	}
	
}
