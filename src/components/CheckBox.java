package components;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import oop.interfaces.Theme;
import res.Resource;

public class CheckBox extends JCheckBox implements Theme{
	private static final long serialVersionUID = 7088141605327845374L;

	public CheckBox() {
		setBackground(main_color[2]);
		
		setIcon(new ImageIcon(Resource.get("check_false.png")));
		setPressedIcon(new ImageIcon(Resource.get("check_pressed.png")));
		setSelectedIcon(new ImageIcon(Resource.get("check_true.png")));
		
		setRolloverIcon(new ImageIcon(Resource.get("check_false_highlight.png")));
		setRolloverSelectedIcon(new ImageIcon(Resource.get("check_true_highlight.png")));
		
		setDisabledIcon(new ImageIcon(Resource.get("check_false_disabled.png")));
		setDisabledSelectedIcon(new ImageIcon(Resource.get("check_true_disabled.png")));
	}

}
