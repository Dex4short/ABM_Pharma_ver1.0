package system.printers.manipulations;

import java.math.BigDecimal;

public abstract class ObjectManipulation {
	public static abstract class Removal{
		private Object newValues[];
		
		public Removal(Object values[],int indexes[]){
			int
			length  = values.length - indexes.length,
			length1 = values.length,
			length2 = indexes.length;
			newValues = new Object[length];
			for(int i=0,ii=0,iii=0;i<length1;i++){
				switch (0) {
				case 0:
					if(iii < length2){
						if(i == indexes[iii]){
							removedValue(values[ii],indexes[iii]);
							ii++;
							iii++;
							break;
						}
					}
				case 1:
					newValues[i-iii] = values[ii];
					ii++;
					break;
				}
			}
		}
		public Object[] getNewValues(){
			return newValues;
		}
		public abstract void removedValue(Object value,int index);
	}
	public static Object[] removeIndexValues(Object values[],int indexes[]){
		return new ObjectManipulation.Removal(values,indexes){
			@Override
			public void removedValue(Object value,int index) {
				return;
			}
		}.getNewValues();
	}
	public static Object[][] removeIndexValues(Object values[][],int indexes[]){
		int length = values.length;
		Object newValues[][] = new Object[length][];
		for(int i=0;i<length;i++){
			newValues[i] = removeIndexValues(values[i], indexes);
		}
		return newValues;
	}
	public static abstract class Combine{
		private Object newValues[];
		
		public Combine(Object values[],int spaces[]){
			int
			length = values.length + spaces.length;
			newValues = new Object[length];
			for(int i=0,ii=0,iii=0;i<length;i++){
				switch(0){
				case 0:
					if(iii < spaces.length){
						if(i == spaces[iii]){
							newValues[i] = add(iii);
							iii++;
							break;
						}
					}
				case 1:
					newValues[i] = values[ii];
					ii++;
				}
			}
		}
		public abstract Object add(int index);
		public Object[] getNewValues(){
			return newValues;
		}
	}
	public static Object[] combineValues(Object values[],int spaces[],Object newValues[]){		
		return new Combine(values,spaces){
			@Override
			public Object add(int index) {
				return newValues[index];
			}
			
		}.getNewValues();
	}
	public static abstract class Replacement{
		private Object replacement[];
		
		public Replacement(Object values[],int indexes[]){
			int 
			length1 = values.length,
			length2 = indexes.length;
			replacement = new Object[length1];
			for(int i=0;i<length1;i++){
				replacement[i] = values[i];
			}
			for(int i=0;i<length2;i++){
				replacement[i] = replace(i);
			}
		}
		public abstract Object replace(int index);
		public Object[] getReplacements(){
			return replacement;
		}
	}
	public static Object[] replaceIndexValues(Object values[],Object newValues[],int indexes[]){
		return new Replacement(values, indexes){
			@Override
			public Object replace(int index) {
				return newValues[index];
			}
		}.getReplacements();
	}
	public static String[] toStrings(Object values[]){
		int length = values.length;
		String toString[] = new String[length];
		for(int i=0;i<length;i++){
			toString[i] = values[i] + "";
		}
		return toString;
	}
	public static BigDecimal[] toBigDecimals(Object values[]){
		int length = values.length;
		BigDecimal newValues[] = new BigDecimal[length];
		for(int i=0;i<length;i++){
			newValues[i] = BigDecimal.valueOf(Double.valueOf(values[i] + ""));
		}
		return newValues;
	}
	public static Object[] getIndexValues(Object values[][],int index){
		int length = values.length;
		Object newValues[] = new Object[length];
		for(int i=0;i<length;i++){
			newValues[i] = values[i][index]; 
		}
		return newValues;
	}
}
