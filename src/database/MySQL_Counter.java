package database;

import oop.Counter;

public class MySQL_Counter {
	public static String 
	table_name = "counter",
	table_columns[] = {"counter_no", "active_time", "counter_state", "currentCart_no"};
	
	public static Counter insertCounter(Counter counter) {
		counter.setCounterNo(MySQL.nextUID("counter_no", "counter"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				counter.getCounterNo(),
				counter.getActiveTime(),
				counter.getCounterState(),
				counter.getCurrentCartNo()
			}
		);
		return counter;
	}
	public static Counter[] selectCounter(int counter_no) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where counter_no=" + counter_no
		);
		Counter counter[] = new Counter[result.length];
		for(int i=0; i<counter.length; i++) {
			counter[i] = new Counter(
				(int)result[i][0],	
				(int)result[i][1],
				(int)result[i][2]
			);
		}
		return counter;
	}
	public static void deleteCounter(int counter_no) {
		MySQL.delete(table_name, "where counter_no=" + counter_no);
	}
	public static void updateCounter(int counter_no, Counter counter) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						counter.getCounterNo(),
						counter.getActiveTime(),
						counter.getCounterState(),
						counter.getCurrentCartNo()
				},
				"where counter_no=" + counter_no);
	}

}
