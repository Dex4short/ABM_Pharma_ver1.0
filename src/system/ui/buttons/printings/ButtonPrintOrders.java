package system.ui.buttons.printings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import components.panels.DialogPanel;
import res.Resource;
import system.objects.Transaction;
import system.ui.Window;

public abstract class ButtonPrintOrders extends Button implements ActionListener{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintOrders() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("printer.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogPanel("Print Orders", "This will print a receipt, continue?") {
			private static final long serialVersionUID = 8286421196492002852L;
			@Override
			public void onOk() {
				printOrders();
				Window.getStackPanel().popPanel();
				Window.floatMessage("Receipt printed");
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	public void printOrders() {
		onPrintReceipt(getSelectedTransaction());
	}
	
	public abstract void onPrintReceipt(Transaction transaction);
	public abstract Transaction getSelectedTransaction();
	
}
