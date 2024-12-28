package system.ui.panels.actions.selling;

import system.managers.PackagingManager;
import system.objects.Packaging;
import system.ui.Window;

public abstract class ActionPanelPackageRemoveFromCart extends ActionPanelPackage{
	private static final long serialVersionUID = 6116507149987916939L;

	public ActionPanelPackageRemoveFromCart(Packaging pack) {
		super(pack);
		setTitle("Remove Order");
		
		while(getUomList().getItemCount() != 1) {
			getUomList().removeItem(1);
		}
	}
	@Override
	public void onPackageOk(Packaging main_pack, Packaging sub_pack) {
		Window.load(() -> {
			try {
				Packaging extracted_packs[] = PackagingManager.extract(main_pack, sub_pack);
				packageRemoveFromCartOk(extracted_packs, sub_pack);
			} catch (Exception e) {
				Window.floatMessageAndBeep(e.getMessage());
			}
		}, "Removing...");
	}
	public void packageRemoveFromCartOk(Packaging extracted_packs[], Packaging sub_pack) {
		onPackageRemoveFromCartOk(extracted_packs, sub_pack);
	}
	
	public abstract void onPackageRemoveFromCartOk(Packaging extracted_packs[], Packaging sub_pack);
	
}
