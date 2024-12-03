package system._default_;

import system.ui.panels.PanelInventory;
import system.ui.panels.PanelTransactions;

public interface Administrator {

	public default void toInventory(PanelInventory inventory) {
		//code here
		
		
		onToInventory();
	}
	public default void toTransactions(PanelTransactions transactions) {
		//code here
		
		
		onToTransactions();
	}
	public default void toStore() {
		//code here
		
		
		onToStore();
	}
	public default void toResreves() {
		//code here
		
		
		onToReserves();
	}
	public default void toDisposal() {
		//code here
		
		
		onToDisposal();
	}
	public default void toProductReturns() {
		//code here
		
		
		
		onToReturns();
	}
	public default void toStatistics() {
		//code here
		
		
		
		onToStatistics();
	}
	public default void toCustomers() {
		//code here
		
		
		
		onToCustomers();
	}

	public void onToInventory();
	public void onToTransactions();
	public void onToStore();
	public void onToReserves();
	public void onToDisposal();
	public void onToReturns();
	public void onToStatistics();
	public void onToCustomers();
	
}
