package system.ui.buttons.printings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import components.panels.DialogPanel;
import res.Resource;
import system.objects.Product;
import system.ui.Window;

public abstract class ButtonPrintProducts extends Button implements ActionListener{
	private static final long serialVersionUID = 2144489025943712049L;

	public ButtonPrintProducts() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		
		setIcon(new ImageIcon(Resource.get("printer.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogPanel("Print Products", "Print all products?") {
			private static final long serialVersionUID = 8286421196492002852L;
			@Override
			public void onOk() {
				printProducts();
				Window.getStackPanel().popPanel();
				Window.floatMessage("All products printed");
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	public void printProducts() {
		onPrintProducts(getProducts());
	}
	
	public abstract void onPrintProducts(Product products[]);
	public abstract Product[] getProducts();
	
}
