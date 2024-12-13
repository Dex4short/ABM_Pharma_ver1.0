package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import res.Resource;


public abstract class ButtonReturnToInventory extends Button{
	private static final long serialVersionUID = 2163543714902288437L;

	public ButtonReturnToInventory() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		setIcon(new ImageIcon(Resource.get("Inventory.png")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	public void returnProductToInventory(Product product) {
		onReturnProductToInventory(product);
	}
	
	public abstract void onReturnProductToInventory(Product product);
	public abstract Product getSelectedProduct();
	
}