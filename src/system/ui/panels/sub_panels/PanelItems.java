package system.ui.panels.sub_panels;

import system._default_.Items;
import system.objects.Item;
import system.ui.UI1;
import system.ui.Window;
import system.ui.buttons.listing_item.ButtonAddItem;
import system.ui.buttons.listing_item.ButtonDeleteItem;
import system.ui.buttons.listing_item.ButtonEditItem;
import system.ui.panels.searches.SearchPanelItems;
import system.ui.tables.TableItems;

public class PanelItems extends UI1 implements Items{
	private static final long serialVersionUID = 2587964527195695876L;

	public PanelItems() {
		SearchPanelItems serach_panel_items = new SearchPanelItems() {
			private static final long serialVersionUID = 976777965429808662L;
			@Override
			public void onSearchItem(String category, String word) {}
		};
		setSearchPanel(serach_panel_items);
		
		ButtonDeleteItem btn_deleteItem = new ButtonDeleteItem() {
			private static final long serialVersionUID = 4664095167239675443L;
			@Override
			public void onDeleteItem(Item item) {
				Window.floatMessageAndBeep("Currently not available.");
			}
			@Override
			public Item[] getSelectedItems() { return new Item[0]; }
		};
		addButton(btn_deleteItem);
		
		ButtonEditItem btn_editItem = new ButtonEditItem() {
			private static final long serialVersionUID = 391137076892200944L;
			@Override
			public void onEditItem(Item item) {
				Window.floatMessageAndBeep("Currently not available.");
			}
			
			@Override
			public Item getSelectedItem() { return new Item(); }
		};
		addButton(btn_editItem);
		
		ButtonAddItem btn_addItem = new ButtonAddItem() {
			private static final long serialVersionUID = 1722284191836925215L;
			@Override
			public void onAddItem(Item item) {
				Window.floatMessageAndBeep("Currently not available.");
			}
		};
		addButton(btn_addItem);
		
		TableItems table_items = new TableItems() {
			private static final long serialVersionUID = 6808848758138801684L;
			@Override
			public void onSelectItem(Item item) {}
		};
		setTable(table_items);
		
	}
	@Override
	public void onSearchFromItems(Item[] items) {
		
	}
}
