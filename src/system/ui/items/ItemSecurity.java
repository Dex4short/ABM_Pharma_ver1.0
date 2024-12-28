package system.ui.items;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Label;
import components._misc_.Graphix;
import components.list.Item;
import res.Resource;
import system.enumerators.SecurityRole;
import system.ui.Window;
import system.ui.panels.actions.security.ActionPanelPasswordEntry;
import system.ui.panels.actions.security.ActionPanelSecuritySettings;

public abstract class ItemSecurity extends Item{
	private static final long serialVersionUID = 2949663695614910176L;

	public ItemSecurity() {
		super(new Label("Security") {
			private static final long serialVersionUID = -7118103241794633788L;
			{
				ImageIcon
				img_icon = Resource.getAsImageIcon("security_settings.png");
				img_icon = Graphix.alterImageIcon(img_icon, main_color[0], getFocusCycleRootAncestor());
				setIcon(img_icon);
			}
		});
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Window.getPopUpPanel().pop();
		requestOpenSecuritySettings();
		setSelected(false);
	}
	public void requestOpenSecuritySettings() {
		Window.getStackPanel().pushPanel(new ActionPanelPasswordEntry() {
			private static final long serialVersionUID = 4706152178350976661L;
			@Override
			public void onPasswordOk() {
				openSecuritySettings();
			}
		}, 200, 300);
	}
	
	public abstract void onChangePassword(SecurityRole role, char[] old_pass, char[] new_pass);

	private void openSecuritySettings() {
		Window.getStackPanel().pushPanel(new ActionPanelSecuritySettings() {
			private static final long serialVersionUID = -260451950434318384L;
			@Override
			public void onChangePassword(SecurityRole role, char[] old_pass, char[] new_pass) {
				ItemSecurity.this.onChangePassword(role, old_pass, new_pass);
			}
		}, 600,500);
	}
}
