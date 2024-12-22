package components.panels;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.Label;

public class TitledPanel extends Panel{
	private static final long serialVersionUID = 2770960288601698032L;
	private PanelHead panel_head;
	private PanelBody panel_body;

	{
		setArc(20);
	}
	public TitledPanel(Label title) {
		setLayout(new BorderLayout());
		
		panel_head = new PanelHead(title);
		add(panel_head, BorderLayout.NORTH);
		
		panel_body = new PanelBody();
		add(panel_body, BorderLayout.CENTER);
	}	
	public TitledPanel(String title) {
		setLayout(new BorderLayout());
		
		panel_head = new PanelHead(new Label(title));
		add(panel_head, BorderLayout.NORTH);
		
		panel_body = new PanelBody();
		add(panel_body, BorderLayout.CENTER);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(main_color[3]);
		g.drawLine(0, panel_head.getHeight() + 10, getWidth(), panel_head.getHeight() + 10);
	}
	public PanelHead getPanelHead() {
		return panel_head;
	}
	public PanelBody getPanelBody() {
		return panel_body;
	}
	public void setPanelHead(PanelHead panel_head) {
		this.panel_head = panel_head;
	}
	public void setPanelBody(PanelBody panel_body) {
		this.panel_body = panel_body;
	}
	public void setTitle(String title) {
		panel_head.setTitle(title);
	}
	public String getTitle() {
		return panel_head.getTitle();
	}
	
	public class PanelHead extends JPanel{
		private static final long serialVersionUID = -5060388821116578839L;
		private Label title;

		public PanelHead(Label title) {
			setOpaque(false);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			
			this.title = title;
			add(title).setForeground(main_color[0]);
		}
		public void setTitle(String title) {
			this.title.setText(title);
		}
		public String getTitle() {
			return title.getText();
		}
	}
	public class PanelBody extends JPanel{
		private static final long serialVersionUID = 460599379005674587L;

		public PanelBody() {
			setOpaque(false);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		}
	}
}
