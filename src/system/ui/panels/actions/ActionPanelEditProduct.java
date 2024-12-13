package system.ui.panels.actions;

import oop.Product;

public abstract class ActionPanelEditProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	
	public ActionPanelEditProduct(Product product) {
		getPanelHead().setTitle("Edit Product");
		setProduct(product, 0);
	}
	@Override
	public void onProductOk(Product[] products) {
		editProductOk(products);
	}
	public void editProductOk(Product products[]) {
		onEditProductOk(products);
	}
	public abstract void onEditProductOk(Product products[]);
}
