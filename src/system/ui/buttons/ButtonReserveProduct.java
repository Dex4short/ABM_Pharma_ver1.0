package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Product;
import system.ui.Window;
import system.ui.panels.dialogs.DialogReserveProduct;

public abstract class ButtonReserveProduct extends Button implements ActionListener{
	private static final long serialVersionUID = -2701202578036756483L;
	
	public ButtonReserveProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		
		setIcon(new ImageIcon(Resource.get("reserve.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DialogReserveProduct dialog_reserve_product = new DialogReserveProduct(getSelectedProducts()) {
			private static final long serialVersionUID = -7099745788054494469L;
			@Override
			public void onReserveProductOk(Product product) {
				reserveProduct(product);
			}
		};
		Window.getStackPanel().pushPanel(dialog_reserve_product, 200, 200);
	}
	public void reserveProduct(Product product) {
		onReserveProduct(product);
	}
	
	public abstract void onReserveProduct(Product product);
	public abstract Product[] getSelectedProducts();
	
}
