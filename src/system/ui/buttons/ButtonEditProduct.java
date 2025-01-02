package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelEditProduct;

public abstract class ButtonEditProduct extends Button implements ActionListener{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		
		setIcon(new ImageIcon(Resource.get("pencil.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Product products[] = getSelectedProductSet();
		
		if(products[0].getPackaging().getParentPackId() != -1) {
			String
			description = products[0].getItem().getDescription(),
			uom_name = products[0].getPackaging().getUom().getUnitType().name();
			
			Window.pushDialog(
				"Edit Product", 
				"\"" + description + " (" + uom_name +")\" is a sub product and changes may only be applied from its origin.");
			return;
		}
		
		Window.load( () -> {
			Window.getStackPanel().pushPanel(new ActionPanelEditProduct(products) {
				private static final long serialVersionUID = -4374619851729526914L;				
				@Override
				public void onEditProductOk(Product new_product, Product old_product, ProductCondition product_condition) {
					editProduct(new_product, old_product, product_condition);
				}
			}, 20, 20, 226);
		
		}, "");
	}
	public void editProduct(Product new_product, Product old_product, ProductCondition product_condition) {
		onEditProduct(new_product, old_product, product_condition);
	}
	
	public abstract void onEditProduct(Product new_product, Product old_product, ProductCondition product_condition);
	public abstract Product[] getSelectedProductSet();
	
}
