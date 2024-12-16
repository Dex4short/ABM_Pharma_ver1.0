package system._default_;

public interface Reserves {
	
	public default void searchFromInventory() {
		onSearchFromInventory();
	}
public default void returnToInventory() {
		
		onReturnToInventory();
	}
public default void deleteFromReserves() {
		
		onDeleteFromReserves();
	}
	public default void loadAllReserves() {
		
		onLoadAllReserves();
	}
	public void onSearchFromInventory();
	public void onReturnToInventory();
	public void onDeleteFromReserves();
	public void onLoadAllReserves();
}
