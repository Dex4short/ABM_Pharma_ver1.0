package system.printers.manipulations;

import system.printers.tools.Precision;

public class ItemManipulation {
	private Object uom_table[][];
	private int length;
	private String uom1,uom2,toUom;
	private double quotient,newQty,remainder;
	private int qty1,qty2,remaining1,remaining2;
	private Object newItems[][];

	public ItemManipulation(String uom[],int ofs[]){
		length = uom.length;
		uom_table = new Object[length][2];
		for(int i=0;i<length;i++){
			uom_table[i][0] = uom[i];
			uom_table[i][1] = ofs[i];
		}
	}
	public int getIndexOf(String uom){
		for(int i=0;i<length;i++){
			if(uom_table[i][0].equals(uom)){
				return i;
			}
		}
		return -1;
	}
	public int getOfs(String uom){
		return (int)uom_table[getIndexOf(uom)][1];
	}
	public Object[][] getItems(){
		return newItems;
	}
	public ItemManipulation select(String uom,int qty){
		uom1 = uom;
		qty1 = qty;
		return this;
	}
	private void convertTo(String uom){
		toUom = uom;
		quotient    = Precision.divide(getOfs(uom1), getOfs(toUom));
		newQty      = Precision.multiply(qty2, quotient);
	}
	public ItemManipulation split(String uom,int qty){
		uom2 = uom;
		qty2 = qty;
		convertTo(uom);
		int splited = (int)Math.ceil(newQty);
		remaining1  = qty1 - splited;
		
		remainder   = Precision.subtract(splited,newQty);
		remaining2  = (int)Precision.multiply(getOfs(toUom), remainder);
		
		newItems = new Object[][]{
			{uom1,remaining1},
			{uom2,remaining2},
			{uom2,qty2}
		};

		System.out.println(quotient);
		System.out.println(newQty);
		System.out.println(remaining1);
		System.out.println(remainder);
		System.out.println(remaining2);
		
		System.out.println(uom1+","+remaining1);
		System.out.println(uom2+","+remaining2);
		System.out.println(uom2+","+qty2);
		System.out.println();
		return this;
	}
	public ItemManipulation merge(String uom,int qty){	
		uom2 = uom;
		qty2 = qty;
		convertTo(uom);		
		int merged  = (int)Math.floor(newQty);
		remaining1  = qty1 + merged;
		
		remainder   = Precision.subtract(newQty,merged);
		remaining2  = (int)Precision.multiply(getOfs(toUom), remainder);
		
		newItems = new Object[][]{
			{uom1,remaining1},
			{uom2,remaining2},
			{uom2,qty2}
		};
		
		System.out.println(uom1+","+remaining1);
		System.out.println(uom2+","+remaining2);
		System.out.println(uom2+","+qty2);
		System.out.println();

		return this;
	}
	public double getQuotient(){
		return quotient;
	}
}
/*
 * 
	public abstract int qty_set(String uom);
	public abstract int of_s(String uom);

	public static abstract class Converter{}
	public static abstract class Split extends Converter{
		public Split(String uom,int qty){
			holdItem1(uom, qty);
		}
		public Object[][] with(String uom,int qty){
			holdItem2(uom, qty);
			convertTo(uom);
			split();
			return getItems();
		}
	}
	public static abstract class Merge extends Converter{		
		public Merge(String uom,int qty){
			holdItem1(uom, qty);
		}
		public Object[][] with(String uom,int qty){
			holdItem2(uom, qty);
			convertTo(uom);
			merge();
			return getItems();
		}
	}
 * */
