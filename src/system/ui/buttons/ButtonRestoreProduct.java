package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Product;
import system.ui.Window;
import system.ui.panels.dialogs.DialogRestoreProduct;


public abstract class ButtonRestoreProduct extends Button implements ActionListener{
	private static final long serialVersionUID = 2163543714902288437L;

	public ButtonRestoreProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("restore.png")));
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.load(() -> {
			showActionPanel();
		}, "");
	}
	public void restoreProduct(Product product) {
		onRestoreProduct(product);
	}
	
	public abstract void onRestoreProduct(Product product);
	public abstract Product[] getSelectedProducts();
	
	private void showActionPanel() {
		Window.getStackPanel().pushPanel(new DialogRestoreProduct(getSelectedProducts()) {
			private static final long serialVersionUID = 6816596261649790662L;
			@Override
			public void onRestoreProductOk(Product product) {
				restoreProduct(product);
			}
		}, 200, 200);
	}
}