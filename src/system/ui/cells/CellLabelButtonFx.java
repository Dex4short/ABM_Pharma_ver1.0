package system.ui.cells;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import components.Button;
import res.Resource;

public class CellLabelButtonFx extends CellLabelAmount{
	private static final long serialVersionUID = -6277392169855422374L;
	private Button btn;

	public CellLabelButtonFx(String str) {
		super(str);
		
		btn = new Button(Resource.getAsImageIcon("function.png"));
		add(btn, BorderLayout.EAST);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public Button getButtonFx() {
		return btn;
	}
	public void setButtonFx(Button btn) {
		this.btn = btn;
	}
}
