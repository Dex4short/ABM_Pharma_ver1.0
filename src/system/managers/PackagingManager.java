package system.managers;

import java.math.BigDecimal;
import java.util.ArrayList;

import system.enumerators.PackagingLine;
import system.objects.Packaging;
import system.objects.Quantity;
import system.objects.Uom;

public class PackagingManager {

	public static Packaging[] extract(Packaging main_pack, Packaging sub_pack) {
		ArrayList<PackagingData> pack_datas = new ArrayList<PackagingData>();
		
		Quantity mainPack_qty = main_pack.getQty(), subPack_qty = sub_pack.getQty();
		Uom mainPack_uom = main_pack.getUom(), subPack_uom = sub_pack.getUom();
		
		BigDecimal 
		length = BigDecimal.ONE,
		scale = BigDecimal.ONE,
		magnitude = null;
		
		PackagingData pack_data;
		while(true) {
			pack_data = new PackagingData(mainPack_uom, length, scale);
			pack_data.setQuantity_A(mainPack_qty);
			pack_datas.add(pack_data);

			if(mainPack_uom.getUnitType() == subPack_uom.getUnitType()) magnitude = scale;
			
			mainPack_uom = mainPack_uom.getSubUom();
			if(mainPack_uom == null) break;
			
			length = length.multiply(new BigDecimal(mainPack_uom.getUnitSize()));
			scale = scale.divide(new BigDecimal(mainPack_uom.getUnitSize()));
			
		}
		
		Packaging extracted_packages[] = new Packaging[pack_datas.size()];
		for(int p=0; p<extracted_packages.length; p++) {
			pack_datas.get(p).setQuantity_B(subPack_qty, magnitude);
			pack_datas.get(p).subtract();
			
			extracted_packages[p] = pack_datas.get(p).createPackaging(main_pack.getPackagingLine(), main_pack.getPackagingGroup());
			if(p==0) {
				extracted_packages[p].setPackId(main_pack.getPackId());
			}
			else {
				extracted_packages[p].setParentPackId(main_pack.getPackId());
			}
		}
		return extracted_packages;
	}
	/*
	public static void merge(Packaging main_pack, Packaging sub_pack) {
		throw new RuntimeException("PackagingManager.merge() is currently not available...");
	}
	*/
	
	private static class PackagingData {
		private Uom uom;
		private Quantity quantity;
		private BigDecimal amount_a, amount_b, length, scale;
		
		public PackagingData(Uom uom, BigDecimal length, BigDecimal scale) {
			this.uom = uom;
			this.length = length;
			this.scale = scale;
			quantity = new Quantity(0);
		}
		public void setQuantity_A(Quantity main_qty) {
			amount_a = new BigDecimal(main_qty.getAmount()).multiply(length);
			quantity.setSize(main_qty.getSize());
		}
		public void setQuantity_B(Quantity sub_qty, BigDecimal magnitude) {
			amount_b = new BigDecimal(sub_qty.getAmount()).multiply(magnitude).divide(scale);
		}
		public void subtract() {
			if(uom.getUnitSize() == 1) {
				quantity.setAmount(amount_a.subtract(amount_b).intValue());
			}
			else {
				int value = amount_a.subtract(amount_b).intValue() % uom.getUnitSize();
				quantity.setAmount(value);
				quantity.setSize(value);
			}
		}
		/*
		public void add() {
			if(uom.getUnitSize() == 1) {
				quantity.setAmount(amount_a.add(amount_b).intValue());
			}
			else {
				int value = amount_a.add(amount_b).intValue() % uom.getUnitSize();
				quantity.setAmount(value);
				quantity.setSize(value);
			}
		}
		*/
		public Packaging createPackaging(PackagingLine pack_line, int pack_group) {
			return new Packaging(-1, quantity, uom, -1, pack_line, pack_group);
		}
		public String toString() {
			return  
				amount_a.toPlainString() + " " + uom.getUnitType().name() + 
				" - " +
				amount_b.toPlainString() + " " + uom.getUnitType().name() +
				" = " +  
				quantity.toString() + " " + uom.getUnitType().name() 
			;
		}
	}
	public static boolean isRunningOut(Packaging pack) {
		return pack.getParentPackId()==-1 && pack.isRunningOut();
	}
	public static boolean isOutOfStock(Packaging pack) {
		return pack.getParentPackId()==-1 && pack.isOutOfStock();
	}
}
