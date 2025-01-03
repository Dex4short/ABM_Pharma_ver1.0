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
import system._default_.Administrator;
import system.managers.NotificationsManager;
import system.ui.buttons.accessibility.ButtonNotifications;
import system.ui.buttons.accessibility.ButtonSettings;
import system.ui.panels.PanelCustomers;
import system.ui.panels.PanelDisposal;
import system.ui.panels.PanelInventory;
import system.ui.panels.PanelProductReturns;
import system.ui.panels.PanelReserves;
import system.ui.panels.PanelStore;
import system.ui.panels.PanelTransactions;

public class PanelAdmin extends Panel implements Administrator{
	private static final long serialVersionUID = 4864384612729816588L;
	private static Tab tabs[];
	private Panel next_panel;
	private ButtonNotifications btn_notifications;

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
				
				ButtonSettings btn_settings = new ButtonSettings();
				add(btn_settings);
				
				btn_notifications = new ButtonNotifications();
				add(btn_notifications);
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

		PanelInventory panel_inventory = new PanelInventory();
		PanelTransactions panel_transactions = new PanelTransactions();
		PanelStore panel_store = new PanelStore();
		PanelReserves panel_reserves = new PanelReserves();
		PanelDisposal panel_disposal = new PanelDisposal();
		PanelProductReturns panel_product_return = new PanelProductReturns();
		PanelCustomers panel_customers = new PanelCustomers();
		
		tabs = new Tab[]{
			new Tab("Inventory", panel_inventory),
			new Tab("Transactions", panel_transactions),
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
				next_panel = panel_inventory;
				toInventory();
			}
		});
		tabs[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_transactions;
				toTransactions();
			}
		});
		tabs[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_store;
				toStore();
				panel_inventory.loadAllFromInventory();
			}
		});
		tabs[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_reserves;
				toResreves();
			}
		});
		tabs[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_disposal;
				toDisposal();
			}
		});
		tabs[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next_panel = panel_product_return;
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
				next_panel = panel_customers;
				toCustomers();
			}
		});
		
		next_panel = panel_inventory;		
	}
	@Override
	public void onToInventory(PanelInventory inventory) {
		Window.load(() -> {
			inventory.loadAllFromInventory();
			NotificationsManager.checkReservedProducts();
		},"Loading Inventory...");
	}
	@Override
	public void onToTransactions(PanelTransactions transactions) {
		Window.load(() -> {
			transactions.loadAllFromTransactions();
		}, "Loading Transactions...");
	}
	@Override
	public void onToStore(PanelStore store) {
		Window.load(() -> {
			store.openCounter(0);
			store.openCart(store.getCounter().getCurrentCartNo(), store.getCounter().getCounterNo());
			store.loadAisleFromStore();
			store.loadCartFromStore(store.getCounter());
		}, "Loading Store...");
	}
	@Override
	public void onToReserves(PanelReserves reserves) {
		Window.load(() -> {
			reserves.loadAllFromReserves();
		}, "Loading Reserves...");
	}
	@Override
	public void onToDisposal(PanelDisposal disposals) {
		Window.load(() -> {
			disposals.loadAllFromDisposal();
		}, "Loading Disposals...");
	}
	@Override
	public void onToProductReturns(PanelProductReturns product_returns) {
		Window.load(() -> {
			product_returns.loadAllReturnedProducts();
		}, "Loading Product Returns...");
	}
	@Override
	public void onToStatistics() {
		
	}
	@Override
	public void onToCustomers(PanelCustomers customers) {
		Window.load(() -> {
			customers.loadAllFromCustomers();
		}, "Loading Customers...");
	}
	@Override
	public Panel nextPanel() {
		return next_panel;
	}
	
	public static void notifyTab(int n, boolean notify) {
		tabs[n].getDot().setShow(notify);
	}
}
