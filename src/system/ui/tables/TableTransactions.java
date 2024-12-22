package system.ui.tables;

import components.table.Cell;
import components.table.Row;
import components.table.Table;
import oop.Transaction;
import system.ui.cells.CellLabel;
import system.ui.cells.CellLabelDecimal;

public class TableTransactions extends Table{
	private static final long serialVersionUID = 4737286913516237032L;
	public static final String fields[] = {"Customer Name", "TIN No.", "Address", "Date", "Terms", "Cost Amount", "Profit"};

	public TableTransactions() {
		super(fields);
	}
	public void addTransaction(Transaction transaction) {
		addRow(new TransactionRow(transaction));
	}
	public void addTransaction(Transaction transactions[]) {
		TransactionRow rows[] = new TransactionRow[transactions.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new TransactionRow(transactions[r]);
		}
		addRows(rows);
	}
	public void removeProduct(int n) {
		removeRow(n);
	}
	public void removeProduct(Transaction transaction) {
		int n=0;
		for(Transaction t: getTransactions()) {
			if(t == transaction) {
				removeProduct(n);
				n++;
			}
		}
	}
	public void removeProducts(int p[]) {
		Row rows[] = new Row[p.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = getRow(p[r]);
		}
		for(Row row: rows) {
			remove(row);
		}
	}
	public void removeAllProducts() {
		removeAllRows();
	}
	public Transaction getTransaction(int n) {
		return ((TransactionRow)getRow(n)).getTransaction();
	}
	public Transaction[] getTransactions() {
		Transaction transactions[] = new Transaction[getTransactionCount()];
		for(int t=0; t<transactions.length; t++) {
			transactions[t] = getTransaction(t);
		}
		return transactions;
	}
	public int getTransactionCount() {
		return getRowCount();
	}
	
	private class TransactionRow extends Row{
		private static final long serialVersionUID = 2778698924837158048L;
		private Transaction transaction;

		public TransactionRow(Transaction transaction) {
			super(new Cell[] {
					new CellLabel(transaction.getCustomer().getCustomerName()),
					new CellLabel(transaction.getTinNo()),
					new CellLabel(transaction.getCustomer().getAddress()),
					new CellLabel(transaction.getDate().toString()),
					new CellLabel(transaction.getTerms()),
					new CellLabelDecimal(transaction.getCostAmount()),
					new CellLabelDecimal(transaction.getProfit())
			});
			setTransaction(transaction);
		}
		public Transaction getTransaction() {
			return transaction;
		}
		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}
	}
}
