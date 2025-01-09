package system.ui.panels.actions.descriptive;

import components.panels.ActionPanel;
import system.objects.Item;
import system.ui.Window;

public abstract class ActionPanelItem extends ActionPanel{
	private static final long serialVersionUID = 7220071635534709051L;

	public ActionPanelItem(String title) {
		super(title);
		
	}
	@Override
	public void onOk() {
		itemOk();
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void itemOk() {
		onItemOk(new Item());
	}
	
	public abstract void onItemOk(Item item);
	
}
