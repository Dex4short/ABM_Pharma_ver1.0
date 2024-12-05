package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import components.Panel;
import oop.Product;
import oop.interfaces.Theme;
import system._default_.Transactions;
import system.ui.bars.BarFieldOrder;
import system.ui.bars.BarFieldTransaction;
import system.ui.buttons.ButtonPrintOrders;
import system.ui.buttons.ButtonPrintTransactions;
import system.ui.buttons.ButtonReturnOrder;
import system.ui.search_panels.SearchPanelTransactionCustomer;
import system.ui.tables.TableOrders;
import system.ui.tables.TableTransactions;

public class PanelTransactions extends Panel implements Transactions,Theme{
	private static final long serialVersionUID = -4728899875964533207L;

	public PanelTransactions() {
		setOpaque(false);
		setLayout(new GridLayout(2, 1));

		add(transactionsPanel());
		add(ordersPanel());
		
		
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
	
	private Panel transactionsPanel() {
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout(5,5));
		
		Panel header = new Panel();
		header.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		
		Panel header_left = new Panel();
		header_left.setLayout(new FlowLayout(FlowLayout.LEFT));
		header.add(header_left, BorderLayout.WEST);
		
		Panel header_right = new Panel();
		header_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.add(header_right, BorderLayout.EAST);
		
		SearchPanelTransactionCustomer search_customer = new SearchPanelTransactionCustomer() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				onSearchFromTransactionCustomers(null);
			}
		};
		header_left.add(search_customer);
		
		ButtonPrintTransactions btn_print_transactions = new ButtonPrintTransactions();
		header_right.add(btn_print_transactions);
		
		TableTransactions table_transactions = new TableTransactions();
		panel.add(table_transactions, BorderLayout.CENTER);
		
		BarFieldTransaction bar_accountancy_transaction = new BarFieldTransaction();
		panel.add(bar_accountancy_transaction, BorderLayout.SOUTH);
		
		return panel;
	}
	private Panel ordersPanel() {
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout(5,5));
		
		Panel header = new Panel();
		header.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		
		Panel header_left = new Panel();
		header_left.setLayout(new FlowLayout(FlowLayout.LEFT));
		header.add(header_left, BorderLayout.WEST);
		
		Panel header_right = new Panel();
		header_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.add(header_right, BorderLayout.EAST);
		
		SearchPanelTransactionCustomer search_orders = new SearchPanelTransactionCustomer() {
			private static final long serialVersionUID = -3060789857476437659L;
			@Override
			public void onSearch(String category, String word) {
				onSearchFromTransactionCustomers(null);
			}
		};
		header_left.add(search_orders);
		
		ButtonPrintOrders btn_print_orders = new ButtonPrintOrders();
		header_right.add(btn_print_orders);
		
		ButtonReturnOrder btn_return_orders = new ButtonReturnOrder() {
			private static final long serialVersionUID = 3251937345183043930L;
			@Override
			public void onReturnOrder() {
				// TODO Auto-generated method stub
			}
		};
		header_right.add(btn_return_orders);
		
		TableOrders table_orders = new TableOrders();
		panel.add(table_orders, BorderLayout.CENTER);
		
		BarFieldOrder bar_accountancy_order = new BarFieldOrder();
		panel.add(bar_accountancy_order, BorderLayout.SOUTH);
		
		return panel;
	}
}
