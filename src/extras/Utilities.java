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
}
