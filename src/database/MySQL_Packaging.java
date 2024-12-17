package database;

import oop.Packaging;
import oop.Quantity;
import oop.enums.ProductCondition;

public class MySQL_Packaging {
	public static String 
	table_name = "packaging",
	table_columns[] = {"pack_id", "qty", "size", "uom_id", "parentPack_id"};
	
	public static Packaging insert(Packaging packaging, ProductCondition condition) {
		packaging.setPackId(MySQL.nextUID(table_columns[0], table_name));
		
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				packaging.getPackId(),
				packaging.getQty().getQuantity(),
				packaging.getQty().getSize(),
				MySQL_Uom.insert(packaging.getUom()).getUomId(),//needs better approach
				packaging.getParentPackId()
			}
		);
		return packaging;
	}
	public static Packaging selectPackaging(int pack_id) {
		return selectPackagings("where pack_id=" + pack_id)[0];
	}
	public static Packaging[] selectPackagings(String condition) {
		Object results[][] = MySQL.select(
			table_columns,
			table_name,
			condition
		);
		
		Packaging packagings[] = new Packaging[results.length];
		for(int r=0; r<results.length; r++) {
			packagings[r] = new Packaging(
				(int)results[r][0],
				new Quantity((int)(results[r][1]), (int)(results[r][2])),
				MySQL_Uom.selectUom((String)(results[r][3])),
				(int)(results[r][4])
			);
		}
		
		return packagings;
	}
	public static void deletePackaging(Packaging packaging) {
		MySQL.delete(table_name, "where pack_id=" + packaging.getPackId());
	}
	public static void updatePackaging(Packaging packaging) {
		MySQL_Uom.updateUom(packaging.getUom());
		MySQL.update(
			table_name,
			table_columns,
			new Object[] {
				packaging.getPackId(),
				packaging.getQty().getQuantity(),
				packaging.getQty().getSize(),
				packaging.getUom().getUomId(),
				packaging.getParentPackId()
			},
			"where pack_id=" + packaging.getPackId()
		);
	}
}
