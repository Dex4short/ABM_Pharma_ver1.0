package system.enumerators;


public enum UomType {
	set(0, "_"), box(1, "b"), pieces(2, "p"), stab(3, "s"), capsule(4, "c"), tablet(5, "t"), sachet(6, "S"), bag(7, "B"), jar(8, "j");
	
	private int i;
	private String id;
	
	UomType(int i, String id){
		setIndex(i);
		setId(id);
	}
	public int getIndex(){
		return i;
	}
	public void setIndex(int index) {
		i = index;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
};
