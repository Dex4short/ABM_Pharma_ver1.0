package database;

import oop.Packaging;
import oop.Quantity;
import oop.enumerations.PackagingLine;

public class MySQL_Packaging {
	public static final String 
	table_name = "packaging",
	table_columns[] = {"pack_id", "qty", "size", "uom_id", "parentPack_id", "pack_line", "pack_group"};
	
	public static Packaging insertPackaging(Packaging packaging) {		
		packaging.setPackId(MySQL.nextUID("pack_id", table_name));
		if(packaging.getPackagingGroup() == -1) {
			packaging.setPackagingGroup(MySQL.nextUID("pack_group", table_name));
		}
		
		MySQL.insert(
			table_name,
			table_columns,
			new Object[] {
				packaging.getPackId(),
				packaging.getQty().getAmount(),
				packaging.getQty().getSize(),
				packaging.getUom().getUomId(),
				packaging.getParentPackId(),
				packaging.getPackagingLine().name(),
				packaging.getPackagingGroup()
			}
		);

		MySQL_Uom.insert(packaging.getUom());
		
		return packaging;
	}
	public static Packaging selectPackaging(int pack_id) {
		return selectPackagings("where pack_id=" + pack_id)[0];
	}
	public static Packaging[] selectPackagings(PackagingLine pack_line,int pack_group) {
		return selectPackagings("where pack_line='" + pack_line.name() + "' and pack_group=" + pack_group);
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
				(int)(results[r][4]),
				PackagingLine.valueOf((String)(results[r][5])),
				(int)(results[r][6])
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
			new String[] {"qty", "size", "uom_id"},
			new Object[] {
				packaging.getQty().getAmount(),
				packaging.getQty().getSize(),
				packaging.getUom().getUomId()
			},
			"where pack_id=" + packaging.getPackId()
		);
	}
}
