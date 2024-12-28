package system.ui.buttons.ordering;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import components.Button;
import system.objects.Packaging;
import system.ui.Window;
import system.ui.panels.actions.selling.ActionPanelPackageAddToCart;

public abstract class ButtonAddToCart extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;
	
	public ButtonAddToCart() {
		setArc(20);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setText("+ Add to Cart");
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelPackageAddToCart(getPackaging()) {
			private static final long serialVersionUID = -7924751815416121087L;
			@Override
			public void onPackageAddToCartOk(Packaging extracted_packs[], Packaging sub_pack) {
				addToCartOk(extracted_packs, sub_pack);
			}
		}, 300, 300);
	}
	public void addToCartOk(Packaging extracted_packs[], Packaging sub_pack) {
		onAddToCartOk(extracted_packs, sub_pack);
	}
	
	public abstract void onAddToCartOk(Packaging extracted_packs[], Packaging sub_pack);
	public abstract Packaging getPackaging();
	
}
