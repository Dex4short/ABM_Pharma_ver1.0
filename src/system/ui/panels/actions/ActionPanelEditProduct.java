package system.ui.panels.actions;

import components.table.Row;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.ui.Window;
import system.ui.cells.CellQuantityField;
import system.ui.cells.CellTextField;
import system.ui.cells.clickable.CellButtonDatePicker;
import system.ui.cells.clickable.CellButtonUomPicker;

public abstract class ActionPanelEditProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	private Product old_products[];
	
	public ActionPanelEditProduct(Product product_set[]) {
		getPanelHead().setTitle("Edit Product");
		setProductSet(product_set);
		setOldProducts(product_set);
		
		if(product_set[0].getPackaging().getQty().isDeducted()) {
			Row rows[] = getRows();
			
			((CellTextField)rows[0].getCell(0)).getTextField().setEnabled(false);
			((CellTextField)rows[0].getCell(1)).getTextField().setEnabled(false);
			((CellTextField)rows[0].getCell(2)).getTextField().setEnabled(false);
			((CellTextField)rows[0].getCell(5)).getTextField().setEnabled(false);
			
			((CellButtonDatePicker)rows[0].getCell(3)).getButtonDatePicker().setEnabled(false);
			((CellButtonDatePicker)rows[0].getCell(4)).getButtonDatePicker().setEnabled(false);
			
			((CellQuantityField)rows[0].getCell(6)).getTwinNumericField().setEnabled(false);
			
			for(int i=0; i<3; i++) {
				((CellButtonUomPicker)rows[i].getCell(7)).getButtonUomPicker().setEnabled(false);
			}
			
			Window.floatMessage("Other fields may no longer be changed due to its deducted quantity");
		}
	}
	@Override
	public void onProductOk(Product[] new_products) {
		Window.load(
			new Runnable() {
				private int 
				item_id = old_products[0].getItem().getItemId(),
				parentPack_id = -1,
				pack_group = old_products[0].getPackaging().getPackagingGroup();
				
				public void run() {
					
					ProductCondition product_condition = ProductCondition.STORED;
					for(int p=0; p<new_products.length; p++) {
						if(new_products[p]==null && old_products[p]==null) return;
						
						revalidateIds(new_products[p], old_products[p]);
						editProductOk(new_products[p], old_products[p], product_condition);
						
						if(new_products[p] != null) 
							parentPack_id = new_products[p].getPackaging().getPackId();
						else if(old_products[p] != null) 
							parentPack_id = old_products[p].getPackaging().getPackId();
						
						product_condition = ProductCondition.ARCHIVED;
					}
				}
				private void revalidateIds(Product new_product, Product old_product) {
					if(new_product==null) return;
					new_product.getItem().setItemId(item_id);
					new_product.getPackaging().setParentPackId(parentPack_id);
					new_product.getPackaging().setPackagingGroup(pack_group);
					
					if(old_product==null) return;
					new_product.setProdId(old_product.getProdId());
					new_product.getPackaging().setPackId(old_product.getPackaging().getPackId());
					new_product.getPricing().setPriceId(old_product.getPricing().getPriceId());
				}
			},
			"Updating..."
		);
	}
	public void setOldProducts(Product old_products[]) {
		this.old_products = old_products;
	}
	public Product[] getOldProducts() {
		return old_products;
	}
	public void editProductOk(Product new_product, Product old_product, ProductCondition product_condition) {
		onEditProductOk(new_product, old_product, product_condition);
	}
	
	public abstract void onEditProductOk(Product new_product, Product old_product, ProductCondition product_condition);
	
}
