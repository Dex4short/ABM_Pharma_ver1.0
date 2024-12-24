package oop;

import java.util.ArrayList;

public class Cart {
	private int cart_no, counter_no, order_no;
	private ArrayList<Order> orders;
	
	public Cart(int cart_no, int counter_no, int order_no, Order orders[]) {
		this.orders = new ArrayList<Order>();
		
		setCartNo(cart_no);
		setCounterNo(counter_no);
		setOrderNo(order_no);
		setOrders(orders);
	}
	@Override
	public String toString() {
		String info = 
			"Cart:\n" +
			"\t" + getCartNo() + "\n" +
			"\t" + getCounterNo() + "\n" +
			"\t" + getOrderNo() + "\n" +
			"\t oredrs: "
		;
		
		for(int o=0; o<orders.size(); o++) {
			info += orders.get(o) + ", ";
		}
		info += "\n";
		
		return info;
	}
	public int getCartNo() {
		return cart_no;
	}
	public void setCartNo(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getCounterNo() {
		return counter_no;
	}
	public void setCounterNo(int counter_no) {
		this.counter_no = counter_no;
	}
	public int getOrderNo() {
		return order_no;
	}
	public void setOrderNo(int order_no) {
		this.order_no = order_no;
	}
	public Order getOrder(int index) {
		return orders.get(index);
	}
	public Order[] getOrders() {
		return orders.toArray(new Order[orders.size()]);
	}
	public void setOrder(int index, Order order) {
		orders.set(index, order);
	}
	public void setOrders(Order orders[]) {
		this.orders.clear();
		for(Order order: orders) {
			this.orders.add(order);
		}
	}
	public void addOrder(Order order) {
		orders.add(order);
	}
	public void addOrders(Order orders[]) {
		for(Order order: orders) {
			this.orders.add(order);
		}
	}
	public void removeOrder(int index) {
		orders.remove(index);
	}
	public void removeOrder(Order order) {
		orders.remove(order);
	}
	public void clearOrders() {
		orders.clear();
	}
}
