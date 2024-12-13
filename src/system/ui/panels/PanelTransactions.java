package system.ui.panels;

import oop.Product;
import system._default_.Transactions;
import system.ui.UI3;
import system.ui.bars.BarFieldOrder;
import system.ui.bars.BarFieldTransaction;
import system.ui.buttons.ButtonPrintOrders;
import system.ui.buttons.ButtonPrintTransactions;
import system.ui.buttons.ButtonReturnOrder;
import system.ui.panels.searches.SearchPanelOrders;
import system.ui.panels.searches.SearchPanelTransactions;
import system.ui.tables.TableOrders;
import system.ui.tables.TableTransactions;

public class PanelTransactions extends UI3 implements Transactions{
	private static final long serialVersionUID = -4728899875964533207L;

	public PanelTransactions() {
		SearchPanelTransactions search_customer = new SearchPanelTransactions() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				onSearchFromTransactionCustomers(null);
			}
		};
		ButtonPrintTransactions btn_print_transactions = new ButtonPrintTransactions();
		TableTransactions table_transactions = new TableTransactions();
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
