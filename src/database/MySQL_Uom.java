package database;

import system.enumerators.UomType;
import system.objects.Uom;

public class MySQL_Uom {
	public static String 
	table_name = "uom",
	table_columns[] = {"uom_id", "name", "size", "subUom_id"};
	
	public static Uom insert(Uom uom) {
		if(uom != null) {
			String subUom_id = "-1";
			if(uom.getSubUom() != null) {
				Uom inserted_uom = insert(uom.getSubUom());
				
				if(inserted_uom != null) {
					subUom_id = inserted_uom.getUomId();
				}
			};
			
			Object result[][] = MySQL.select(new String[] {"*"}, table_name, "where uom_id='" + uom.getUomId() + "' and subUom_id='" + subUom_id + "'");
			if(result.length == 0) {
				MySQL.insert(
					table_name, 
					table_columns,	
					new Object[] {
						uom.getUomId(),
						uom.getUnitType().name(),
						uom.getUnitSize(),
						subUom_id
					}
				);
			}
			return uom;
		}
		else {
			return null;
		}
	}
	public static Uom selectUom(String uom_id) {
		if(!uom_id.equals("-1")) {
			Object results[][] = MySQL.select(
				table_columns,
				table_name,
				"where uom_id='" + uom_id + "'"
			);
			if(results.length > 0) {
				Uom uom = new Uom(
					(String)results[0][0],	
					UomType.valueOf((String)results[0][1]),	
					(int)results[0][2],	
					selectUom((String)results[0][3])
				);
				return uom;
			}
		}
		return null;
	}
	public static void deleteUom(int uom_id) {
		MySQL.delete(table_name, "where uom_id='" + uom_id + "'");
	}
	public static void updateUom(Uom uom) {
		insert(uom);
	}
}

