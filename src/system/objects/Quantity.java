package system.objects;

public class Quantity {
	private int amount, size;
	
	public Quantity(int amount) {
		setAmount(amount);
		setSize(amount);
	}
	public Quantity(int amount, int size) {
		setAmount(amount);
		setSize(size);
	}
	@Override
	public String toString() {
		return getAmount() + "/" + getSize();
	}
	public int getAmount() {
		return amount;
	}
	public int getSize() {
		return size;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void add(Quantity quantity) {
		Quantity qty = addQuantities(this, quantity);
		setAmount(qty.getAmount());
		setSize(qty.getSize());
	}
	public boolean isRunningOut(int atleast_size) {
		return amount < atleast_size;
	}
	public boolean isOutOfStock() {
		return amount == 0;
	}
	
	public static Quantity addQuantities(Quantity qty_a, Quantity qty_b) {
		int amount1,amount2,amount,size;
		
		amount1 = qty_a.getAmount();
		amount2 = qty_b.getAmount();
		amount  = amount1 + amount2;
		
		size = qty_a.getSize();
		if(amount > size) {
			size = amount;
		}
		
		return new Quantity(amount, size);
	}
}
