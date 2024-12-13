package system.ui.panels;

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
import oop.interfaces.Theme;
import res.Resource;
import system._default_.Administrator;
import system.ui.Window;

public class PanelAdmin extends Panel implements Theme, Administrator{
	private static final long serialVersionUID = 4864384612729816588L;

	public PanelAdmin() {
		setLayout(new BorderLayout());
		setBackground(new Color(0,0,0,0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
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
		
		Panel header_left = new Panel();
		header_left.setArc(10);
		header_left.setLayout(new FlowLayout(FlowLayout.LEFT));
		header.add(header_left);
		
		Label logo = new Label(new ImageIcon(Resource.get("ABM LOGO 2.png")));
		logo.setBorder(BorderFactory.createEmptyBorder());
		header_left.add(logo);
		
		Panel header_right = new Panel();
		header_right.setArc(10);
		header_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.add(header_right);
		
		Panel center = new Panel();
		center.setArc(10);
		center.setLayout(new BorderLayout());
		center.setBackground(main_color[3]);
		center.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		panel.add(center, BorderLayout.CENTER);
		
		TabPane tab_pane = new TabPane();
		center.add(tab_pane, BorderLayout.CENTER);

		PanelInventory inventory = new PanelInventory();
		PanelTransactions transactions = new PanelTransactions();
		PanelStore panel_store = new PanelStore();
		PanelReserves panel_reserves = new PanelReserves();
		PanelDisposal panel_disposal = new PanelDisposal();
		PanelProductReturns panel_product_return = new PanelProductReturns();
		PanelCustomers panel_customers = new PanelCustomers();
		
		Tab tabs[] = {
				new Tab("Inventory", inventory),
				new Tab("Transactions", transactions),
				new Tab("Store", panel_store),
				new Tab("Reserves", panel_reserves),
				new Tab("Disposal", panel_disposal),
				new Tab("Product Returns", panel_product_return),
				new Tab("Statistics", new Panel()),
				new Tab("Customers", panel_customers)
		};
		
		for(Tab tab: tabs) {
			tab_pane.addTab(tab);
		}
		tab_pane.setSelectedTab(0);
		
		tabs[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toInventory(inventory);
			}
		});
		tabs[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toTransactions(transactions);
			}
		});
		tabs[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toStore();
			}
		});
		tabs[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toResreves();
			}
		});
		tabs[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toDisposal();
			}
		});
		tabs[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toProductReturns();
			}
		});
		tabs[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toStatistics();
			}
		});
		tabs[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toCustomers();
			}
		});

		Window.floatMessage("Welcome Admin");
	}
	@Override
	public void onToInventory() {}
	@Override
	public void onToTransactions() {}
	@Override
	public void onToStore() {}
	@Override
	public void onToReserves() {}
	@Override
	public void onToDisposal() {}
	@Override
	public void onToReturns() {}
	@Override
	public void onToStatistics() {}
	@Override
	public void onToCustomers() {}
}
