package system._default_;

import components.panels.Panel;
import database.MySQL_Products;
import system.enumerators.ProductCondition;
import system.managers.NotificationsManager;
import system.objects.Notification;
import system.objects.Product;
import system.ui.panels.PanelAdmin;
import system.ui.panels.PanelCustomers;
import system.ui.panels.PanelDisposal;
import system.ui.panels.PanelInventory;
import system.ui.panels.PanelProductReturns;
import system.ui.panels.PanelReserves;
import system.ui.panels.PanelStore;
import system.ui.panels.PanelTransactions;

public interface Administrator {
	
	public default void toInventory() {
		onToInventory((PanelInventory)nextPanel());
		
		Notification notification;
		boolean notify=false;
		for(Product product: MySQL_Products.selectProducts(ProductCondition.RESERVED)) {
			notification = NotificationsManager.createNotification(product);
			if(notification != null) {
				NotificationsManager.pushNotification(notification);
				notify = true;
			}
		}
		PanelAdmin.notifyTab(3, notify);
	}
	public default void toTransactions() {
		onToTransactions((PanelTransactions)nextPanel());
	}
	public default void toStore() {
		onToStore((PanelStore)nextPanel());
	}
	public default void toResreves() {		
		onToReserves((PanelReserves)nextPanel());
		
		Notification notification;
		boolean notify=false;
		for(Product product: MySQL_Products.selectProducts(ProductCondition.STORED)) {
			notification = NotificationsManager.createNotification(product);
			if(notification != null) {
				NotificationsManager.pushNotification(notification);
				notify = true;
			}
		}
		PanelAdmin.notifyTab(0, notify);
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

	public Panel nextPanel();
	public void onToInventory(PanelInventory inventory);
	public void onToTransactions(PanelTransactions transactions);
	public void onToStore(PanelStore store);
	public void onToReserves(PanelReserves reserves);
	public void onToDisposal(PanelDisposal disposal);
	public void onToProductReturns(PanelProductReturns product_returns);
	public void onToStatistics();
	public void onToCustomers(PanelCustomers customers);
	
}
