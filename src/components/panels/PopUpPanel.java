package components.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopUpPanel extends Panel{
	private static final long serialVersionUID = -5604785258389029476L;
	private TitledPanel titled_panel;
	
	private MouseListener mouse_listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { pop(); };
	};

	public PopUpPanel() {
		setBackground(new Color(0,0,0,0));
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	public TitledPanel getTitledPanel() {
		return titled_panel;
	}
	public void setTitledPanel(TitledPanel popUp_pane) {
		this.titled_panel = popUp_pane;
	}
	public void popUp(TitledPanel titled_panel) {
		if(getTitledPanel() != null) pop();
		
		add(titled_panel);
		addMouseListener(mouse_listener);
		
		getRootPane().revalidate();
		getRootPane().repaint();

		setTitledPanel(titled_panel);
	}
	public void pop() {
		remove(getTitledPanel());
		removeMouseListener(mouse_listener);

		getRootPane().revalidate();
		getRootPane().repaint();

		setTitledPanel(null);
	}
	
}
