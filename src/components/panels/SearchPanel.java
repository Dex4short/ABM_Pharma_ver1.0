package components.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;

import components.Button;
import components.ComboBox;
import components._misc_.Graphix;
import components.fields.TextField;
import res.Resource;

public abstract class SearchPanel extends Panel{
	private static final long serialVersionUID = 5613802306658005888L;
	private ComboBox combo_button;
	private TextField search_field;
	private Button search_button;

	public SearchPanel() {
		setLayout(new BorderLayout(2,2));
		setArc(30);
		setForeground(main_color[3]);
		setPreferredSize(new Dimension(360, 30));
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		combo_button = new ComboBox();
		combo_button.setArc(26);
		combo_button.setHorizontalAlignment(ComboBox.LEFT);
		combo_button.setIcon(Graphix.alterImageIcon(Resource.get("filter.png"), combo_button.getForeground(), combo_button));
		combo_button.addMouseListener(new ComboButtonMouseListener());
		combo_button.setPreferredSize(new Dimension(120, 30));
		combo_button.setMinimumSize(getPreferredSize());
		combo_button.setMaximumSize(getPreferredSize());
		add(combo_button, BorderLayout.WEST);
		
		search_field = new TextField();
		search_field.setColumns(10);
		add(search_field, BorderLayout.CENTER);
		
		search_button = new Button();
		search_button.setArc(26);
		search_button.setIcon(Graphix.alterImageIcon(Resource.get("search.png"), main_color[2], search_button));
		search_button.setBorder(BorderFactory.createEmptyBorder(0,23,0,23));
		search_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search(combo_button.getText(), search_field.getText());
			}
		});
		add(search_button, BorderLayout.EAST);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				getRootPane().revalidate();
				getRootPane().repaint();
			}
		});
	}
	public ComboBox getComboBox() {
		return combo_button;
	}
	public TextField getSearchField() {
		return search_field;
	}
	public Button getSearchButton() {
		return search_button;
	}
	public void setComboBox(ComboBox combo_button) {
		this.combo_button = combo_button;
	}
	public void setSearchField(TextField search_field) {
		this.search_field = search_field;
	}
	public void setSearchButton(Button search_button) {
		this.search_button = search_button;
	}
	public void setFilters(String filters[]) {
		combo_button.setItems(filters);
	}
	public void closeSearchFilter() {
		combo_button.close();
	}
	public void search(String category, String word) {
		onSearch(category, word);
		getComboBox().close();
	}
	
	public abstract void onSearch(String category, String word);
	
	private class ComboButtonMouseListener extends MouseAdapter{
		private Icon pressed, released, entered, exited;
		
		public ComboButtonMouseListener() {
			pressed = Graphix.alterImageIcon(Resource.get("filter.png"), combo_button.getForeground(), combo_button);
			released = Graphix.alterImageIcon(Resource.get("filter.png"), combo_button.getForeground(), combo_button);
			entered = Graphix.alterImageIcon(Resource.get("filter.png"), combo_button.getForeground(), combo_button);
			exited = Graphix.alterImageIcon(Resource.get("filter.png"), combo_button.getForeground(), combo_button);
		}
		@Override
		public void mousePressed(MouseEvent e) {
			combo_button.setIcon(pressed);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			combo_button.setIcon(released);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			combo_button.setIcon(entered);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			combo_button.setIcon(exited);
		}
	}
}
