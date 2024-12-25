package oop.implementations;

import java.awt.Color;
import java.awt.Font;

import components._misc_.Graphix;

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
			new Font("Arial", Font.BOLD, 10)
	};
	
}
