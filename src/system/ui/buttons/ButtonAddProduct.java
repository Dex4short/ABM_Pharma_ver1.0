package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import oop.enums.ProductCondition;
import res.Resource;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelAddProduct;

public abstract class ButtonAddProduct extends Button{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddProduct() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Product");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Window.load(new Runnable() {
			@Override
			public void run() {
				showActionPanel();
			}
		}, "");
		
	}
	public void addProduct(Product product, ProductCondition condition) {
		onAddProduct(product, condition);
	}
	
	public abstract void onAddProduct(Product product, ProductCondition condition);
	
	private void showActionPanel() {
		Window.getStackPanel().pushPanel(new ActionPanelAddProduct() {
			private static final long serialVersionUID = -2025771841420314051L;
			@Override
			public void onAddProductOk(Product product_set[]) {
				load(product_set);
			}
			private void load(Product product_set[]) {
				Window.load(
					() -> save(product_set),
					"Saving..."
				);
			}
			private void save(Product product_set[]) {
				ProductCondition condition = ProductCondition.STORED;
				for(Product product: product_set) {
					if(product != null) {
						product.getPackaging().setParentPackId(product_set[0].getPackaging().getPackId());
						addProduct(product, condition);
					}
					condition = ProductCondition.ARCHIVED;
				}
			}
		}, 20, 20, 226);
	
	}
}
