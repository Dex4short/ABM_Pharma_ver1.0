package system._default_;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import database.MySQL_Security;
import system.enumerators.SecurityRole;

public interface Settings {

	public default void changeThemeSettings(int theme_mode) {
		
	}
	public default void changePasswordSecuritySettings(SecurityRole role, char old_password[], char new_password[]) {
		MySQL_Security.updateSecurityData(role, old_password, new_password);
	}
	
	public static void rendering_hint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
}
