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
import components._misc_.interfaces.Task;
import components.panels.Panel;
import components.tab.Tab;
import components.tab.TabPane;
import res.Resource;
import system._default_.Administrator;
import system.managers.NotificationsManager;
import system.ui.Window;
import system.ui.buttons.accessibility.ButtonNotifications;
import system.ui.buttons.accessibility.ButtonSettings;
import system.ui.panels.sub_panels.PanelCustomers;
import system.ui.panels.sub_panels.PanelDisposal;
import system.ui.panels.sub_panels.PanelEmployees;
import system.ui.panels.sub_panels.PanelInventory;
import system.ui.panels.sub_panels.PanelItems;
import system.ui.panels.sub_panels.PanelProductReturns;
import system.ui.panels.sub_panels.PanelReserves;
import system.ui.panels.sub_panels.PanelStore;
import system.ui.panels.sub_panels.PanelTransactions;

public class PanelAdmin extends Panel implements Administrator{
	public static final int Inventory=0, Items=1, Transactions=2, Store=3, Reserves=4, Disposal=5, Product=6, Returns=7, Customers=8;
	private static final long serialVersionUID = 4864384612729816588L;
	private static Tab tabs[];
	private Panel panels[], next_panel;
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

		panels = new Panel[] {
			new PanelInventory(),
			new PanelItems(),
			new PanelTransactions(),
			new PanelStore(),
			new PanelReserves(),
			new PanelDisposal(),
			new PanelProductReturns(),
			new PanelCustomers(),
			new PanelEmployees()
		};
		
		String panel_labels[] = {
			"Inventory",
			"Items",
			"Transactions",
			"Store",
			"Reserves",
			"Disposal",
			"Product Returns",
			"Customers",
			"Employees"
		};
		
		Task directories[] = {
			() -> toInventory(),
			() -> toItems(),
			() -> toTransactions(),
			() -> toStore(),
			() -> toResreves(),
			() -> toDisposal(),
			() -> toProductReturns(),
			() -> toCustomers(),
			() -> toEmployees(),
		};
		
		tabs = new Tab[panels.length];
		for(int t=0; t<tabs.length; t++) {
			final int T=t;
			tabs[t] = new Tab(panel_labels[t], panels[t]);
			tabs[t].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					next_panel = panels[T];
					directories[T].perform();
				}
			});
			tab_pane.addTab(tabs[t]);
		}
		tab_pane.setSelectedTab(0);
		
		next_panel = panels[0];
	}
	@Override
	public void onToInventory(PanelInventory inventory) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			inventory.loadAllFromInventory();
			NotificationsManager.checkReservedProducts();
		},"Loading Inventory...");
	}
	@Override
	public void onToItems(PanelItems items) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			
		}, "Loading Items...");
	}
	@Override
	public void onToTransactions(PanelTransactions transactions) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			transactions.loadAllFromTransactions();
		}, "Loading Transactions...");
	}
	@Override
	public void onToStore(PanelStore store) {
		Window.load(() -> {
			store.enterCounter(0);
			store.openCart(store.getCounter().getCurrentCartNo(), store.getCounter().getCounterNo());
			store.loadAisleFromStore();
			store.loadCartFromStore(store.getCounter());
		}, "Loading Store...");
	}
	@Override
	public void onToReserves(PanelReserves reserves) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			reserves.loadAllFromReserves();
		}, "Loading Reserves...");
	}
	@Override
	public void onToDisposal(PanelDisposal disposals) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			disposals.loadAllFromDisposal();
		}, "Loading Disposals...");
	}
	@Override
	public void onToProductReturns(PanelProductReturns product_returns) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			product_returns.loadAllReturnedProducts();
		}, "Loading Product Returns...");
	}
	@Override
	public void onToStatistics() {
		((PanelStore)panels[Store]).exitCounter();
		
	}
	@Override
	public void onToCustomers(PanelCustomers customers) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			customers.loadAllFromCustomers();
		}, "Loading Customers...");
	}
	@Override
	public void onToEmployees(PanelEmployees employees) {
		Window.load(() -> {
			((PanelStore)panels[Store]).exitCounter();
			
		}, "Loading Employees...");
	}
	@Override
	public Panel nextPanel() {
		return next_panel;
	}
	
	public static void notifyTab(int n, boolean notify) {
		tabs[n].getDot().setShow(notify);
	}
}
