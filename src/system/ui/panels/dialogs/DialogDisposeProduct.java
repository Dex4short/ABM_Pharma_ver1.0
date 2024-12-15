package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import oop.Product;
import system.ui.Window;

public abstract class DialogDisposeProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product product;

	public DialogDisposeProduct(Product product) {
		super("Dispose Product");
		setProduct(product);
		setText("Dispose selected product(s)?");
	}
	@Override
	public void onOk() {
		disposeProductOk(product);
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void disposeProductOk(Product product) {
		onDisposeProductOk(product);
	}
	
	public abstract void onDisposeProductOk(Product product);
	
}
