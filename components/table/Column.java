package components.table;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import oop.interfaces.Theme;

public class Column extends JPanel implements Theme{
	private static final long serialVersionUID = 4355410927257105403L;

	public Column(JLabel label) {
		setOpaque(false);
		setLayout(new BorderLayout());
		
		label.setFont(font[0]);
		label.setForeground(text_color[1]);
		label.setHorizontalAlignment(JLabel.LEFT);
		add(label, BorderLayout.CENTER);
	}
}
