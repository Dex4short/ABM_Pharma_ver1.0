package oop.structures;

import java.util.ArrayList;

public class Stack<T> {
	private ArrayList<T> types;
	
	public Stack() {
		types = new ArrayList<T>();
	}
	public void push(T type) {
		types.add(type);
	}
	public T pop() {
		T type = types.get(types.size() - 1);
		types.remove(type);
		return type;
	}
	public int size() {
		return types.size();
	}
	public T top() {
		return types.get(types.size() - 1);
	}
	public boolean isEmpty() {
		return types.isEmpty();
	}
}
