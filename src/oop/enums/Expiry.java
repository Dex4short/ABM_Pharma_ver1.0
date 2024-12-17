package oop.enums;

public enum Expiry {
	Good(1),
	Warning(2),
	Bad(3), 
	Expired(4),
	NEUTRAL(0),
	UNSET(0);
	
	private int state;
	Expiry(int state){
		this.state = state;
	}
	public int getState() {
		return state;
	}
}
