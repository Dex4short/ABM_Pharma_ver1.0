package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Date;
import system.objects.Product;
import system.objects.Remarks;
import system.objects.Time;
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
			public void onDisposeRemarksOk(Time time, Date date, String details) {
				for(Product product: products) {
					product.setRemarks(new Remarks(-1, date, time, details));
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
