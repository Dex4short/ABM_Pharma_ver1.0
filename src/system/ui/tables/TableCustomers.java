package system.ui.tables;

import components.table.Cell;
import components.table.Row;
import components.table.Table;
import system.objects.Customer;
import system.ui.cells.CellLabel;

public class TableCustomers extends Table{
	private static final long serialVersionUID = 5910798367373357936L;
	public static final String fields[] = {"Customer Name", "Address", "Contact Number", "E-mail", "Company"};
	
	public TableCustomers() {
		super(fields);
	}
	@Override
	public void onSelectRow(Row row) {
		//overridable block
	}
	@Override
	public void onPointRow(Row row) {
		//overridable block
	}
	public void addCustomer(Customer customer) {
		addRow(new CustomerRow(customer));
	}
	public void addCustomers(Customer customers[]) {
		CustomerRow rows[] = new CustomerRow[customers.length];
		for(int r=0; r<rows.length; r++) {
			rows[r] = new CustomerRow(customers[r]);
		}
		addRows(rows);
	}
	public void removeCustomer(int n) {
		removeRow(n);
	}
	public void removeCustomer(Customer customer) {
		int n=0;
		for(Customer c: getCustomers()) {
			if(c == customer) {
				removeCustomer(n);
			}
			n++;
		}
	}
	public void removeCustomers(int c[]) {
		for(int i=0; i<c.length; i++) {
			removeCustomer(c[i]);
		}
	}
	public void removeAllCustomers() {
		removeAllRows();
	}
	public Customer getCustomer(int n) {
		return ((CustomerRow)getRow(n)).getCustomer();
	}
	public Customer[] getCustomers() {
		Customer customers[] = new Customer[getCustomerCount()];
		for(int c=0; c<customers.length; c++) {
			customers[c] = getCustomer(c);
		}
		return customers;
	}
	public int getCustomerCount() {
		return getRowCount();
	}
	public Customer getSelectedCustomer() {
		Row row = getSelectedRow();
		if(row != null)	return ((CustomerRow)getSelectedRow()).getCustomer();
		else return null;
	}
	public Customer[] getSelectedCustomers() {
		Row rows[] = getSelectedRows();
		Customer customers[] = new Customer[rows.length];
		for(int c=0; c<customers.length; c++) {
			customers[c] = ((CustomerRow)rows[c]).getCustomer();
		}
		return customers;
	}
	
	public class CustomerRow extends Row{
		private static final long serialVersionUID = 5577816983654754017L;
		Customer customer;

		public CustomerRow(Customer customer) {
			super(new Cell[] {
				new CellLabel(customer.getCustomerName()),
				new CellLabel(customer.getAddress()),
				new CellLabel(customer.getContactNo()),
				new CellLabel(customer.getEmail()),
				new CellLabel(customer.getCompany())
			});
			
			setCustomer(customer);
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
	}
}
