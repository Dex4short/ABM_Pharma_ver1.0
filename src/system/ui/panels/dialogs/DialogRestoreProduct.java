package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Product;
import system.ui.Window;

public abstract class DialogRestoreProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product products[];

	public DialogRestoreProduct(Product products[]) {
		super("Restore Product");
		setProducts(products);
		setText("Restore selected Product(s)?");
	}
	@Override
	public void onOk() {
		Window.load(() -> {
			for(Product product: products){
				restoreProductOk(product);
			}
		}, "Restoring...");
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
	public void restoreProductOk(Product product) {
		onRestoreProductOk(product);
	}
	
	public abstract void onRestoreProductOk(Product product);
	
}
