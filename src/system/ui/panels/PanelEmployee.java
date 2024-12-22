package system.ui.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import components.panels.Panel;

public class PanelEmployee extends Panel{
	private static final long serialVersionUID = -6277054873151673957L;

	public PanelEmployee() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Panel panel = new Panel();
		add(panel, BorderLayout.CENTER);
		
	
	}
}
