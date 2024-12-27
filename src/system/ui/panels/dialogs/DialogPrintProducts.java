package system.ui.panels.dialogs;

import components.panels.DialogPanel;
import system.objects.Product;
import system.ui.Window;

public abstract class DialogPrintProducts extends DialogPanel{
	private static final long serialVersionUID = -6761604159530514131L;

	public DialogPrintProducts(Product product) {
		super("Dispose Product");
		setText("Print all product(s)?");
	}
	@Override
	public void onOk() {
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
}
