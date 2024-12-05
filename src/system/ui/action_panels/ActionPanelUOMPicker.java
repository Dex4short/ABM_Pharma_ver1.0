package system.ui.action_panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

import components.Label;
import components.Panel;
import components.Stepper;
import components.fields.NumericField;
import components.list.Item;
import components.list.ListPane;
import components.panels.ActionPanel;
import oop.Uom;
import oop.enums.UomType;
import oop.interfaces.UomPresets;

public abstract class ActionPanelUOMPicker extends ActionPanel implements UomPresets{
	private static final long serialVersionUID = 8885051793237377183L;
	private Panel header_panel, field_panel;
	private MainUomPanel mainUom_panel;
	private SubUomPanel subUom_panel;
	private Uom selected_uom;

	public ActionPanelUOMPicker() {
		super(new Label("Unit of Measure"));
		setArc(20);
		
		getPanelBody().setLayout(new BorderLayout());
		
		Panel north_panel = new Panel();
		north_panel.setLayout(new GridLayout(2, 1));
		north_panel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		getPanelBody().add(north_panel, BorderLayout.NORTH);
		
		header_panel = new Panel();
		header_panel.setLayout(new GridLayout(1,2));
		header_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		header_panel.add(createLabel("Main UOM"));
		header_panel.add(createLabel("Sub UOM"));
		north_panel.add(header_panel);
		
		field_panel = new Panel();
		field_panel.setLayout(new GridLayout(1,2));
		field_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		field_panel.setBackground(main_color[3]);
		field_panel.add(createLabel("box"));
		field_panel.add(createLabel(": none"));
		north_panel.add(field_panel);
		
		Panel center_panel = new Panel();
		center_panel.setLayout(new GridLayout(0, 2, 10, 10));
		center_panel.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		getPanelBody().add(center_panel, BorderLayout.CENTER);
		
		mainUom_panel = new MainUomPanel();
		center_panel.add(mainUom_panel);

		subUom_panel = new SubUomPanel();
		center_panel.add(subUom_panel);

		setSelectedUom(Uom_Preset[0]);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				getRootPane().repaint();
				getRootPane().revalidate();
			}
		});
	}
	@Override
	public void onOk() {
		onUomOk(getSelectedUom());
	}
	@Override
	public void onCancel() {
		onUomCancel();
	}
	public Uom getSelectedUom() {
		prepareIds(selected_uom);
		return selected_uom;
	}
	public void setSelectedUom(Uom uom) {
		JLabel uom_lbl1 = (JLabel)field_panel.getComponent(0);
		JLabel uom_lbl2 = (JLabel)field_panel.getComponent(1);
		
		String
		main_uom = uom.getUnitType().toString(),
		sub_uoms = "";
		
		Uom uom_node = uom;
		
		subUom_panel.removeAllItems();
		while(uom_node.getSubUom() != null) {
			uom_node = uom_node.getSubUom();
			
			sub_uoms += ": " + uom_node.getUnitType() + " ";
			subUom_panel.addSubUomItem(uom_node);
		}
		
		uom_lbl1.setText(main_uom);
		if(sub_uoms.equals("")) {
			subUom_panel.addItem(new Item(new Label("none")));
			uom_lbl2.setText(": none");
		}
		else {
			uom_lbl2.setText(sub_uoms);
		}
		
		selected_uom = uom;		
	}
	
	public abstract void onUomOk(Uom selectedUom);
	public abstract void onUomCancel();

	private final JLabel createLabel(String str) {
		JLabel lbl = new JLabel(str);
		lbl.setFont(font[0]);
		lbl.setForeground(main_color[4]);
		return lbl;
	}
	
	public class MainUomPanel extends ListPane{
		private static final long serialVersionUID = 8252498005827473422L;
		
		public MainUomPanel(){
			setForeground(main_color[3]);
			setArc(10);
			
			Uom uom;
			
			String
			main_uom = "",
			sub_uoms = "";
			
			int i;
			for(i=0; i<Uom_Preset.length; i++) {
				uom = Uom_Preset[i];
				
				main_uom = uom.getUnitType().toString();
				while(uom.getSubUom() != null) {
					uom = uom.getSubUom();
					sub_uoms += (": " + uom.getUnitType() + " ");
				}
				addItem(new UomItem(main_uom + " " + (sub_uoms.equals("") ? "" : sub_uoms), Uom_Preset[i]));
				
				main_uom = "";
				sub_uoms = "";
			}
		}
		
		private class UomItem extends Item{
			private static final long serialVersionUID = 6778346943833204792L;
			
			public UomItem(String str, Uom uom) {
				super(new Label(str));
				
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setSelectedUom(uom);
					}
				});
			}
		}
	}
	
	public class SubUomPanel extends ListPane{
		private static final long serialVersionUID = 8252498005827473422L;
		{
			setForeground(main_color[3]);
			setArc(10);
			
			addItem(new Item(new Label("none")));
			setSelectionEnabled(false);
		}
		public void addSubUomItem(Uom subUom) {
			addItem(new SubUomItem(subUom, subUom.getUnitSize()));
		}
		
		private class SubUomItem extends Item{
			private static final long serialVersionUID = -5248387306150387785L;
			
			public SubUomItem(Uom subUom, int size_value) {
				super(new Panel() {
					private static final long serialVersionUID = 5328811090212419153L;
					
					{
						setLayout(new GridLayout(1,2));
						
						
						Label unitName_label = new Label(subUom.getUnitType().toString());
						add(unitName_label);
						
						JComponent unitSize_field;
						UomType uom_type = subUom.getUnitType();
						if(uom_type==UomType.capsule || uom_type==UomType.tablet) {
							unitSize_field = new Stepper(Uom_Sizes, (size_value/2) - 2) {
								private static final long serialVersionUID = 1L;
								@Override
								public void onIncrement(String selectedIterationValue) {
									subUom.setUnitSize(Integer.parseInt(selectedIterationValue));
								}
								@Override
								public void onDecrement(String selectedIterationValue) {
									subUom.setUnitSize(Integer.parseInt(selectedIterationValue));
								}
							};
						}
						else {
							NumericField num_field = new NumericField(size_value + "");
							num_field.addKeyListener(new KeyAdapter() {
								@Override
								public void keyReleased(KeyEvent e) {
									subUom.setUnitSize(num_field.getNumber());
								}
							});
							//num_field.setArc(5);
							num_field.setCharacterLimit(4);
							unitSize_field = num_field;
						}
						add(unitSize_field);
					}
				});
				
			}
		}
	}
}
