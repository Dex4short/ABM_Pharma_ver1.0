package extras;

import java.awt.Component;
import java.awt.Container;

public class Utilities {

	public static Component findComponentByName(Container container, String name) {
		for(Component component: container.getComponents()) {
			if(component.getName() != null && component.getName().equals(name)) {
				return component;
			}
			if(component instanceof Container) {
				Component inner_component = findComponentByName((Container)component, name);
				if(inner_component != null) {
					return inner_component;
				}
			}
		}
		return null;
	}
	public static void printStackTrace(String message, Thread thread) {
		System.err.print("Error: " + message + " ");
		
		StackTraceElement traces[] = thread.getStackTrace();
		String file_name;
		int line_number;
		for(int t=2; t<traces.length; t++) {
			file_name = traces[t].getFileName();
			line_number = traces[t].getLineNumber();
			
			System.err.println("(" + file_name + ":" + line_number + ")");
		}
	}
	public static void printStackTraceAsWarning(String message, Thread thread) {
		System.err.print("Warning: " + message + " ");
		
		StackTraceElement trace = thread.getStackTrace()[2];
		String file_name = trace.getFileName();
		int line_number = trace.getLineNumber();
		
		System.err.println("(" + file_name + ":" + line_number + ")");
	}
}
