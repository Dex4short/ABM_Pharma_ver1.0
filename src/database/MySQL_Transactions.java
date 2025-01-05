package database;

import java.math.BigDecimal;

import system.enumerators.CustomerState;
import system.objects.Customer;
import system.objects.Date;
import system.objects.Decimal;
import system.objects.Percentage;
import system.objects.Transaction;

public class MySQL_Transactions {
	public static final String
	table_name = "transactions",
	table_columns[] = {"trans_id", "counter_no", "cart_no", "cust_id", "tin_no", "transaction_date", "terms", "cost_amount", "discount", "total_netAmount", "profit"};

	public static Transaction instertTransaction(Transaction transaction) {		
		if(transaction.getCustomer().getCustomerState() == CustomerState.Unlisted) MySQL_Customer.insertCustomer(transaction.getCustomer());

		transaction.setTransId(MySQL.nextUID("trans_id", table_name));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				transaction.getTransId(),
				transaction.getCounter().getCounterNo(),
				transaction.getCart().getCartNo(),
				transaction.getCustomer().getCustomerId(),
				transaction.getTinNo(),
				transaction.getDate().toSQLDate(),
				transaction.getTerms(),
				transaction.getCostAmount().toBigDecimal(),
				transaction.getDiscount().toString(),
				transaction.getTotalNetAmount().toBigDecimal(),
				transaction.getProfit().toBigDecimal()
			}
		);
		
		int
		counter_no = transaction.getCounter().getCounterNo(),
		cart_no = MySQL_Cart.insertCart(counter_no).getCartNo();//create next cart
		
		transaction.getCounter().setCurrentCartNo(cart_no);//set next cart no
		MySQL_Counter.updateCounter(transaction.getCounter());//update current counter for the next cart no
		
		return transaction;
	}
	public static Transaction selectFromTransaction(int trans_id) {
		return selectTransactions("where trans_id=" + trans_id)[0];
	}
	public static Transaction[] selectFromTransactions(Customer customer) {
		return selectTransactions("where cust_id=" + customer.getCustomerId());
	}
	public static Transaction[] selectTransactions() {
		return selectTransactions("");
	}
	public static Transaction[] selectTransactions(String condition) {
		Object result[][] = MySQL.select(table_columns, table_name, condition);
		
		Transaction transactions[] = new Transaction[result.length];
		for(int t=0; t<transactions.length; t++) {
			transactions[t] = new Transaction(
				(int)result[t][0],
				MySQL_Counter.selectCounter((int)result[t][1]), 
				MySQL_Cart.selectCart((int)result[t][2], (int)result[t][1]),
				MySQL_Customer.selectCustomer((int)result[t][3]),
				(String)result[t][4],
				Date.parseDate((java.sql.Date)result[t][5]),
				(String)result[t][6],
				new Decimal((BigDecimal)result[t][7]),
				new Percentage((String)result[t][8]), 
				new Decimal((BigDecimal)result[t][9]),
				new Decimal((BigDecimal)result[t][10])
			);
		}
		return transactions;
	}
	public static Transaction[] selectTransactions(String key, String word) {
		String 
		joins = " transactions as t"
				+ "	join "
				+ "	customers as m ",
		on = " on t.cust_id=m.cust_id ",
		where = " where " + key + word;
		Object results[][] = MySQL.select(new String[] {"t.trans_id"}, joins, on + where);
		
		Transaction transactions[] = new Transaction[results.length];
		for(int t=0; t<transactions.length; t++) {
			transactions[t] = selectFromTransaction((int)results[t][0]);
		}
		
		return transactions;
	}
}
