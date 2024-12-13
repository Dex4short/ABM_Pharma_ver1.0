package system.ui.panels.actions;

import oop.Product;

public abstract class ActionPanelAddProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	
	public ActionPanelAddProduct() {
		getPanelHead().setTitle("Add Product");
	}
	@Override
	public void onProductOk(Product[] products) {
		addProductOk(products);
	}
	public void addProductOk(Product products[]) {
		onAddProductOk(products);
	}
	public abstract void onAddProductOk(Product products[]);
}
