package system._default_;

import components.panels.Panel;
import system.ui.panels.PanelDisposal;
import system.ui.panels.PanelInventory;
import system.ui.panels.PanelReserves;
import system.ui.panels.PanelStore;

public interface Administrator {
	
	public default void toInventory() {
		onToInventory((PanelInventory)nextPanel());
	}
	public default void toTransactions() {
		onToTransactions();
	}
	public default void toStore() {
		onToStore((PanelStore)nextPanel());
	}
	public default void toResreves() {		
		onToReserves((PanelReserves)nextPanel());
	}
	public default void toDisposal() {
		onToDisposal((PanelDisposal)nextPanel());
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

	public Panel nextPanel();
	public void onToInventory(PanelInventory inventory);
	public void onToTransactions();
	public void onToStore(PanelStore store);
	public void onToReserves(PanelReserves reserves);
	public void onToDisposal(PanelDisposal disposal);
	public void onToReturns();
	public void onToStatistics();
	public void onToCustomers();
	
}
