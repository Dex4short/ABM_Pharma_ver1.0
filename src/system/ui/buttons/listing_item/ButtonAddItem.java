package system.ui.buttons.listing_item;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.objects.Item;
import system.ui.Window;
import system.ui.panels.actions.descriptive.ActionPanelItemAdd;

public abstract class ButtonAddItem extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddItem() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Item");
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelItemAdd() {
			private static final long serialVersionUID = 2483456328408759972L;
			@Override
			public void onAddItemOk(Item item) {
				addItem(item);				
			}
		}, 600, 400);
	}
	public void addItem(Item item) {
		onAddItem(item);
	}
	
	public abstract void onAddItem(Item item);
	
}
