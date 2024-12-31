package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Product;
import system.ui.Window;
import system.ui.panels.dialogs.DialogDeleteProduct;

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
		DialogDeleteProduct dialog_dispose_product = new DialogDeleteProduct(getSelectedProducts()) {
			private static final long serialVersionUID = 6203612887549457328L;
			@Override
			public void onDeleteProductOk(Product product) {
				deleteProduct(product);
			}
		};
		Window.getStackPanel().pushPanel(dialog_dispose_product, 200, 200);
	}
	public void deleteProduct(Product product) {
		onDeleteProduct(product);
	}
	
	public abstract void onDeleteProduct(Product product);
	public abstract Product[] getSelectedProducts();
	
}
