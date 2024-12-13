package database;

import oop.Date;
import oop.Remarks;
import oop.Time;

public class MySQL_Remarks {
	public static String 
	table_name = "remarks",
	table_columns[] = {"rem_id", "rem_date", "rem_time", "details"};
			
			public static Remarks insertProduct(Remarks remarks) {
				remarks.setRemId(MySQL.nextUID("rem_id", "remarks"));
				MySQL.insert(
					table_name,
					table_columns,
					new Object[] {
						remarks.getRemId(),
						remarks.getDate(),
						remarks.getTime(),
						remarks.getDetails()
						
					}
				);
				return remarks;
			}
			public static Remarks selectRemarks(int rem_id) {
				Object result[][] = MySQL.select(
						table_columns,
						table_name,
						"where rem_id=" + rem_id
				);
				Remarks remarks = new remarks[result.length];
					remarks[i] = new Remarks(
						(int)results[i][0],	
						(Date)results[i][1],	
						(Time)results[i][2],	
						(String)results[i][3],	
						
					);
				return remarks;
			}
			public static void deleteRemarks(int rem_id) {
				MySQL.delete(table_name, "where rem_id=" + rem_id);
			}
			public static void updateRemarks(int rem_id, Remarks remarks) {
				MySQL.update(
						table_name,
						table_columns,
						new Object[] {
								remarks.getRemId(),
								remarks.getDate(),
								remarks.getTime(),
								remarks.getDetails()
						},
						"where rem_id=" + rem_id);
			}
	
}
