package system._default_;

import oop.Product;

public interface Transactions {
	
	public default void searchCustomersFromTransaction(String category, String word) {
		
	}
	public default void printCustomersFromTransaction() {
		
	}
	public default void getCustomerListFromTransaction() {
		
	}
	public default void printCustomerOrdersFromTransaction() {
		
	}
	public default void returnCustomerOrderFromTransaction() {
		
	}
	public default void getCustomerOrdersFromTransaction() {
		
	}
	public default void calculateCostAmount() {
		
	}
	public default void calculateProfit() {
		
	}
	
	public void onSearchFromTransactionCustomers(Product products[]);
	public void onPrintCustomersFromTransaction();
	public void onGetCustomerListFromTransaction();
	public void onCalculateTotalCostAmount();
	public void onCalculateTotalProfit();
	public void onPrintCustomerOrdersFromTransaction();
	public void onReturnCustomerOrderFromTransaction();
	public void onGetCustomerOrdersFromTransaction();
	public void onCalculateCostAmount();
	public void onCalculateProfit();
}
