package system.ui.panels.actions.descriptive;

import system.objects.Item;

public abstract class ActionPanelItemEdit extends ActionPanelItem{
	private static final long serialVersionUID = -7906100988779555454L;
	private Item item;
	
	public ActionPanelItemEdit(Item item) {
		super("Edit Item");
		setItem(item);
	}
	@Override
	public void onItemOk(Item item) {
		editItemOk(item);
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void editItemOk(Item item) {
		onEditItemOk(item);
	}
	
	public abstract void onEditItemOk(Item item);

}
