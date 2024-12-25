package oop;

import oop.enumerations.CounterState;

public class Counter {
	private int counterNo, currentCart_no;
	private Time activeTime;
	private CounterState counterState;
	
	public Counter() {
		setCounterNo(0);
		setActiveTime(new Time());
		setCounterState(CounterState.CLOSE);
		setCurrentCartNo(0);
	}	
	public Counter(int counter_no) {
		setCounterNo(counter_no);
		setActiveTime(new Time());
		setCounterState(CounterState.CLOSE);
		setCurrentCartNo(0);
	}
	public Counter(int counterNo, Time activeTime, CounterState counterState, int currentCart_no) {
		setCounterNo(counterNo);
		setActiveTime(activeTime);
		setCounterState(counterState);
		setCurrentCartNo(currentCart_no);
	}
	@Override
	public String toString() {
		return 
			"Counter:\n" + 
			"\t" + getCounterNo() + "\n" +
			"\t" + getActiveTime() + "\n" +
			"\t" + getCounterState() + "\n" +
			"\t" + getCurrentCartNo() + "\n"
		;
	}
	public int getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(int counterNo) {
		this.counterNo = counterNo;
	}
	public Time getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Time activeTime) {
		this.activeTime = activeTime;
	}
	public CounterState getCounterState() {
		return counterState;
	}
	public void setCounterState(CounterState counterState) {
		this.counterState = counterState;
	}
	public int getCurrentCartNo() {
		return currentCart_no;
	}
	public void setCurrentCartNo(int currentCart_no) {
		this.currentCart_no = currentCart_no;
	}
}
