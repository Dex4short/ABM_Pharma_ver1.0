package system.ui.cells;


import oop.Packaging;
import oop.Product;
import system.ui.buttons.ordering.ButtonAddToCart;

public abstract class CellButtonAddToCart extends CellButton{
	private static final long serialVersionUID = -752158914266118531L;

	public CellButtonAddToCart(Product product) {
		setButton(new ButtonAddToCart() {
			private static final long serialVersionUID = -1834632943439364017L;
			
			@Override
			public void onAddToCartOk(Packaging extracted_packs[], Packaging sub_pack) {
				addToCart(extracted_packs, sub_pack);
			}
			@Override
			public Packaging getPackaging() {
				return product.getPackaging();
			}
		});
	}
	public void addToCart(Packaging extracted_packs[], Packaging sub_pack) {
		onAddToCart(extracted_packs, sub_pack);
	}
	
	public abstract void onAddToCart(Packaging extracted_packs[], Packaging sub_pack);
	
}
