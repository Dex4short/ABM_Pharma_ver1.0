package system._default_;


public interface Customers {
	
	public default void searchFromInventory() {
		
		onSearchFromCustomers();
	}
	public default void deleteFromCustomers() {
		
		onDeleteFromCustomers();
	}
	public default void editFromCustomers() {
		
		onEditFromCustomers();
	}
	public default void addToCustomers() {
		
		onAddToCustomers();
	}
	public default void loadAllCustomers() {
		
		onLoadAllCustomers();
	}

	public void onSearchFromCustomers();
	public void onDeleteFromCustomers();
	public void onEditFromCustomers();
	public void onAddToCustomers();
	public void onLoadAllCustomers();
}

