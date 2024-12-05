package oop;

public class Cart {
	private int cart_no;
	private Order orders[];
	
	public Cart(int cart_no, Order oredrs[]) {
		setCartNo(cart_no);
		setOrders(oredrs);
	}
	public int getCartNo() {
		return cart_no;
	}
	public void setCartNo(int cart_no) {
		this.cart_no = cart_no;
	}
	public Order[] getOrders() {
		return orders;
	}
	public void setOrders(Order orders[]) {
		this.orders = orders;
	}
}
