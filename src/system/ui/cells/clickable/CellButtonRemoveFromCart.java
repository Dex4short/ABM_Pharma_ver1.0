package system.ui.cells.clickable;


import system.objects.Order;
import system.objects.Packaging;
import system.ui.buttons.ordering.ButtonRemoveFromCart;

public abstract class CellButtonRemoveFromCart extends CellButton{
	private static final long serialVersionUID = -752158914266118531L;

	public CellButtonRemoveFromCart(Order order) {
		setButton(new ButtonRemoveFromCart() {
			private static final long serialVersionUID = -1834632943439364017L;
			
			@Override
			public void onRemoveFromCartOk(Packaging extracted_packs[], Packaging sub_pack) {
				removeFromCart(extracted_packs, sub_pack);
			}
			@Override
			public Packaging getPackaging() {
				return order.getProduct().getPackaging();
			}
		});
	}
	public void removeFromCart(Packaging extracted_packs[], Packaging sub_pack) {
		onRemoveFromCart(extracted_packs, sub_pack);
	}
	
	public abstract void onRemoveFromCart(Packaging extracted_packs[], Packaging sub_pack);
	
}
