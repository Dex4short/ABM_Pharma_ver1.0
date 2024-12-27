package system._default_;

import database.MySQL_Products;
import database.MySQL_Transactions;
import system.enumerators.ProductCondition;
import system.objects.Order;
import system.objects.Product;
import system.objects.Transaction;

public interface Transactions {
	
	public default Transaction selectFromTransactions() {
		return onSelectFromTransactions();
	}
	public default Order[] selectCustomerOrdersFromTransactions() {
		return onSelectCustomerOrdersFromTransactions();
	}
	public default void searchCustomersFromTransactions(String category, String word) {
		
	}
	public default void printCustomersFromTransactions() {
		
	}
	public default void printCustomerOrdersFromTransactions() {
		
	}
	public default void returnCustomersOrderFromTransactions(Order orders[]) {
		for(Order order: orders) {
			order.getProduct().setProduct_condition(ProductCondition.RETURNED);
			MySQL_Products.updateProduct(order.getProduct());
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
	public Order[] onSelectCustomerOrdersFromTransactions();
	public void onSearchCustomersFromTransactions(Product products[]);
	public void onPrintCustomersFromTransactions();
	public void onPrintCustomerOrdersFromTransactions();
	public void onReturnCustomerOrderFromTransactions(Order oredrs[]);
	public void onGetCustomerOrdersFromTransactions(Transaction transction);
	public void onloadAllFromTransactions(Transaction transactions[]);
	
	
}
