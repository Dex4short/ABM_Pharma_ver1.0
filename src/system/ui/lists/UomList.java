package system.ui.lists;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import components.Label;
import components.list.Item;
import components.list.ListPane;
import components.panels.Panel;
import oop.Uom;

public abstract class UomList extends ListPane{
	private static final long serialVersionUID = 3237075334125591989L;

	public UomList(Uom uom) {
		setArc(6);
		setItemHeight(30);
		setForeground(main_color[3]);
		
		createUomList(uom);
	}
	@Override
	public void addItem(Item item) {
		super.addItem(item);
		item.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectUom(((UomItem)item).getUom());
			}
		});
	}
	public void createUomList(Uom uom) {
		removeAllItems();
		
		Uom uom_node = uom;
		String mainUom_size, mainUom_name, subUom_size="", subUom_name="", description1, description2, divider="";
		
		do {
			mainUom_size = subUom_size;
			mainUom_name = subUom_name;
			
			subUom_size = uom_node.getUnitSize() + " ";
			subUom_name = uom_node.getUnitType().name();

			description1 = mainUom_size + mainUom_name;
			description2 = subUom_size + subUom_name;
			subUom_size = "1 ";

			addItem(new UomItem(uom_node, description1 + divider + description2));
			uom_node = uom_node.getSubUom();
			
			divider = " : ";
		}while(uom_node != null); 
	}
	public void selectUom(Uom uom) {
		onSelectUom(uom);
	}
	
	public abstract void onSelectUom(Uom uom);
	
	private class UomItem extends Item{
		private static final long serialVersionUID = 6921346333317608248L;
		private Uom uom;
		
		public UomItem(Uom uom, String uom_description) {
			super(createItemComponent(uom.getUnitType().name(), uom_description));
			setUom(uom);
		}
		private static Panel createItemComponent(String uom_name, String uom_description) {
			Panel panel = new Panel();
			panel.setBackground(new Color(0,0,0,0));
			panel.setLayout(new GridLayout(1, 2));
			panel.add(new Label(uom_name));
			panel.add(new Label(uom_description)).setForeground(text_color[1]);
			return panel;
		}
		public Uom getUom() {
			return uom;
		}
		public void setUom(Uom uom) {
			this.uom = uom;
		}
	}
}
