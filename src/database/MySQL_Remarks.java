package database;

import system.objects.Date;
import system.objects.Remarks;
import system.objects.Time;

public class MySQL_Remarks {
	public static final String 
	table_name = "remarks",
	table_columns[] = {"rem_id", "rem_date", "rem_time", "details"};
			
	public static Remarks insertRemarks(Remarks remarks) {
		remarks.setRemId(MySQL.nextUID(table_columns[0], table_name));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				remarks.getRemId(),
				remarks.getDate().toSQLDate(),
				remarks.getTime().toSQLTime(),
				remarks.getDetails()
			}
		);
		return remarks;
	}
	public static Remarks selectRemarks(int rem_id) {
		if(rem_id == -1) return null;
		
		Object results[][] = MySQL.select(
				table_columns,
				table_name,
				"where rem_id=" + rem_id
		);
		if(results.length > 0) {
			Remarks remarks = new Remarks(
					(int)results[0][0],	
					Date.parseDate((java.sql.Date)results[0][1]),	
					Time.parseTime((java.sql.Time)results[0][2]),	
					(String)results[0][3]
					
			);
			return remarks;
		}
		return null;
	}
	public static void deleteRemarks(Remarks remarks) {
		if(remarks == null) return; 
		MySQL.delete(table_name, "where rem_id=" + remarks.getRemId());
	}
	public static void updateRemarks(Remarks remarks) {
		if(remarks == null) return; 
		
		if(remarks.getRemId() == -1) {
			insertRemarks(remarks);
		}
		else {
			deleteRemarks(remarks);
		}
	}
	
}
