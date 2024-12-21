package database;

import oop.Counter;
import oop.Time;
import oop.enums.CounterState;

public class MySQL_Counter {
	public static final String 
	table_name = "counter",
	table_columns[] = {"counter_no", "active_time", "counter_state", "currentCart_no"};

	private MySQL_Counter() {
		
	}
	public static Counter selectCounter(int counter_no) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where counter_no=" + counter_no
		);
		
		Counter counter = new Counter(
			(int)result[0][0],
			Time.parseTime((java.sql.Time)result[0][1]),
			CounterState.valueOf((String)result[0][2]),
			(int)result[0][3]
		);
		
		return counter;
	}
	public static void updateCounter(Counter counter) {
		MySQL.update(
			table_name,
			table_columns, 
			new Object[] {
				counter.getCounterNo(),
				counter.getActiveTime().toSQLTime(),
				counter.getCounterState().name(),
				counter.getCurrentCartNo()
			}, 
			"where counter_no=" + counter.getCounterNo()
		);
	}
}
