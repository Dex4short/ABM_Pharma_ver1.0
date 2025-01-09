package system.ui.buttons.listing_item;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Item;
import system.ui.Window;
import system.ui.panels.actions.descriptive.ActionPanelItemEdit;

public abstract class ButtonEditItem extends Button implements ActionListener{
	private static final long serialVersionUID = 2805362815419509096L;

	public ButtonEditItem() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("pencil.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelItemEdit(getSelectedItem()) {
			private static final long serialVersionUID = 400953559164136416L;
			@Override
			public void onEditItemOk(Item item) {
				editItem(item); 
			}
		}, 600, 400);
	}
	public void editItem(Item item) { 
		onEditItem(item); 
	}
	
	public abstract void onEditItem(Item item);
	public abstract Item getSelectedItem();

}
