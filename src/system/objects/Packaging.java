package system.objects;

import system.enumerators.PackagingLine;

public class Packaging {
	private int pack_id, parentPack_id, pack_group;
	private Quantity qty;
	private Uom uom;
	private PackagingLine pack_line;
	
	public Packaging(int pack_id, Quantity qty, Uom uom, int parentPack_id, PackagingLine pack_line, int pack_group) {
		setPackId(pack_id);
		setQty(qty);
		setUom(uom);
		setParentPackId(parentPack_id);
		setPackagingLine(pack_line);
		setPackagingGroup(pack_group);
	}
	public Packaging() {
		setPackId(-1);
		setQty(new Quantity(0));
		setUom(new Uom());
		setParentPackId(-2);
		setPackagingLine(null);
		setPackagingGroup(-1);
	}
	@Override
	public String toString() {
		String str = 
			"Packaging( pack_id:" + getPackId() + " )\n" +
			"\t" + getQty().toString() + "\n" +
			"\t" + getUom().toString() + "\n" +
			"\t" + getParentPackId() + "\n" +
			"\t" + getPackagingLine() + "\n" +
			"\t" + getPackagingGroup() + "\n"
		;	
		return str;
	}
	public int getPackId() {
		return pack_id;
	}
	public void setPackId(int pack_id) {
		this.pack_id = pack_id;
	}
	public Quantity getQty() {
		return qty;
	}
	public void setQty(Quantity qty) {
		this.qty = qty;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public int getParentPackId() {
		return parentPack_id;
	}
	public void setParentPackId(int parentPack_id) {
		this.parentPack_id = parentPack_id;
	}
	public PackagingLine getPackagingLine() {
		return pack_line;
	}
	public void setPackagingLine(PackagingLine pack_line) {
		this.pack_line = pack_line;
	}
	public int getPackagingGroup() {
		return pack_group;
	}
	public void setPackagingGroup(int pack_group) {
		this.pack_group = pack_group;
	}
	public boolean isRunningOut() {
		return qty.isRunningOut(qty.getSize()/3);
	}
	public boolean isOutOfStock() {
		return qty.isOutOfStock();
	}
}
