package system.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Label;
import components.panels.Panel;
import components.tab.Tab;
import components.tab.TabPane;
import res.Resource;
import system._default_.Employee;
import system.ui.buttons.accessibility.ButtonSettings;
import system.ui.panels.PanelCounter;

public class PanelEmployee extends Panel implements Employee{
	private static final long serialVersionUID = -6277054873151673957L;
	private static Tab tabs[];
	private Panel next_panel;
	private int counter_no;

	public PanelEmployee(int counter_no) {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(new Color(0,0,0,0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setCounterNo(counter_no);
		
		Panel panel = new Panel();
		panel.setArc(10);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		add(panel, BorderLayout.CENTER);
		
		Panel header = new Panel();
		header.setArc(10);
		header.setLayout(new GridLayout(1, 2));
		header.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		panel.add(header, BorderLayout.NORTH);
		
		Panel header_left = new Panel() {
			private static final long serialVersionUID = -1602411307098764274L;
			{
				setArc(10);
				setLayout(new FlowLayout(FlowLayout.LEFT));
				
				Label logo = new Label(new ImageIcon(Resource.get("ABM LOGO 2.png")));
				logo.setBorder(BorderFactory.createEmptyBorder());
				add(logo);
			}
		};
		header.add(header_left);
		
		Panel header_right = new Panel() {
			private static final long serialVersionUID = -1602411307098764274L;
			{
				setArc(10);
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				
				Label counter_label = new Label("Counter " + getCounterNo());
				counter_label.setFont(font[1]);
				counter_label.setForeground(main_color[0]);
				add(counter_label);
				
				ButtonSettings btn_settings = new ButtonSettings();
				btn_settings.getPopupSettings().getSettingsList().removeItem(counter_no);
				add(btn_settings);
			}
		};
		header.add(header_right);
		
		Panel center = new Panel();
		center.setArc(10);
		center.setLayout(new BorderLayout());
		center.setBackground(main_color[3]);
		center.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		panel.add(center, BorderLayout.CENTER);
		
		TabPane tab_pane = new TabPane();
		center.add(tab_pane, BorderLayout.CENTER);

		PanelCounter panel_counter = new PanelCounter(1);
		
		tabs = new Tab[]{
			new Tab("Store", panel_counter)
		};
		
		for(Tab tab: tabs) {
			tab_pane.addTab(tab);
		}
		tab_pane.setSelectedTab(0);
		
		tabs[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_counter;
				toStore();
			}
		});

		next_panel = panel_counter;
	}
	@Override
	public void onToStore(PanelCounter store) {
		Window.load(() -> {
			store.openCounter(counter_no);
			store.openCart(store.getCounter().getCurrentCartNo(), store.getCounter().getCounterNo());
			store.loadAisleFromStore();
			store.loadCartFromStore(store.getCounter());
		}, "Loading Store...");
	}
	@Override
	public Panel nextPanel() {
		return next_panel;
	}
	public int getCounterNo() {
		return counter_no;
	}
	public void setCounterNo(int counter_no) {
		this.counter_no = counter_no;
	}
	
}
