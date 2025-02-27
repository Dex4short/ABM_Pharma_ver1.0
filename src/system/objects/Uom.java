package system.objects;

import system.enumerators.UomType;

public class Uom {
	private String uom_id="-1";
	private UomType unit_type;
	private int unit_size;
	private Uom sub_uom;
	
	public Uom(String uom_id, UomType uomType, int unitSize, Uom subUom) {
		setUomId(uom_id);
		setUnitType(uomType);
		setUnitSize(unitSize);
		setSubUom(subUom);
	}
	public Uom() {
		setUomId("-1");
		setUnitType(UomType.set);
		setUnitSize(-1);
		setSubUom(null);
	}
	@Override
	public String toString() {
		String str = "[" + getUnitType() + " : " + getUnitSize() + "]";
		Uom sub_uom = getSubUom();
		while(sub_uom != null) {
			str += " -> [" + sub_uom.getUnitType() + " : " + sub_uom.getUnitSize() + "]";
			sub_uom = sub_uom.getSubUom();
		}
		return str;
	}
	public String getUomId() {
		return uom_id;
	}
	public void setUomId(String uom_id) {
		this.uom_id = uom_id;
	}
	public UomType getUnitType() {
		return unit_type;
	}
	public int getUnitSize() {
		return unit_size;
	}
	public Uom getSubUom() {
		return sub_uom;
	}
	public void setUnitType(UomType unitType) {
		unit_type = unitType;
	}
	public void setUnitSize(int unitSize) {
		unit_size = unitSize;
	}
	public void setSubUom(Uom subUom) {
		sub_uom = subUom;
	}
}
