package system._default_;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import database.MySQL_Security;
import io.InputOutput;
import system.ABM_Pharma;
import system.enumerators.SecurityRole;
import system.ui.appearance.Theme;

public interface Settings {

	public default void changeThemeSettings(String theme_mode) {
		InputOutput.write("inf/theme_settings.txt", theme_mode);
		ABM_Pharma.restart();
	}
	public default void changePasswordSecuritySettings(SecurityRole role, char old_password[], char new_password[]) {
		MySQL_Security.updateSecurityData(role, old_password, new_password);
	}
	
	public static void rendering_hint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	public static String current_theme() {
		String theme_mode = InputOutput.read("inf/theme_settings.txt");
		return theme_mode;
	}
	public static void display_theme() {
		String theme_mode = current_theme();
		
		if(theme_mode.equals("Dark Mode")) {
			Theme.darkMode();
		}
		else {
			Theme.lightMode();
		}
		
		System.out.println(">>>" + theme_mode);
	}
	
}
