package system.printers.manipulations;


public abstract class StringManipulation {
	
	public static String toSingleString(Object values[],String divider){
		return new Collection(values) {
			@Override
			public String each(int index) {
				return divider;
			}
		}.getCollection();
	}
	public static abstract class Collection{
		private String value;
		public Collection(Object values[]){
			value = "";
			for(int i=0;i<values.length-1;i++){
				value += values[i] + each(i);
			}
			value += values[values.length-1];
		}
		public abstract String each(int index);
		public String getCollection(){
			return value;
		}
	}
	public static String[][] addIndexValue(Object values[][],String newValue){
		int
		length1 = values.length;
		String
		newValues[][] =  new String[length1][];
		for(int i=0;i<length1;i++){
			newValues[i] = addIndexValue(values[i], newValue);
		}
		return newValues;
	}
	public static String[] addIndexValue(Object values[],String newValue){
		int
		length1 = values.length;
		String newValues[] = new String[length1+1];
		for(int i=0;i<length1;i++){
			newValues[i] = values[i]+"";
		}
		newValues[length1] = newValue;
		return newValues;
	}
	public static class Checker{
		private String values[];
		
		public Checker(String newValues[]){
			values = new String[newValues.length];
			for(int i=0;i<values.length;i++){
				values[i] = newValues[i];
				System.out.println(values[i] );
			}
		}
		public int indexOf(String value){
			int index = -1;
			for(int i=0;i<values.length;i++){
				if(values[i].equals(value)){
					index = i;
					break;
				}
			}
			System.out.println(index);
			return index;
		}
	}
	public static Checker check(String values[]){
		return new Checker(values);
	}
}
