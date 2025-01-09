package system._default_;

import system.objects.Item;

public interface Items {

	public default void searchFromItems(String category, String word) {
		onSearchFromItems(new Item[0]);
	}
	
	public void onSearchFromItems(Item items[]);
	
}
