package system._default_;

import system.ui.panels.PanelDisposal;
import system.ui.panels.PanelInventory;
import system.ui.panels.PanelReserves;
import system.ui.panels.PanelTransactions;

public interface Administrator {

	public default void toInventory(PanelInventory inventory) {
		onToInventory(inventory);
	}
	public default void toTransactions(PanelTransactions transactions) {
		onToTransactions();
	}
	public default void toStore() {
		onToStore();
	}
	public default void toResreves(PanelReserves reserves) {		
		onToReserves(reserves);
	}
	public default void toDisposal(PanelDisposal disposal) {
		onToDisposal(disposal);
	}
	public default void toProductReturns() {
		onToReturns();
	}
	public default void toStatistics() {
		onToStatistics();
	}
	public default void toCustomers() {
		onToCustomers();
	}

	public void onToInventory(PanelInventory inventory);
	public void onToTransactions();
	public void onToStore();
	public void onToReserves(PanelReserves reserves);
	public void onToDisposal(PanelDisposal disposal);
	public void onToReturns();
	public void onToStatistics();
	public void onToCustomers();
	
}
