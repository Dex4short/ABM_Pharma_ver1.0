package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InputOutput {

	private InputOutput() {
		
	}
	public static String read(String file_name){
		String data = "";
		
		try {
			File file = new File(file_name);
			FileReader reader = new FileReader(file);
			
			int d;
			while((d = reader.read()) != -1) {
				data += (char)d;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public static void write(String file_name, String data) {
		try {
			File file = new File(file_name);
			FileWriter writer = new FileWriter(file);
			writer.write(data);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
