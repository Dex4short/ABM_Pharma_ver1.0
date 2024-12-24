package system.ui.panels;

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

	public PanelTransactions() {
		SearchPanelTransactions search_customer = new SearchPanelTransactions() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) { searchCustomersFromTransactions(category, word); }
		};
		ButtonPrintTransactions btn_print_transactions = new ButtonPrintTransactions();
		
		table_transactions = new TableTransactions();
		
		BarFieldTransaction bar_field_transaction = new BarFieldTransaction();
		getUiTop().setSearchPanel(search_customer);
		getUiTop().addButton(btn_print_transactions);
		getUiTop().setTable(table_transactions);
		getUiTop().setBarFields(bar_field_transaction);
		
		SearchPanelOrders search_orders = new SearchPanelOrders() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				
			}
		};
		ButtonPrintOrders btn_print_orders = new ButtonPrintOrders();
		ButtonReturnOrder btn_return_order = new ButtonReturnOrder() {
			private static final long serialVersionUID = 3251937345183043930L;
			@Override
			public void onReturnOrder() {
				
			}
		};
		TableOrders table_orders = new TableOrders();
		BarFieldOrder bar_field_order = new BarFieldOrder();
		getUiBottom().setSearchPanel(search_orders);
		getUiBottom().addButton(btn_print_orders);
		getUiBottom().addButton(btn_return_order);
		getUiBottom().setTable(table_orders);
		getUiBottom().setBarFields(bar_field_order);
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
	public void onGetCustomerListFromTransactions() {
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
	public void onGetCustomerOrdersFromTransactions() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onloadAllFromTransactions(Transaction transactions[]) {
		table_transactions.removeAllTransactions();
		table_transactions.addTransactions(transactions);
	}
}
