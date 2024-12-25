package system._default_;

import database.MySQL_Transactions;
import oop.Product;
import oop.Transaction;

public interface Transactions {
	
	public default void searchCustomersFromTransactions(String category, String word) {
		
	}
	public default void printCustomersFromTransactions() {
		
	}
	public default void printCustomerOrdersFromTransactions() {
		
	}
	public default void returnCustomerOrderFromTransactions() {
		
	}
	public default void getCustomerOrdersFromTransactions(Transaction transction) {
		onGetCustomerOrdersFromTransactions(transction);
	}
	public default void loadAllFromTransactions() {
		Transaction transactions[] = MySQL_Transactions.selectTransactions();
		onloadAllFromTransactions(transactions);
	}
	
	public void onSearchCustomersFromTransactions(Product products[]);
	public void onPrintCustomersFromTransactions();
	public void onPrintCustomerOrdersFromTransactions();
	public void onReturnCustomerOrderFromTransactions();
	public void onGetCustomerOrdersFromTransactions(Transaction transction);
	public void onloadAllFromTransactions(Transaction transactions[]);
	
	
}
