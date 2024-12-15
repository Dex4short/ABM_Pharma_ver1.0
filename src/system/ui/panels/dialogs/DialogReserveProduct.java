package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import oop.Product;
import system.ui.Window;

public abstract class DialogReserveProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product product;

	public DialogReserveProduct(Product product) {
		super("Reserve Product");
		setProduct(product);
		setText("Reserve selected Product(s)?");
	}
	@Override
	public void onOk() {
		reserveProductOk(product);
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
	public void reserveProductOk(Product product) {
		onReserveProductOk(product);
	}
	
	public abstract void onReserveProductOk(Product product);
	
}
