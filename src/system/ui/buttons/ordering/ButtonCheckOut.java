package system.ui.buttons.ordering;

import java.awt.event.MouseEvent;

import components.Button;
import oop.Cart;
import oop.Counter;
import oop.Transaction;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelCheckOut;

public abstract class ButtonCheckOut extends Button.Quaternary{
	private static final long serialVersionUID = -6341557504204266678L;
	private Counter counter;

	public ButtonCheckOut(Counter counter) {
		super("Check Out");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
		
		onCheckOut(transaction);
	}
	public Counter getCounter() {
		return counter;
	}
	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public abstract void onCheckOut(Transaction transaction);
	public abstract Cart getCart();
	
}
