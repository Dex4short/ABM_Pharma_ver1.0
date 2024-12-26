package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import res.Resource;

public abstract class ButtonDeleteProduct extends Button implements ActionListener{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDeleteProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		
		setIcon(new ImageIcon(Resource.get("delete.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		DialogDisposeProduct dialog_dispose_product = new DialogDisposeProduct(getSelectedProducts()) {
			private static final long serialVersionUID = 6203612887549457328L;
			@Override
			public void onDisposeProductOk(Product product) {
				disposeProduct(product);
			}
		};
		Window.getStackPanel().pushPanel(dialog_dispose_product, 200, 200);
		*/
	}
	public void deleteProduct(Product product) {
		onDeleteProduct(product);
	}
	
	public abstract void onDeleteProduct(Product product);
	public abstract Product[] getSelectedProducts();
	
}
