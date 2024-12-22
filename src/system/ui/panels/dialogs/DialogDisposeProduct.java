package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import oop.Product;
import oop.Remarks;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelRemarksDispose;

public abstract class DialogDisposeProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product products[];

	public DialogDisposeProduct(Product products[]) {
		super("Dispose Product");
		setProducts(products);
		setText("Dispose selected product(s)?");
	}
	@Override
	public void onOk() {
		showActionPanel();
		Window.getStackPanel().popPanel(this);
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public Product[] getProducts() {
		return products;
	}
	public void setProducts(Product products[]) {
		this.products = products;
	}
	public void disposeProductOk(Product product) {
		onDisposeProductOk(product);
	}
	
	public abstract void onDisposeProductOk(Product product);
	
	private void showActionPanel() {
		Window.getStackPanel().pushPanel(new ActionPanelRemarksDispose() {
			private static final long serialVersionUID = 6447871600282867621L;
			@Override
			public void onDisposeRemarksOk(Remarks remarks) {
				for(Product product: products) {
					product.setRemarks(remarks);
					disposeProductOk(product);
				}
			}
		}, 300, 250);
	}
}
