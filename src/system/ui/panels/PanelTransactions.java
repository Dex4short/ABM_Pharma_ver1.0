package system.ui.panels;

import components.table.Row;
import oop.Decimal;
import oop.Percentage;
import oop.Product;
import oop.Transaction;
import system._default_.Transactions;
import system.ui.UI3;
import system.ui.bars.BarFieldOrder;
import system.ui.bars.BarFieldTransaction;
import system.ui.buttons.ButtonReturnOrder;
import system.ui.buttons.printings.ButtonPrintOrders;
import system.ui.buttons.printings.ButtonPrintTransactions;
import system.ui.panels.searches.SearchPanelOrders;
import system.ui.panels.searches.SearchPanelTransactions;
import system.ui.tables.TableOrders;
import system.ui.tables.TableTransactions;

public class PanelTransactions extends UI3 implements Transactions{
	private static final long serialVersionUID = -4728899875964533207L;
	
	private TableTransactions table_transactions;
	private BarFieldTransaction bar_field_transaction;

	private ButtonReturnOrder btn_return_order;
	private TableOrders table_orders;
	private BarFieldOrder bar_field_order;

	public PanelTransactions() {
		//top UI
		{
			SearchPanelTransactions search_panel_customer = new SearchPanelTransactions() {
				private static final long serialVersionUID = -3060789857476437659L;
				@Override
				public void onSearch(String category, String word) { searchCustomersFromTransactions(category, word); }
			};
			
			ButtonPrintTransactions btn_print_transactions = new ButtonPrintTransactions();
			
			table_transactions = new TableTransactions() {
				private static final long serialVersionUID = 8004484913942693371L;
				@Override
				public void onSelectRow(Row row) {
					getCustomerOrdersFromTransactions(getSelectedTransaction());
				}
				@Override
				public void onPointRow(Row row) {
					search_panel_customer.closeSearchFilter();
				}
			};
			table_transactions.setCheckBoxesEnabled(false);
			
			bar_field_transaction = new BarFieldTransaction();
			getUiTop().setSearchPanel(search_panel_customer);
			getUiTop().addButton(btn_print_transactions);
			getUiTop().setTable(table_transactions);
			getUiTop().setBarFields(bar_field_transaction);
		}
		
		//bottom UI
		{
			SearchPanelOrders search_panel_orders = new SearchPanelOrders() {
				private static final long serialVersionUID = -3060789857476437659L;
				@Override
				public void onSearch(String category, String word) {
					
				}
			};
			ButtonPrintOrders btn_print_orders = new ButtonPrintOrders();
			
			btn_return_order = new ButtonReturnOrder() {
				private static final long serialVersionUID = 3251937345183043930L;
				@Override
				public void onReturnOrder() { returnOrder(); }
			};
			
			table_orders = new TableOrders() {
				private static final long serialVersionUID = 6144003418942793339L;
				@Override
				public void onSelectRow(Row row) {
					boolean hasSelected = table_orders.getSelectedRows().length == 1;
					if(hasSelected) btn_return_order.setEnabled(true);
				}
				@Override
				public void onPointRow(Row row) {
					search_panel_orders.closeSearchFilter();
				}
			};
			
			bar_field_order = new BarFieldOrder();
			getUiBottom().setSearchPanel(search_panel_orders);
			getUiBottom().addButton(btn_print_orders);
			getUiBottom().addButton(btn_return_order);
			getUiBottom().setTable(table_orders);
			getUiBottom().setBarFields(bar_field_order);
		}
	}
	@Override
	public void onSearchCustomersFromTransactions(Product[] products) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPrintCustomersFromTransactions() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPrintCustomerOrdersFromTransactions() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onReturnCustomerOrderFromTransactions() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onGetCustomerOrdersFromTransactions(Transaction transction) {
		table_orders.removeAllOrders();
		table_orders.addOrders(transction.getCart().getOrders());
		bar_field_order.calculateDiscount_and_TotalNetAmount(transction);
	}
	@Override
	public void onloadAllFromTransactions(Transaction transactions[]) {
		btn_return_order.setEnabled(false);
		
		table_transactions.removeAllTransactions();
		table_transactions.addTransactions(transactions);
		bar_field_transaction.calculateTotalCostAmount_and_Profit(transactions);
		
		table_orders.removeAllOrders();
		bar_field_order.setDiscount(new Percentage());
		bar_field_order.setTotalNetAmount(new Decimal());
	}
}
