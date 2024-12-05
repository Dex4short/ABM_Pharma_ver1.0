package oop.enums;

import java.awt.Color;

import oop.interfaces.Theme;


public enum ExpState {
	Good(Color.green.darker()),
	Warning(Color.yellow.darker()),
	Bad(Color.red.darker()), 
	Expired(Color.darkGray.darker()),
	NEUTRAL(Theme.main_color[3]),
	UNSET(Theme.main_color[3]);
	
	private final Color color;
	ExpState(Color color){
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
