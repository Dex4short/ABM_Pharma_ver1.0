package system._default_;

public interface Disposal {
	
	public default void searchFromDisposal() {
		onSearchFromDisposal();
	}
public default void returnToInventory() {
		
		onReturnToInventory();
	}
public default void reserveFromDisposal() {
	
		onReserveFromDisposal();
	}
public default void deleteFromReserves() {
		
		onDeleteFromReserves();
	}
	public default void loadAllReserves() {
		
		onLoadAllDisposal();
	}
	public void onSearchFromDisposal();
	public void onReturnToInventory();
	public void onReserveFromDisposal();
	public void onDeleteFromReserves();
	public void onLoadAllDisposal();
}

