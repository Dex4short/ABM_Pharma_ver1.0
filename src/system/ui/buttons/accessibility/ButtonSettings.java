package system.ui.buttons.accessibility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import components._misc_.Graphix;
import res.Resource;
import system.ui.Window;
import system.ui.panels.popups.PopUpSettings;

public class ButtonSettings extends Button.Tertiary implements ActionListener{
	private static final long serialVersionUID = -255131618867894983L;
	private PopUpSettings popup_settings;

	public ButtonSettings() {
		setArc(30);
		setBorder(BorderFactory.createEmptyBorder(7,7,7,7));
		
		ImageIcon
		img_ico = Resource.getAsImageIcon("settings.png");
		img_ico = Graphix.alterImageIcon(img_ico, main_color[0], getFocusCycleRootAncestor());
		setIcon(img_ico);
		
		popup_settings = new PopUpSettings();
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getPopUpPanel().popUp(popup_settings);
	}
	public PopUpSettings getPopupSettings() {
		return popup_settings;
	}
}
