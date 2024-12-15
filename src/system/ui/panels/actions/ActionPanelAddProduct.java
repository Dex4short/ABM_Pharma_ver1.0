package system.ui.panels.actions;

import oop.Product;

public abstract class ActionPanelAddProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	
	public ActionPanelAddProduct() {
		getPanelHead().setTitle("Add Product");
	}
	@Override
	public void onProductOk(Product[] product_set) {
		addProductOk(product_set);
	}
	public void addProductOk(Product product_set[]) {
		onAddProductOk(product_set);
	}
	public abstract void onAddProductOk(Product product_set[]);
}
