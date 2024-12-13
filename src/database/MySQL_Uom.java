package database;

import oop.Uom;
import oop.enums.UomType;

public class MySQL_Uom {
	public static String 
	table_name = "uom",
	table_columns[] = {"uom_id", "name", "size", "subUom_id"};
	
	public static Uom insert(Uom uom) {
		uom.setUomId(MySQL.nextUID("uom_id", "uom"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				uom.getUomId(),
				uom.getUnitType().name(),
				uom.getUnitSize(),
				uom.getSubUom()
			}
		);
		return uom;
	}
	public static Uom selectUom(int uom_id) {
		if(uom_id != -1) {
			Object result[][] = MySQL.select(
					table_columns,
					table_name,
					"where uom_id=" + uom_id
			);
			Uom uom = new Uom(
					(int)result[0][0],	
					UomType.valueOf((String)result[0][1]),	
					(int)result[0][2],	
					selectUom((int)result[0][3])
			);
			return uom;
		}
		return null;
	}
	public static void deleteUom(int uom_id) {
		MySQL.delete(table_name, "where uom_id=" + uom_id);
	}
	public static void updateUom(int uom_id, Uom uom) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						uom.getUomId(),
						uom.getUnitType().name(),
						uom.getUnitSize(),
						uom.getSubUom()
				},
				"where uom_id=" + uom_id);
	}

}

