package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InputOutput {

	private InputOutput() {
		
	}
	public static void write(String file_name, String value) {
		File file = new File("inf/" + file_name);
		
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String read(String file_name) {
		String value = "";
		File file = new File("inf/" + file_name);
		
		try (FileReader reader = new FileReader(file)) {
			int data;
			while((data = reader.read()) != -1) {
				value += (char)data;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
}
