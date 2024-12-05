package oop;

public class Order {
	private int order_no;
	private Product product;
	
	public Order(int order_no, Product product) {
		setOrderNo(order_no);
		setProduct(product);
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
}