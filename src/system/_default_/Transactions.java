package system._default_;

import database.MySQL_Orders;
import database.MySQL_Transactions;
import system.enumerators.ProductCondition;
import system.objects.Order;
import system.objects.Product;
import system.objects.Transaction;
import system.printers.ReciptPrinter;
import system.printers.TransactionsPrinter;

public interface Transactions {
	
	public default Transaction selectFromTransactions() {
		return onSelectFromTransactions();
	}
	public default Transaction[] selectAllFromTransactions() {
		return onSelectAllFromTransactions();
	}
	public default Order[] selectCustomerOrdersFromTransactions() {
		return onSelectCustomerOrdersFromTransactions();
	}
	public default void searchCustomersFromTransactions(String category, String word) {
		
	}
	public default void printCustomersFromTransactions(Transaction transactions[]) {
		TransactionsPrinter.printTransactions(transactions);
		onPrintCustomersFromTransactions();
	}
	public default void printCustomerOrdersFromTransactions(Transaction transaction) {
		ReciptPrinter.printReceipt(transaction);
		onPrintCustomerOrdersFromTransactions();
	}
	public default void returnCustomersOrderFromTransactions(Order orders[]) {
		for(Order order: orders) {
			MySQL_Orders.updateOrder(order, ProductCondition.RETURNED);
		}
		onReturnCustomerOrderFromTransactions(orders);
	}
	public default void getCustomerOrdersFromTransactions(Transaction transction) {
		onGetCustomerOrdersFromTransactions(transction);
	}
	public default void loadAllFromTransactions() {
		Transaction transactions[] = MySQL_Transactions.selectTransactions();
		onloadAllFromTransactions(transactions);
	}

	public Transaction onSelectFromTransactions();
	public Transaction[] onSelectAllFromTransactions();
	public Order[] onSelectCustomerOrdersFromTransactions();
	public void onSearchCustomersFromTransactions(Product products[]);
	public void onPrintCustomersFromTransactions();
	public void onPrintCustomerOrdersFromTransactions();
	public void onReturnCustomerOrderFromTransactions(Order oredrs[]);
	public void onGetCustomerOrdersFromTransactions(Transaction transction);
	public void onloadAllFromTransactions(Transaction transactions[]);
	
}
