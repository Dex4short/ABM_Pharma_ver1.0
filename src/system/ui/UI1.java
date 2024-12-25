package system.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import components.Button;
import components.panels.Panel;
import components.panels.SearchPanel;
import components.table.Row;
import components.table.Table;
import oop.implementations.Theme;

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
		
		table = new Table(new String[] {"A", "B", "C", "D", "E"}) {
			private static final long serialVersionUID = 4351252363585860064L;
			@Override
			public void onSelectRow(Row row) {}
			@Override
			public void onPointRow(Row row) {}
		};
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
		remove(getTable());
		add(table, BorderLayout.CENTER);
		this.table = table;
	}
}
