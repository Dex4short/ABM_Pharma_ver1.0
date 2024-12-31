package system.ui.panels.actions;

import components.table.Row;
import system.objects.Product;
import system.ui.Window;
import system.ui.cells.CellQuantityField;
import system.ui.cells.CellTextField;
import system.ui.cells.clickable.CellButtonDatePicker;
import system.ui.cells.clickable.CellButtonUomPicker;

public abstract class ActionPanelEditProduct  extends ActionPanelProduct{
	private static final long serialVersionUID = -4880139382406239412L;
	private Product product_temp[];
	
	public ActionPanelEditProduct(Product product_set[]) {
		getPanelHead().setTitle("Edit Product");
		setProductSet(product_set);
		
		product_temp = product_set;
		
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
	public void onProductOk(Product[] products) {
		Window.load(
			() -> editProductOk(products, product_temp),
			"Updating..."
		);
	}
	public void editProductOk(Product new_products[], Product old_products[]) {
		onEditProductOk(new_products, old_products);
	}
	
	public abstract void onEditProductOk(Product new_products[], Product old_products[]);
	
}
