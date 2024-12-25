package oop.essentials;

import oop.Uom;
import oop.enumerations.UomType;

public class UomPresets {
	private UomPresets() {
		
	}
	public static final Uom set[] = {
			new Uom("-1", UomType.box, 1, null),
			new Uom("-1", UomType.pieces, 1, null),
			new Uom("-1", UomType.box, 1, new Uom("-1", UomType.pieces, 10, null)),
			new Uom("-1", UomType.box, 1, new Uom("-1", UomType.stab, 10, new Uom("-1", UomType.capsule, 10, null))),
			new Uom("-1", UomType.box, 1, new Uom("-1", UomType.stab, 10, new Uom("-1", UomType.tablet, 10, null))),
			new Uom("-1", UomType.box, 1, new Uom("-1", UomType.sachet, 10, null)),
			new Uom("-1", UomType.stab, 1, new Uom("-1", UomType.capsule, 10, null)),
			new Uom("-1", UomType.stab, 1, new Uom("-1", UomType.tablet, 10, null)),
			new Uom("-1", UomType.capsule, 1, null),
			new Uom("-1", UomType.tablet, 1, null),
			new Uom("-1", UomType.bag, 1, null),
			new Uom("-1", UomType.bag, 1, new Uom("-1", UomType.sachet, 10, null)),
			new Uom("-1", UomType.sachet, 1, null),
			new Uom("-1", UomType.jar, 1, null),
	};
	public static final String sizes[] = {"2", "3","4", "6", "8", "10", "12", "14"};	
	
	public static int sizeIndexOf(String size) {
		for(int i=0; i<sizes.length; i++) {
			if(sizes[i].equals(size)) {
				return i;
			}
		}
		return -1;
	}
	public static void prepareIds(Uom uom) {
		if(uom != null) {
			String alias = uom.getUnitType().getId();
			int size = uom.getUnitSize();
			
			String id = alias + "" + size;
			String new_id;
			
			Uom sub_uom = uom.getSubUom();
			
			prepareIds(sub_uom);
			
			if(sub_uom != null) {
				new_id = id + sub_uom.getUomId();
			}
			else {
				new_id = id;
			}
			
			uom.setUomId(new_id);
		}
	}
}
