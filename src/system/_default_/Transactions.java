package system._default_;

import database.MySQL_Orders;
import database.MySQL_Transactions;
import system.enumerators.ProductCondition;
import system.objects.Order;
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
	public default void searchFromTransactions(String category, String word) {
		String keys[][] = {
			{"Customer Name", "customer_name"},
			{"TIN No.", "tin_no"},
			{"Address", "address"},
			{"Date", "transactions_date"},
			{"Terms", "terms"},
			{"Cost Amount", "cost_amount"},
			{"Profit", "profit"}
		};
		
		int k;
		for(k=0; k<keys.length; k++) {
			if(keys[k][0].equals(category)) break;
		}

		word = " like '%" + word + "%'";
		
		onSearchFromTransactions(MySQL_Transactions.selectTransactions(keys[k][1], word));
	}
	public default void searchOrdersFromTransactions(int trans_id,String category, String word) {
		String keys[][] = {
			{"Item No.", "item_no"},
			{"Description", "description"},
			{"Lot No.", "lot_no"},
			{"Date Added", "date_added"},
			{"Exp Date", "exp_date"},
			{"Brand", "brand"},
			{"Quantity", "qty"},
			{"UOM", "u.name"},
			{"Cost", "cost"},
			{"Unit Price", "unit_price"},
			{"Discount", "discount"},
			{"Unit Amount", "unit_amount"}
		};
		
		int k;
		for(k=0; k<keys.length; k++) {
			if(keys[k][0].equals(category)) break;
		}

		if(k == 6) {
			word = " = " + word;
		}
		else {
			word = " like '%" + word + "%'";
		}
		
		onSearchOrdersFromTransactions(MySQL_Orders.selectOrders(trans_id, keys[k][1], word));
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
	public void onSearchFromTransactions(Transaction transactions[]);
	public void onSearchOrdersFromTransactions(Order orders[]);
	public void onPrintCustomersFromTransactions();
	public void onPrintCustomerOrdersFromTransactions();
	public void onReturnCustomerOrderFromTransactions(Order oredrs[]);
	public void onGetCustomerOrdersFromTransactions(Transaction transction);
	public void onloadAllFromTransactions(Transaction transactions[]);
	
}
