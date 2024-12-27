package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Order;
import system.objects.Transaction;
import system.ui.Window;
import system.ui.panels.dialogs.DialogReturnProduct;

public abstract class ButtonReturnOrder extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonReturnOrder() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("return.png")));
		setText("Return Order");
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogReturnProduct(getSelectedTransacton(), getSelectedOrders()) {
			private static final long serialVersionUID = -4230431942636921503L;
			@Override
			public void onReturnProductOk(Order orders[]) {
				returnOrder(orders);
			}
		}, 200, 200);
	}
	public void returnOrder(Order orders[]) {
		onReturnOrder(orders);
	}
	
	public abstract void onReturnOrder(Order orders[]);
	public abstract Transaction getSelectedTransacton();
	public abstract Order[] getSelectedOrders();
	
}
