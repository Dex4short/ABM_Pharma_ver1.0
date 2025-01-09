package system.ui.appearance;

import java.awt.Color;
import java.awt.Font;
import components._misc_.Graphix;

public interface Theme {
	public static final String THEME_MODES[] = {"Light Mode", "Dark Mode"};
	public Color
	main_color[] = {
			new Color(1,34,131),
			new Color(5,145,255),
			new Color(255, 255, 255),
			new Color(214, 214, 214),
			new Color(128, 128, 128)
	},
	accent_color[][] = {
			{
				new Color(1,34,131), 
				Color.green.darker(),
				Color.yellow.darker(), 
				Color.red.darker(), 
				Color.gray.darker()
			},
			{
				new Color(186, 203, 254),
				Graphix.setAlpha(Color.green.brighter(), 128),
				Graphix.setAlpha(Color.yellow.brighter(), 128),
				Graphix.setAlpha(Color.red.brighter(), 128),
				Graphix.setAlpha(Color.gray.brighter(), 128),
			}
	},
	text_color[] = {
			new Color(0, 0, 0),
			new Color(128, 128, 128),
			new Color(192, 192, 192),
			new Color(255, 255, 255)
	};
	public Font font[] = {
			new Font("Arial", Font.BOLD, 10),
			new Font("Arial", Font.BOLD, 15)
	};
	
	public static void lightMode() {
		main_color[0] = new Color(1,34,131);
		main_color[1] = new Color(5,145,255);
		main_color[2] = new Color(255, 255, 255);
		main_color[3] = new Color(214, 214, 214);
		main_color[4] = new Color(128, 128, 128);

		accent_color[0][0] = new Color(1,34,131);
		accent_color[0][1] = Color.green.darker();
		accent_color[0][2] = Color.yellow.darker(); 
		accent_color[0][3] = Color.red.darker();
		accent_color[0][4] = Color.gray.darker();
		
		accent_color[1][0] = new Color(186, 203, 254);
		accent_color[1][1] = Graphix.setAlpha(Color.green.brighter(), 128);
		accent_color[1][2] = Graphix.setAlpha(Color.yellow.brighter(), 128);
		accent_color[1][3] = Graphix.setAlpha(Color.red.brighter(), 128);
		accent_color[1][4] = Graphix.setAlpha(Color.gray.brighter(), 128);
		
		text_color[0] = new Color(0, 0, 0);
		text_color[1] = new Color(128, 128, 128);
		text_color[2] = new Color(192, 192, 192);
		text_color[3] = new Color(255, 255, 255);
	}
	public static void darkMode() {
		main_color[0] = new Color(  63,  65,  69);
		main_color[1] = new Color( 126, 131, 133);
		main_color[2] = new Color(   0,   0,   0);
		main_color[3] = new Color(  41,  41,  41);
		main_color[4] = new Color( 128, 128, 128);

		accent_color[0][0] = new Color(1,34,131);
		accent_color[0][1] = Color.green.darker();
		accent_color[0][2] = Color.yellow.darker(); 
		accent_color[0][3] = Color.red.darker();
		accent_color[0][4] = Color.gray.darker();
		
		accent_color[1][0] = new Color(186, 203, 254);
		accent_color[1][1] = Graphix.setAlpha(Color.green.brighter(), 128);
		accent_color[1][2] = Graphix.setAlpha(Color.yellow.brighter(), 128);
		accent_color[1][3] = Graphix.setAlpha(Color.red.brighter(), 128);
		accent_color[1][4] = Graphix.setAlpha(Color.gray.brighter(), 128);
		
		text_color[3] = new Color(0, 0, 0);
		text_color[2] = new Color(128, 128, 128);
		text_color[1] = new Color(192, 192, 192);
		text_color[0] = new Color(255, 255, 255);
	}
}
