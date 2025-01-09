package system._default_;

import components.panels.Panel;
import system.ui.panels.sub_panels.PanelCustomers;
import system.ui.panels.sub_panels.PanelDisposal;
import system.ui.panels.sub_panels.PanelEmployees;
import system.ui.panels.sub_panels.PanelInventory;
import system.ui.panels.sub_panels.PanelItems;
import system.ui.panels.sub_panels.PanelProductReturns;
import system.ui.panels.sub_panels.PanelReserves;
import system.ui.panels.sub_panels.PanelStore;
import system.ui.panels.sub_panels.PanelTransactions;

public interface Administrator {
	
	public default void toInventory() {
		onToInventory((PanelInventory)nextPanel());
	}
	public default void toItems() {
		onToItems((PanelItems)nextPanel());
	}
	public default void toTransactions() {
		onToTransactions((PanelTransactions)nextPanel());
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
		onToProductReturns((PanelProductReturns)nextPanel());
	}
	public default void toStatistics() {
		onToStatistics();
	}
	public default void toCustomers() {
		onToCustomers((PanelCustomers)nextPanel());
	}
	public default void toEmployees() {
		onToEmployees((PanelEmployees)nextPanel());
	}

	public Panel nextPanel();
	public void onToInventory(PanelInventory inventory);
	public void onToItems(PanelItems items);
	public void onToTransactions(PanelTransactions transactions);
	public void onToStore(PanelStore store);
	public void onToReserves(PanelReserves reserves);
	public void onToDisposal(PanelDisposal disposal);
	public void onToProductReturns(PanelProductReturns product_returns);
	public void onToStatistics();
	public void onToCustomers(PanelCustomers customers);
	public void onToEmployees(PanelEmployees employees);
	
}
