package system.ui.panels.actions;

import oop.Packaging;
import oop.essentials.PackagingManager;
import system.ui.Window;

public abstract class ActionPanelPackageAddToCart extends ActionPanelPackage{

	private static final long serialVersionUID = 6116507149987916939L;

	public ActionPanelPackageAddToCart(Packaging pack) {
		super(pack);
		setTitle("Add to Cart");
	}
	@Override
	public void onPackageOk(Packaging main_pack, Packaging sub_pack) {
		Window.load(() -> {
			Packaging extracted_packs[] = PackagingManager.extract(main_pack, sub_pack);			
			packageAddToCartOk(extracted_packs, sub_pack);
		}, "Adding to Cart...");
	}
	public void packageAddToCartOk(Packaging extracted_packs[], Packaging sub_pack) {
		onPackageAddToCartOk(extracted_packs, sub_pack);
	}
	
	public abstract void onPackageAddToCartOk(Packaging extracted_packs[], Packaging sub_pack);
	
}
