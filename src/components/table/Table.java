package components.table;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.CheckBox;
import components.Padding;
import components.Panel;
import components.scroll.ScrollPane;
import oop.interfaces.Theme;

public class Table extends JPanel implements Theme{
	private static final long serialVersionUID = -8446662030696532231L;
	private Table_Head table_header;
	private Table_Body table_body;

	public Table(Column columns[], Row rows[]) {
		setOpaque(false);
		setLayout(new BorderLayout(5, 5));
		
		table_header = new Table_Head(columns);
		add(new Padding(table_header, 0, 0, 0, 15), BorderLayout.NORTH);
		
		table_body = new Table_Body(rows);
		table_body.getVertical_scrollbar().setAutoHide(false);
		((BorderLayout)table_body.getLayout()).setHgap(5);
		((BorderLayout)table_body.getLayout()).setVgap(5);
		add(table_body, BorderLayout.CENTER);
	}
	public CheckBox getMainCheckBox() {
		return table_header.getCheckBox();
	}
	public Column getColumn(int col) {
		return table_header.getColumn(col);
	}
	public Row getRow(int row) {
		return table_body.getRow(row);
	}
	public Row[] getRows() {
		return table_body.getRows();
	}
	public int getRowCount() {
		return table_body.getRowCount();
	}
	public void addRow(Row row) {
		table_body.addRow(row);
	}
	public void addRows(Row rows[]) {
		table_body.addRows(rows);
	}
	public void removeRow(int r) {
		table_body.removeRow(r);
	}
	public void removeRow(Row row) {
		table_body.removeRow(row);
	}
	public void removeRows(Row rows[]) {
		table_body.removeRows(rows);
	}
	public void removeAllRows() {
		table_body.removeAllRows();
	}
	public void setSelectionEnabled(boolean enable) {
		table_body.setSelectionEnabled(enable);
	}
	public void setCheckBoxesEnabled(boolean enable) {
		table_header.setCheckBoxEnabled(enable);
		table_body.setCheckBoxesEnabled(enable);
	}
	
	private final class Table_Head extends Panel implements ItemListener{
		private static final long serialVersionUID = 7478862247310844785L;
		private CheckBox checkBox;
		private JPanel column_pane;
		
		public Table_Head(Column columns[]) {
			setArc(5);
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			setBackground(main_color[2]);
			setForeground(main_color[3]);

			checkBox = new CheckBox();
			checkBox.addItemListener(this);
			add(checkBox, BorderLayout.WEST);
			
			column_pane = new JPanel(new GridLayout(1, columns.length));
			column_pane.setOpaque(false);
			add(column_pane, BorderLayout.CENTER);
			
			for(Column column: columns) {
				column_pane.add(column);
			}
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			table_body.selectAllRows(e.getStateChange()==ItemEvent.SELECTED);
		}
		public void setCheckBoxEnabled(boolean enable) {
			checkBox.setVisible(enable);
		}
		public CheckBox getCheckBox() {
			return checkBox;
		}
		public Column getColumn(int col) {
			return (Column)column_pane.getComponent(col);
		}
	}
	private class Table_Body extends ScrollPane implements MouseListener, ItemListener{
		private static final long serialVersionUID = -3847386805619370183L;
		private int row_w, row_h, r, view_w, view_h;
		private ArrayList<Row> selected_rows;
		
		public Table_Body(Row rows[]) {
			super(new Panel());
			getPanelView().setBackground(main_color[2]);
			
			selected_rows = new ArrayList<Row>();
			getVertical_scrollbar().setAutoHide(false);
			getHorizontal_scrollbar().setAutoHide(true);

			for(Row row: rows) {
				addRow(row);
			}
			
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					row_w = getPanelView().getWidth();
					row_h = 30;
					
					view_w = row_w;
					view_h = row_h * getPanelView().getComponentCount();

					for(r=0; r<getRowCount(); r++) {
						getPanelView().getComponent(r).setBounds(0, getScrollY() + (row_h * r), row_w, row_h);
					}
				}
			});
		}
		@Override
		public int viewingWidth() {
			return view_w;
		}
		@Override
		public int viewingHeight() {
			return view_h;
		}
		@Override
		public void onScrollPanel(int scroll_x, int scroll_y) {
			for(r=0; r<getRowCount(); r++) {
				getRow(r).setBounds(0, getScrollY() + (row_h * r), row_w, row_h);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Row row = (Row)e.getSource();
			while(selected_rows.size() > 0) {
				selected_rows.get(0).setSelected(false);
			}
			row.setSelected(true);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void itemStateChanged(ItemEvent e) {
			Row row = (Row)((CheckBox)e.getSource()).getParent();
			switch(e.getStateChange()){
			case ItemEvent.SELECTED:   selected_rows.add(row);
				break;
			case ItemEvent.DESELECTED: selected_rows.remove(row);
				break;
			}
		}
		public void addRow(Row row) {
			row.addMouseListener(this);
			row.getCheckBox().addItemListener(this);
			
			getPanelView().add(row);
			
			align_rows();
		}
		public void addRows(Row rows[]) {
			for(Row row: rows) {
				addRow(row);
			}
		}
		public void removeRow(int r) {
			getPanelView().remove(r);
			align_rows();
		}
		public void removeRow(Row row) {
			getPanelView().remove(row);
			align_rows();
		}
		public void removeRows(Row rows[]) {
			for(Row row: rows) {
				removeRow(row);
			}
		}
		public void removeAllRows() {
			getPanelView().removeAll();
			align_rows();
		}
		public Row getRow(int row) {
			return (Row)getPanelView().getComponent(row);
		}
		public Row[] getRows() {
			Row row[] = new Row[getRowCount()];
			for(int r=0; r<row.length; r++) {
				row[r] = getRow(r);
			}
			return row;
		}
		public int getRowCount() {
			return getPanelView().getComponentCount();
		}
		public void selectAllRows(boolean flag) {
			int rows = getRowCount();
			for(int r=0; r<rows; r++) {
				getRow(r).setSelected(flag);
			}
		}
		public void setCheckBoxesEnabled(boolean enabled) {
			for(int r=0; r<getRowCount(); r++) {
				getRow(r).getCheckBox().setVisible(enabled);
			}
		}
		public void setSelectionEnabled(boolean enabled) {
			for(int r=0; r<getRowCount(); r++) {
				getRow(r).setSelectionEnabled(enabled);
			}
		}
		
		private void align_rows() {
			row_w = getPanelView().getWidth();
			row_h = 30;
			
			view_w = row_w;
			view_h = row_h * (getRowCount() + 1);
			
			for(int r=0; r<getRowCount(); r++) {
				getRow(r).setBounds(0, getScrollY() + (row_h * r), row_w, row_h);
			}
			
			if(getRootPane() != null) {
				getRootPane().revalidate();
				getRootPane().repaint();
			}
		}
	}
}
