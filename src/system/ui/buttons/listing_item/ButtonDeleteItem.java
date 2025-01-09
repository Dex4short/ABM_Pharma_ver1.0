package system.ui.buttons.listing_item;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import components.Button;
import components.panels.DialogPanel;
import res.Resource;
import system.objects.Item;
import system.ui.Window;

public abstract class ButtonDeleteItem extends Button implements ActionListener{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDeleteItem() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));

		setIcon(new ImageIcon(Resource.get("delete.png")));
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.getStackPanel().pushPanel(new DialogPanel("Delete Item") {
			private static final long serialVersionUID = -5908193837480508966L;
			{
				setText("Delete selected item(s)?");
			}
			@Override
			public void onOk() {
				deleteItem();
				Window.getStackPanel().popPanel(this);
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	public void deleteItem() {
		Window.load(() -> {
			
		}, "Deleting From the list...");
	}
	
	public abstract void onDeleteItem(Item item);
	public abstract Item[] getSelectedItems();
	
}
