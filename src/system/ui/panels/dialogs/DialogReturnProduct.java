package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Date;
import system.objects.Order;
import system.objects.Remarks;
import system.objects.Time;
import system.objects.Transaction;
import system.ui.Window;
import system.ui.panels.actions.remarks.ActionPanelRemarksReturning;

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
		Window.getStackPanel().pushPanel(new ActionPanelRemarksReturning(getTransaction()) {
			private static final long serialVersionUID = 3958441542959575603L;
			@Override
			public void onReturnRemarksOk(Time time, Date date, String details) {
				details += 
					"Customer: " + transaction.getCustomer().getCustomerName() + "\n" + 
					"Tin No.: " + transaction.getTinNo() + "\n";
				returnProductOk(time, date, details);
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
	public void returnProductOk(Time time, Date date, String details) {
		Order orders[] = getOrders();
		for(Order order: orders) {
			order.getProduct().setRemarks(new Remarks(-1, date, time, details));
		}
		onReturnProductOk(orders);
	}
	
	public abstract void onReturnProductOk(Order orders[]);
	
}
