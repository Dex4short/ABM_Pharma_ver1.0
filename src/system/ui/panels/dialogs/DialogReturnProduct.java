package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Order;
import system.objects.Remarks;
import system.objects.Transaction;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelRemarksReturn;

public abstract class DialogReturnProduct extends DialogPanel{
	private static final long serialVersionUID = 3800261841382671423L;
	private Transaction transaction;
	private Order orders[];
	
	public DialogReturnProduct(Transaction trasnaction, Order orders[]) {
		super("Return Product");
		setText("Return selected products(s)? This will never be undone.");
		setTransaction(trasnaction);
		setOrders(orders);
	}
	@Override
	public void onOk() {
		Window.getStackPanel().pushPanel(new ActionPanelRemarksReturn(getTransaction()) {
			private static final long serialVersionUID = 3958441542959575603L;
			@Override
			public void onReturnRemarksOk(Remarks remarks) {
				returnProductOk(remarks);
			}
		}, 300, 300);
		Window.getStackPanel().popPanel(this);
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Order[] getOrders() {
		return orders;
	}
	public void setOrders(Order orders[]) {
		this.orders = orders;
	}
	public void returnProductOk(Remarks remarks) {
		Order orders[] = getOrders();
		for(Order order: orders) {
			order.getProduct().setRemarks(remarks);
		}
		onReturnProductOk(orders);
	}
	
	public abstract void onReturnProductOk(Order orders[]);
	
}
