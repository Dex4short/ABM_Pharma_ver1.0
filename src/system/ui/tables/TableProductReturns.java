package system.ui.tables;

public class TableProductReturns extends TableProducts{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Item No.", "Description", "Lot No.", "Date Added", "Exp Date", "Brand", "Quantity", "UOM", "Cost", "Unit Price", "Discount", "Unit Amount", "Remarks"};

	public TableProductReturns() {
		setColumns(fields);
	}
}
