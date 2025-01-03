package system._default_;

import components.panels.Panel;
import system.ui.panels.PanelCounter;

public interface Employee {
	
	public default void toStore() {
		onToStore((PanelCounter)nextPanel());
	}

	public Panel nextPanel();
	public void onToStore(PanelCounter store);
	
}
