package database;

import oop.Packaging;

public class MySQL_Packaging {
	public static String 
	table_name = "packaging",
	table_columns[] = {"pack_id", "qty", "size", "uom_id", "parentPack_id"};
	
	public static Packaging insert(Packaging packaging) {
		packaging.setPackId(MySQL.nextUID("pack_id", "packaging"));
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				packaging.getPackId(),
				packaging.getQty().getQuantity(),
				packaging.getQty().getSize(),
				packaging.getUom(),
				packaging.getParentPackId()
			}
		);
		return packaging;
	}
	public static Package[] selectPackage(int pack_id) {
		Object result[][] = MySQL.select(
				table_columns,
				table_name,
				"where pack_id=" + pack_id
		);
		Packaging packaging[] = new Packaging[result.length];
		for(int i=0; i<packaging.length; i++) {
			packaging[i] = new Packaging(
				(int)result[i][0],	
				(String)result[i][1],	
				(String)result[i][2],	
				(String)result[i][3],	
				(Date)result[i][4]
			);
		}
		return packaging;
	}
	public static void deletePackaging(int pack_id) {
		MySQL.delete(table_name, "where pack_id=" + pack_id);
	}
	public static void updatePackaging(int pack_id, Packaging packaging) {
		MySQL.update(
				table_name,
				table_columns,
				new Object[] {
						packaging.getPackId(),
						packaging.getQty().getQuantity(),
						packaging.getQty().getSize(),
						packaging.getUom(),
						packaging.getParentPackId()
				},
				"where pack_id=" + pack_id);
	}

}
