package system.ui.panels.actions;

import oop.Product;

public abstract class ActionPanelEditProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	private Product product_temp[];
	
	public ActionPanelEditProduct(Product product_set[]) {
		getPanelHead().setTitle("Edit Product");
		setProductSet(product_set);
		
		product_temp = product_set;
	}
	@Override
	public void onProductOk(Product[] products) {
		
		editProductOk(products, product_temp);
	}
	public void editProductOk(Product new_products[], Product old_products[]) {
		onEditProductOk(new_products, old_products);
	}
	public abstract void onEditProductOk(Product new_products[], Product old_products[]);
}
