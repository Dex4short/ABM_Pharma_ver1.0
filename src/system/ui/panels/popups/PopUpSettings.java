package system.ui.panels.popups;

import java.awt.BorderLayout;
import java.awt.Dimension;

import components.list.ListPane;
import components.panels.TitledPanel;
import system._default_.Settings;
import system.enumerators.SecurityRole;
import system.ui.items.ItemAbout;
import system.ui.items.ItemSecurity;
import system.ui.items.ItemTheme;

public class PopUpSettings extends TitledPanel implements Settings{
	private static final long serialVersionUID = -2122393070139823198L;

	public PopUpSettings() {
		super("Settings");
		
		setPreferredSize(new Dimension(300,150));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		
		getPanelBody().setLayout(new BorderLayout());
		
		ListPane list_pane = new ListPane();
		list_pane.setItemHeight(30);
		getPanelBody().add(list_pane);

		list_pane.addItem(new ItemTheme() {
			private static final long serialVersionUID = -2133925395291482251L;
			@Override
			public void onChangeTheme(int mode) {
				changeThemeSetting(mode);
			}
		});
		
		list_pane.addItem(new ItemSecurity() {
			private static final long serialVersionUID = -1500111958509411305L;
			@Override
			public void onChangePassword(SecurityRole role, char[] old_pass, char[] new_pass) {
				changePasswordSecuritySettings(role, old_pass, new_pass);
			}
		});
		
		list_pane.addItem(new ItemAbout());
		
	}
	@Override
	public void onChangeThemeSetting() {
		
	}
	@Override
	public void onChangePasswordSetting() {
		
	}
}
