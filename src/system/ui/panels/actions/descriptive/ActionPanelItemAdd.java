package system.ui.panels.actions.descriptive;

import system.objects.Item;

public abstract class ActionPanelItemAdd extends ActionPanelItem{
	private static final long serialVersionUID = -7906100988779555454L;
	
	public ActionPanelItemAdd() {
		super("Add Item");
	}
	@Override
	public void onItemOk(Item item) {
		onAddItemOk(item);
	}
	public void addOtemOk(Item item) {
		onAddItemOk(item);
	}
	
	public abstract void onAddItemOk(Item item);

}
