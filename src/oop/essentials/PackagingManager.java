package oop.essentials;

import java.math.BigDecimal;
import java.util.ArrayList;

import oop.Packaging;
import oop.Quantity;
import oop.Uom;

public class PackagingManager {

	public static Packaging[] extract(Packaging main_pack, Packaging sub_pack) {
		System.out.println(">>>" + main_pack.toString());
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
			
			extracted_packages[p] = pack_datas.get(p).createPackaging();
			if(p==0) {
				extracted_packages[p].setPackId(main_pack.getPackId());
			}
			else {
				extracted_packages[p].setParentPackId(main_pack.getPackId());
			}
			System.out.println(pack_datas.get(p).toString());
		}

		return extracted_packages;
	}
	public static void merge(Packaging main_pack, Packaging sub_pack) {
		
	}
	
	private static class PackagingData {
		private Uom uom;
		private Quantity quantity;
		private BigDecimal quantity_a, quantity_b, length, scale;
		
		public PackagingData(Uom uom, BigDecimal length, BigDecimal scale) {
			this.uom = uom;
			this.length = length;
			this.scale = scale;
		}
		public void setQuantity_A(Quantity main_qty) {
			quantity_a = new BigDecimal(main_qty.getQuantity()).multiply(length);
		}
		public void setQuantity_B(Quantity sub_qty, BigDecimal magnitude) {
			quantity_b = new BigDecimal(sub_qty.getQuantity()).multiply(magnitude).divide(scale);
		}
		public void subtract() {
			if(uom.getUnitSize() == 1) {
				quantity = new Quantity(quantity_a.subtract(quantity_b).intValue());
			}
			else {
				quantity = new Quantity(quantity_a.subtract(quantity_b).intValue() % uom.getUnitSize());
			}
		}
		public void add() {
			if(uom.getUnitSize() == 1) {
				quantity = new Quantity(quantity_a.add(quantity_b).intValue());
			}
			else {
				quantity = new Quantity(quantity_a.add(quantity_b).intValue() % uom.getUnitSize());
			}
		}
		public Packaging createPackaging() {
			return new Packaging(-1, quantity, uom, -1, null, -1);
		}
		public String toString() {
			return  
				quantity_a.toPlainString() + " " + uom.getUnitType().name() + 
				" - " +
				quantity_b.toPlainString() + " " + uom.getUnitType().name() +
				" = " +  
				quantity.toString() + " " + uom.getUnitType().name() ;
		}
	}
}
