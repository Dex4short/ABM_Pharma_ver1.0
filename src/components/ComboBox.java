package components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

import components.list.Item;
import components.list.ListPane;
import components.panels.Panel;
import extras.Settings;

public class ComboBox extends Button implements ActionListener{
	private static final long serialVersionUID = 2967464342223499866L;
	private ComboPane combo_pane;
	private boolean open;

	public ComboBox() {
		setArc(30);
		setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
		addActionListener(this);
		
		combo_pane = new ComboPane();
		combo_pane.setOpaque(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isOpen()) {
			close();
		}
		else {
			open();
		}
	}
	public Item addItem(Item item) {
		combo_pane.list_pane.addItem(item);
		item.addMouseListener(new ComboItemMouseAdapter());
		return item;
	}
	public void addItems(Item items[]) {
		for(Item item: items) {
			item.addMouseListener(new ComboItemMouseAdapter());
		}
		combo_pane.list_pane.addItems(items);
	}
	public void removeItem(int index) {
		combo_pane.list_pane.removeItem(index);
	}
	public void removeItem(Item item) {
		combo_pane.list_pane.removeItem(item);
	}
	public void removeItems() {
		combo_pane.list_pane.removeAllItems();
	}
	public Item getItem(int index) {
		return combo_pane.list_pane.getItem(index);
	}
	public Item[] getItems() {
		return combo_pane.list_pane.getItems();
	}
	public void setItems(Item items[]) {
		removeItems();
		addItems(items);
	}
	public void setItems(String[] str_items) {
		removeItems();
		Item items[] = new Item[str_items.length];
		for(int i=0; i<items.length; i++) {
			items[i] = new Item(str_items[i]);
		}
	}
	public boolean isOpen() {
		return open;
	}
	private int px=0, py=0;
	public void open() {
		px = getX();
		py = getY();
		Component parent = getParent();
		while(parent!=getRootPane()) {
			px += parent.getX();
			py += parent.getY();
			parent = parent.getParent();
		}
		
		combo_pane.setBounds(px, py + getHeight() + 5, getWidth(), 100);
		getRootPane().add(combo_pane, 0);
		getRootPane().revalidate();
		getRootPane().repaint();
		
		open = true;
	}
	public void close() {
		if(getRootPane() != null) {
			getRootPane().remove(combo_pane);
			getRootPane().revalidate();
			getRootPane().repaint();
		}
		open = false;
	}
	public void setSelectedItem(int index) {
		if(combo_pane.list_pane.getItemCount() == 0) {
			setText("");
			return;
		}
		combo_pane.list_pane.setSelectedItem(index);
		setText(combo_pane.list_pane.getItem(index).getName());
	}
	
	private final class ComboPane extends Panel{
		private static final long serialVersionUID = 265426238802373121L;
		private ListPane list_pane;
		private Graphics2D g2d;
		
		public ComboPane() {
			setArc(10);
			setLayout(new BorderLayout(5,5));
			setForeground(main_color[3]);
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			list_pane = new ListPane();
			add(list_pane, BorderLayout.CENTER);
			addMouseWheelListener(list_pane.getVertical_scrollbar());

			BorderLayout border_layout = (BorderLayout)list_pane.getLayout();
			border_layout.setHgap(5);
			
		}
		@Override
		public void paint(Graphics g) {
			g2d = (Graphics2D)g;
			Settings.rendering_hint(g2d);
			
			super.paint(g2d);
		}
	}
	private class ComboItemMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			setText(((Item)e.getSource()).getName());
			close();
		}
	}
}
