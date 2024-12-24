package oop;

public class Order{
	private int order_no;
	private Product product;
	private Decimal net_amount;
	
	public Order(int order_no, Product product, Decimal net_amount) {
		setOrderNo(order_no);
		setProduct(product);
		setNetAmount(net_amount);
	}
	@Override
	public String toString() {
		return 
			"Order:\n" +
			"\t" + getOrderNo() + "\n" +
			"\t" + getProduct().toString() + "\n" +
			"\t" + getNetAmount() + "\n"
		;
	}
	public int getOrderNo() {
		return order_no;
	}
	public void setOrderNo(int order_no) {
		this.order_no = order_no;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Decimal getNetAmount() {
		return net_amount;
	}
	public void setNetAmount(Decimal net_amount) {
		this.net_amount = net_amount;
	}
}