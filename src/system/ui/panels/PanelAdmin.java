package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.Panel;
import components.tab.Tab;
import components.tab.TabPane;
import oop.interfaces.Theme;
import res.Resource;
import system._default_.Administrator;
import system.ui.Window;

public class PanelAdmin extends JPanel implements Theme, Administrator{
	private static final long serialVersionUID = 4864384612729816588L;

	public PanelAdmin() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder());
		add(panel, BorderLayout.CENTER);
		
		JPanel header = new JPanel(new GridLayout(1, 2));
		header.setOpaque(false);
		header.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		panel.add(header, BorderLayout.NORTH);
		
		JPanel header_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header_left.setOpaque(false);
		header.add(header_left);
		
		JLabel logo = new JLabel(new ImageIcon(Resource.get("ABM LOGO 2.png")));
		logo.setBorder(BorderFactory.createEmptyBorder());
		header_left.add(logo);
		
		JPanel header_right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		header_right.setOpaque(false);
		header.add(header_right);
		
		Panel center = new Panel();
		center.setLayout(new BorderLayout());
		center.setBackground(main_color[3]);
		center.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		panel.add(center, BorderLayout.CENTER);
		
		TabPane tab_pane = new TabPane();
		center.add(tab_pane, BorderLayout.CENTER);

		PanelInventory inventory = new PanelInventory();
		PanelTransactions transactions = new PanelTransactions();
		
		Tab tabs[] = {
				new Tab("Inventory", inventory),
				new Tab("Transactions", transactions),
				new Tab("Store", new JPanel()),
				new Tab("Reserves", new JPanel()),
				new Tab("Disposal", new JPanel()),
				new Tab("Product Returns", new JPanel()),
				new Tab("Statistics", new JPanel()),
				new Tab("Customers", new JPanel())
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
