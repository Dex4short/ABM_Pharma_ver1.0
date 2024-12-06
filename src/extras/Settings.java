package extras;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Settings {

	private Settings() {
		
	}
	public static void rendering_hint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
