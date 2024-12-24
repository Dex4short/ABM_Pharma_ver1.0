package oop;

public class Transaction {
	private int trans_id;
	private Counter counter;
	private Cart cart;
	private Customer customer;
	private String tin_no, terms;
	private Date date;
	private Percentage discount;
	private Decimal cost_amount, total_net_amount, profit;
	
	public Transaction(int trans_id, Counter counter, Cart cart, Customer customer, String tin_no, Date date, String terms, Decimal cost_amount, Percentage discount, Decimal total_net_amount, Decimal profit) {
		setTransId(trans_id);
		setCounter(counter);
		setCart(cart);
		setCustomer(customer);
		setTinNo(tin_no);
		setDate(date);
		setTerms(terms);
		setCostAmount(cost_amount);
		setDiscount(discount);
		setTotalNetAmount(total_net_amount);
		setProfit(profit);
	}
	@Override
	public String toString() {
		return 
			"Transaction: \n" +
			"\t" + getTransId() + "\n" +
			"\t" + getCounter().toString() + "\n" +
			"\t" + getCart().toString() + "\n" +
			"\t" + getCustomer().toString() + "\n" +
			"\t" + getTinNo() + "\n" +
			"\t" + getDate() + "\n" +
			"\t" + getTerms() + "\n" +
			"\t" + getCostAmount() + "\n" +
			"\t" + getDiscount() + "\n" +
			"\t" + getTotalNetAmount() + "\n" +
			"\t" + getProfit() + "\n" 
		;
	}
	public int getTransId() {
		return trans_id;
	}
	public void setTransId(int trans_id) {
		this.trans_id = trans_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Counter getCounter() {
		return counter;
	}
	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	public String getTinNo() {
		return tin_no;
	}
	public void setTinNo(String tin_no) {
		this.tin_no = tin_no;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Percentage getDiscount() {
		return discount;
	}
	public void setDiscount(Percentage discount) {
		this.discount = discount;
	}
	public Decimal getCostAmount() {
		return cost_amount;
	}
	public void setCostAmount(Decimal cost_amount) {
		this.cost_amount = cost_amount;
	}
	public Decimal getTotalNetAmount() {
		return total_net_amount;
	}
	public void setTotalNetAmount(Decimal total_net_amount) {
		this.total_net_amount = total_net_amount;
	}
	public Decimal getProfit() {
		return profit;
	}
	public void setProfit(Decimal profit) {
		this.profit = profit;
	}
}
