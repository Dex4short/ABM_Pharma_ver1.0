package system.ui.buttons.ordering;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import components.Button;
import system.objects.Packaging;
import system.ui.Window;
import system.ui.panels.actions.selling.ActionPanelPackageRemoveFromCart;

public abstract class ButtonRemoveFromCart extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;
	
	public ButtonRemoveFromCart() {
		setArc(20);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setText("- Remove");
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelPackageRemoveFromCart(getPackaging()) {
			private static final long serialVersionUID = -7924751815416121087L;
			@Override
			public void onPackageRemoveFromCartOk(Packaging extracted_packs[], Packaging sub_pack) {
				removeFromCartOk(extracted_packs, sub_pack);
			}
		}, 300, 300);
	}
	public void removeFromCartOk(Packaging extracted_packs[], Packaging sub_pack) {
		onRemoveFromCartOk(extracted_packs, sub_pack);
	}
	
	public abstract void onRemoveFromCartOk(Packaging extracted_packs[], Packaging sub_pack);
	public abstract Packaging getPackaging();
	
}
