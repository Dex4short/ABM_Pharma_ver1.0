package components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Padding extends JPanel{
	private static final long serialVersionUID = -5191687214606812365L;

	public Padding(JComponent component, Border border) {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(border);
		add(component, BorderLayout.CENTER);
	}	
	public Padding(JComponent component, int top, int left, int bottom, int right) {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		add(component, BorderLayout.CENTER);
	}
}
