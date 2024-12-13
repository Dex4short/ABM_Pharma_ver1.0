package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import oop.enums.ProductCondition;
import res.Resource;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelEditProduct;

public abstract class ButtonEditProduct extends Button{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("pencil.png")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ActionPanelEditProduct actionPanel_editProduct = new ActionPanelEditProduct(getSelectedProduct()) {
			private static final long serialVersionUID = -4374619851729526914L;
			@Override
			public void onEditProductOk(Product[] products) {
				ProductCondition condition = ProductCondition.STORED;
				for(Product product: products) {
					editProduct(product, condition);
					condition = ProductCondition.ARCHIVED;
				}
			}
		};
		Window.getStackPanel().pushPanel(actionPanel_editProduct, 20, 20, 226);
	}
	public void editProduct(Product product, ProductCondition condition) {
		onEditProduct(product, condition);
	}
	
	public abstract void onEditProduct(Product product, ProductCondition condition);
	public abstract Product getSelectedProduct();
	
}
