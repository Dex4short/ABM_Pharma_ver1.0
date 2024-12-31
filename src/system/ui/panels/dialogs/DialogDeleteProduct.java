package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Product;
import system.ui.Window;

public abstract class DialogDeleteProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product products[];

	public DialogDeleteProduct(Product products[]) {
		super("Delete Product");
		setProducts(products);
		setText("Delete selected product(s)?");
	}
	@Override
	public void onOk() {
		Window.load(() -> {
			for(Product product: getProducts()) {
				deleteProductOk(product);
			}
		}, "Deleting...");
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
	public void deleteProductOk(Product product) {
		onDeleteProductOk(product);
	}
	
	public abstract void onDeleteProductOk(Product product);
	
}
