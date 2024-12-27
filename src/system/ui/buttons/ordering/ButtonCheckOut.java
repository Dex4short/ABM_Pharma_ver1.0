package system.ui.buttons.ordering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Button;
import system.managers.AccountancyManager;
import system.objects.Cart;
import system.objects.Counter;
import system.objects.Transaction;
import system.ui.Window;
import system.ui.bars.BarFieldCart;
import system.ui.panels.actions.ActionPanelCheckOut;

public abstract class ButtonCheckOut extends Button.Quaternary implements ActionListener{
	private static final long serialVersionUID = -6341557504204266678L;
	private BarFieldCart barField_cart;

	public ButtonCheckOut(BarFieldCart barField_cart) {
		super("Check Out");
		setBarFieldCart(barField_cart);
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelCheckOut() {
			private static final long serialVersionUID = -2886112591953422606L;
			@Override
			public void onCheckoutOk(Transaction transaction) {
				checkOut(transaction);
			}
		}, 400, 370);
	}
	public void checkOut(Transaction transaction) {
		transaction.setCounter(getCounter());
		transaction.setCart(getCart());
		transaction.setCostAmount(AccountancyManager.calculateCostAmount(getCart().getOrders()));
		transaction.setDiscount(getBarFieldCart().getDiscount());
		transaction.setTotalNetAmount(getBarFieldCart().getTotalNetAmount());
		transaction.setProfit(AccountancyManager.calculateProfit(transaction.getTotalNetAmount(), transaction.getCostAmount()));
		onCheckOut(transaction);
	}
	public BarFieldCart getBarFieldCart() {
		return barField_cart;
	}
	public void setBarFieldCart(BarFieldCart barField_cart) {
		this.barField_cart = barField_cart;
	}

	public abstract void onCheckOut(Transaction transaction);
	public abstract Counter getCounter();
	public abstract Cart getCart();
	
}
