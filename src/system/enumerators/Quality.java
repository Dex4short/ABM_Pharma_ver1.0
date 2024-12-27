package system.enumerators;

public enum Quality {
	Good(1),
	Warning(2),
	Bad(3), 
	Expired(4),
	NEUTRAL(0),
	UNSET(0);
	
	private int state;
	Quality(int state){
		this.state = state;
	}
	public int getState() {
		return state;
	}
}
