package components.tab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import components.Label;
import components.panels.Panel;

public class Tab extends Panel implements MouseListener{
	private static final long serialVersionUID = 3839302809616823664L;
	private boolean toggled;
	private Label tab_title;
	private Panel tab_content;

	public Tab(String title, Panel tab_content){
		setArc(20);
		setCorner(2, false);
		setCorner(3, false);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension( 100, 40));
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		setBackground(main_color[0].brighter());
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		tab_title = new Label(title);
		tab_title.setFont(font[0]);
		tab_title.setForeground(text_color[3]);
		tab_title.setHorizontalAlignment(Label.CENTER);
		tab_title.setVerticalAlignment(Label.CENTER);
		tab_title.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		add(tab_title, BorderLayout.CENTER);
		
		setTabContent(tab_content);
		
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		toggle();
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(!isToggled()) {
			tab_title.setForeground(text_color[3]);
			setBackground(main_color[0]);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(!isToggled()) {
			tab_title.setForeground(text_color[3]);
			setBackground(main_color[0].brighter());
		}
	}
	public Label getLabel() {
		return tab_title;
	}
	public void setLabel(Label label) {
		this.tab_title = label;
	}
	public void toggle() {
		toggled = !toggled;
		if(toggled) {
			tab_title.setForeground(text_color[0]);
			setBackground(main_color[2]);
		}
		else {
			tab_title.setForeground(text_color[3]);
			setBackground(main_color[0].brighter());
		}
		
		if(getRootPane() != null) {
			getRootPane().revalidate();
			getRootPane().repaint();
		}
	}
	public boolean isToggled() {
		return toggled;
	}
	public Panel getTabContent() {
		return tab_content;
	}
	public void setTabContent(Panel panel) {
		this.tab_content = panel;
	}
}
