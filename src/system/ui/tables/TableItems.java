package system.ui.tables;

import components.table.Cell;
import components.table.Row;
import components.table.Table;
import system.objects.Item;

public abstract class TableItems extends Table{
	private static final long serialVersionUID = -3435359604725778537L;
	public static final String fields[] = {"Item No.", "Description", "Brand"};

	public TableItems() {
		super(fields);
	}
	@Override
	public void onSelectRow(Row row) {
		selectItem(null);
	}
	@Override
	public void onPointRow(Row row) {
		
	}
	public void selectItem(Item item) {
		onSelectItem(null);
	}
	
	public abstract void onSelectItem(Item item);
	
	public static class ItemRow extends Row{
		private static final long serialVersionUID = -2966732571638242794L;

		public ItemRow(Item item) {
			super(new Cell[] {
				new Cell(),
				new Cell(),
				new Cell(),
			});
		}
		
	}
		
}
