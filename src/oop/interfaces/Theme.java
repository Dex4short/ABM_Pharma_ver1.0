package oop.interfaces;

import java.awt.Color;
import java.awt.Font;

public interface Theme {
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
				new Color(0, 255, 0),
				new Color(202, 202, 0), 
				new Color(255, 0, 0), 
				new Color(128,128, 128)
			},
			{
				new Color(186, 203, 254),
				new Color(192, 236, 192),
				new Color(255, 255, 179), 
				new Color(255, 149, 149), 
				new Color(214, 214, 214)
			}
	},
	text_color[] = {
			new Color(0, 0, 0),
			new Color(128, 128, 128),
			new Color(192, 192, 192),
			new Color(255, 255, 255)
	};
	public Font font[] = {
			new Font("Arial", Font.BOLD, 11)
	};
	
}
