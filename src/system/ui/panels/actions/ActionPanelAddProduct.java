package system.ui.panels.actions;

import oop.Product;
import oop.enumerations.ProductCondition;
import system.ui.Window;

public abstract class ActionPanelAddProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	
	public ActionPanelAddProduct() {
		getPanelHead().setTitle("Add Product");
	}
	@Override
	public void onProductOk(Product[] product_set) {
		Window.load(
			() -> {
				ProductCondition condition = ProductCondition.STORED;
				
				int item_id=-1, parentPack_id=-1;
				for(Product product: product_set) {
					if(product == null) break;
					product.getItem().setItemId(item_id);
					product.getPackaging().setParentPackId(parentPack_id);
					product.getPackaging().setPackagingGroup(product_set[0].getPackaging().getPackagingGroup());
					addProductOk(product, condition);
					
					item_id = product.getItem().getItemId();
					parentPack_id = product.getPackaging().getPackId();
					condition = ProductCondition.ARCHIVED;
				}
			},
			"Saving..."
		);
	}
	public void addProductOk(Product product, ProductCondition condition) {
		onAddProductOk(product, condition);
	}
	public abstract void onAddProductOk(Product product, ProductCondition condition);
}
