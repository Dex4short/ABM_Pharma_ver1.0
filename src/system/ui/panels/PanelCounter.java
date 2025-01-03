package system.ui.panels;

import components.table.Row;
import system.objects.Cart;
import system.objects.Product;

public class PanelCounter extends PanelStore{
	private static final long serialVersionUID = 5174091202932674937L;

	public PanelCounter(int counter_no) {
		getUiTop().getTable().removeColumn(8);
		getUiBottom().getTable().removeColumn(8);
	}
	@Override
	public void onLoadAisleFromStore(Product[] products) {
		super.onLoadAisleFromStore(products);
		for(Row row: getUiTop().getTable().getRows()) {
			row.removeCell(8);
		}
	}
	@Override
	public void onLoadCartFromStore(Cart cart) {
		super.onLoadCartFromStore(cart);
		for(Row row: getUiBottom().getTable().getRows()) {
			row.removeCell(8);
		}
	}
}
