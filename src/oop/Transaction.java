package oop;

public class Transaction {
	private int trans_id;
	private Counter counter;
	private Cart cart;
	private Customer customer;
	private String tin_no, terms;
	private Date date;
	private Percentage discount;
	private Decimal cost_amount, profit, total_amount;
	
	public Transaction(int trans_id, Counter counter, Cart cart, Customer customer, String tin_no, Date date, String terms, Decimal cost_amount, Decimal profit, Percentage discount, Decimal total_amoun) {
		setTransId(trans_id);
		setTinNo(tin_no);
		setDate(date);
		setTerms(terms);
		setCostAmount(cost_amount);
		setProfit(profit);
		setDiscount(discount);
		setTotalAmount(total_amount);
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
	public Decimal getProfit() {
		return profit;
	}
	public void setProfit(Decimal profit) {
		this.profit = profit;
	}
	public Decimal getTotalAmount() {
		return total_amount;
	}
	public void setTotalAmount(Decimal total_amount) {
		this.total_amount = total_amount;
	}
}
