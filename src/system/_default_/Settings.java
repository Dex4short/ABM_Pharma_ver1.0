package system._default_;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import database.MySQL_Security;
import io.InputOutput;
import system.enumerators.SecurityRole;
import system.ui.items.ItemTheme;

public interface Settings {

	public default void changeThemeSetting(int theme_mode) {
		InputOutput.write("theme_settings.txt", ItemTheme.THEMES[theme_mode]);
		onChangeThemeSetting();
	}
	public default void changePasswordSecuritySettings(SecurityRole role, char old_password[], char new_password[]) {
		MySQL_Security.updateSecurityData(role, old_password, new_password);
		onChangePasswordSetting();
	}

	public void onChangeThemeSetting();
	public void onChangePasswordSetting();
	
	public static void rendering_hint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
}
