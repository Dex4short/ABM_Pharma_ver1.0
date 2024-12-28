package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Product;
import system.objects.Remarks;
import system.ui.Window;
import system.ui.panels.actions.remarks.ActionPanelRemarksDisposal;

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
		Window.getStackPanel().pushPanel(new ActionPanelRemarksDisposal() {
			private static final long serialVersionUID = 6447871600282867621L;
			@Override
			public void onDisposeRemarksOk(Remarks remarks) {
				for(Product product: products) {
					product.setRemarks(remarks);
					disposeProductOk(product);
				}
			}
		}, 300, 250);
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
	
}
