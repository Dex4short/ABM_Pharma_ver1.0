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

public abstract class ButtonPrintTransactions extends Button implements ActionListener{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintTransactions() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("printer.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogPanel("Print Transactions", "Print transaction records?") {
			private static final long serialVersionUID = 8286421196492002852L;
			@Override
			public void onOk() {
				printTransactions();
				Window.getStackPanel().popPanel();
				Window.floatMessage("Transaction records printed");
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	public void printTransactions() {
		onPrintTransactions(getTransactions());
	}
	
	public abstract void onPrintTransactions(Transaction transactions[]);
	public abstract Transaction[] getTransactions();
	
}
