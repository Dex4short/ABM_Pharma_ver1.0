package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import components.Button;
import components.panels.Panel;
import components.panels.SearchPanel;
import components.table.Table;
import oop.interfaces.Theme;

public class UI1 extends Panel implements Theme{
	private static final long serialVersionUID = 5294758605465387431L;
	private Table table;
	private SearchPanel search_panel;
	private Panel header, header_left, header_right;

	public UI1() {		
		setLayout(new BorderLayout(5,5));
		setOpaque(false);
		
		header = new Panel();
		header.setLayout(new BorderLayout());
		add(header, BorderLayout.NORTH);
		
		header_left = new Panel();
		header_left.setLayout(new FlowLayout(FlowLayout.LEFT));
		header.add(header_left, BorderLayout.WEST);
		
		header_right = new Panel();
		header_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.add(header_right, BorderLayout.EAST);

		search_panel = new SearchPanel() {
			private static final long serialVersionUID = 2196118624906740161L;
			@Override
			public void onSearch(String category, String word) {}
		};
		header_left.add(search_panel);
		
		table = new Table(new String[] {"A", "B", "C", "D", "E"});
		add(table, BorderLayout.CENTER);
		
	}
	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(!aFlag) {
			search_panel.getComboBox().close();
		}
	}
	public SearchPanel getSearchPanel() {
		return search_panel;
	}
	public void setSearchPanel(SearchPanel search_panel) {
		header_left.remove(getSearchPanel());
		header_left.add(search_panel);
		this.search_panel = search_panel;
	}
	public void addButton(Button button) {
		button.addMouseListener(closeSearchFilterAdapter());
		header_right.add(button);
	}
	public Button getButton(int n) {
		return (Button)header_right.getComponent(n);
	}
	public void removeButton(int n) {
		header_right.remove(n);
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		table.addMouseListener(closeSearchFilterAdapter());
		remove(getTable());
		add(table, BorderLayout.CENTER);
		this.table = table;
	}
	public MouseListener closeSearchFilterAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				search_panel.getComboBox().close();
			}
		};
	}
}
