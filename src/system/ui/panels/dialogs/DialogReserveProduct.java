package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Date;
import system.objects.Product;
import system.objects.Remarks;
import system.objects.Time;
import system.ui.Window;
import system.ui.panels.actions.remarks.ActionPanelRemarksReservation;

public abstract class DialogReserveProduct extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;
	private Product products[];

	public DialogReserveProduct(Product products[]) {
		super("Reserve Product");
		setProducts(products);
		setText("Reserve selected Product(s)?");
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
	public void reserveProductOk(Product product) {
		onReserveProductOk(product);
	}
	
	public abstract void onReserveProductOk(Product product);
	
	private void showActionPanel() {
		Window.getStackPanel().pushPanel(new ActionPanelRemarksReservation() {
			private static final long serialVersionUID = 8375859060438443980L;
			@Override
			public void onReserveRemarksOk(Time time, Date date, String details) {
				for(Product product: products) {
					product.setRemarks(new Remarks(-1, date, time, details));
					reserveProductOk(product);
				}
			}
		}, 300, 250);
	}
}
