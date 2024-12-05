package system.ui.panels;

import oop.Product;
import system._default_.Transactions;
import system.ui.UI3;
import system.ui.bars.BarFieldOrder;
import system.ui.bars.BarFieldTransaction;
import system.ui.buttons.ButtonPrintOrders;
import system.ui.buttons.ButtonPrintTransactions;
import system.ui.buttons.ButtonReturnOrder;
import system.ui.search_panels.SearchPanelTransactionCustomer;
import system.ui.tables.TableOrders;
import system.ui.tables.TableTransactions;

public class PanelTransactions extends UI3 implements Transactions{
	private static final long serialVersionUID = -4728899875964533207L;

	public PanelTransactions() {
		SearchPanelTransactionCustomer search_customer = new SearchPanelTransactionCustomer() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				onSearchFromTransactionCustomers(null);
			}
		};
		ButtonPrintTransactions btn_print_transactions = new ButtonPrintTransactions();
		TableTransactions table_transactions = new TableTransactions();
		BarFieldTransaction bar_accountancy_transaction = new BarFieldTransaction();
		
		getUiTop().setSearchPanel(search_customer);
		getUiTop().addButton(btn_print_transactions);
		getUiTop().setTable(table_transactions);
		getUiTop().setBarFields(bar_accountancy_transaction);
		
		SearchPanelTransactionCustomer search_orders = new SearchPanelTransactionCustomer() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				onSearchFromTransactionCustomers(null);
			}
		};
		ButtonPrintOrders btn_print_orders = new ButtonPrintOrders();
		ButtonReturnOrder btn_return_orders = new ButtonReturnOrder() {
			private static final long serialVersionUID = 3251937345183043930L;
			@Override
			public void onReturnOrder() {
				// TODO Auto-generated method stub
			}
		};
		TableOrders table_orders = new TableOrders();
		BarFieldOrder bar_accountancy_order = new BarFieldOrder();
		
		getUiBottom().setSearchPanel(search_orders);
		getUiBottom().addButton(btn_print_orders);
		getUiBottom().addButton(btn_return_orders);
		getUiBottom().setTable(table_orders);
		getUiBottom().setBarFields(bar_accountancy_order);
	}
	@Override
	public void onSearchFromTransactionCustomers(Product[] products) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPrintCustomersFromTransaction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onGetCustomerListFromTransaction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onCalculateTotalCostAmount() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onCalculateTotalProfit() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPrintCustomerOrdersFromTransaction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onReturnCustomerOrderFromTransaction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onGetCustomerOrdersFromTransaction() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onCalculateCostAmount() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onCalculateProfit() {
		// TODO Auto-generated method stub
	}
}
