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
		Window.load(new Runnable() {
			@Override
			public void run() {
				showActionPanel();
			}
		}, "");
	}
	public void editProduct(Product new_product, Product old_product, ProductCondition product_condition) {
		onEditProduct(new_product, old_product, product_condition);
	}
	
	public abstract void onEditProduct(Product new_product, Product old_product, ProductCondition product_condition);
	public abstract Product[] getSelectedProductSet();
	
	private void showActionPanel() {
		Window.getStackPanel().pushPanel(new ActionPanelEditProduct(getSelectedProductSet()) {
			private static final long serialVersionUID = -4374619851729526914L;
			private int parentPack_id = -1;
			
			@Override
			public void onEditProductOk(Product new_products[], Product old_products[]) {
				load(new_products, old_products);
			}
			private void load(Product new_products[], Product old_products[]) {
				Window.load(
					() -> update(new_products, old_products),
					"Updating..."
				);
			}
			private void update(Product new_products[], Product old_products[]) {
				ProductCondition product_condition = ProductCondition.STORED;
				
				parentPack_id = -1;
				for(int p=0; p<new_products.length; p++) {
					if(new_products[p]!=null || old_products[p]!=null) {
						revalidateIds(new_products[p], old_products[p]);
						editProduct(new_products[p], old_products[p], product_condition);
					}
					product_condition = ProductCondition.ARCHIVED;
				}
			}
			private void revalidateIds(Product new_product, Product old_product) {
				revalidateParentPackId(new_product, old_product);
				
				if(new_product == null || old_product == null) return;
				new_product.setProdId(old_product.getProdId());
				new_product.getItem().setItemId(old_product.getItem().getItemId());
				new_product.getPackaging().setPackId(old_product.getPackaging().getPackId());
				new_product.getPricing().setPriceId(old_product.getPricing().getPriceId());
				new_product.getRemarks().setRemId(old_product.getRemarks().getRemId());
			}
			private void revalidateParentPackId(Product new_product, Product old_product) {
				if(new_product == null) return;
				if(parentPack_id == -1) {
					parentPack_id = old_product.getPackaging().getParentPackId();
				}
				new_product.getPackaging().setParentPackId(parentPack_id);
			}
		}, 20, 20, 226);
	}
}
