package system.ui.panels.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import components.Label;
import components.fields.NumericField;
import components.panels.ActionPanel;
import components.panels.Panel;
import oop.Packaging;
import oop.Quantity;
import oop.Uom;
import oop.enums.PackagingLine;
import system.ui.Window;
import system.ui.lists.UomList;

public abstract class ActionPanelPackage extends ActionPanel{
	private static final long serialVersionUID = -242917752204373917L;
	private UomLabel uom_label;
	private QuantityField qty_field;
	private UomList uom_list;
	private Packaging main_pack, sub_pack;
	
	public ActionPanelPackage(Packaging pack) {
		super("Product Package");
		setArc(20);
		getPanelBody().setLayout(new BorderLayout(5,5));
		
		Panel head = new Panel();
		head.setLayout(new GridLayout(2, 1, 5, 5));
		getPanelBody().add(head, BorderLayout.NORTH);
		
		Panel panel_label = new Panel();
		panel_label.setArc(6);
		panel_label.setForeground(main_color[3]);
		panel_label.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		panel_label.setLayout(new GridLayout(1, 2));
		panel_label.add(new Label("UOM : " + pack.getUom().getUnitType().name()));
		panel_label.add(new Label("QTY : " + pack.getQty().toString()));
		head.add(panel_label);
		
		Panel panel_field = new Panel();
		panel_field.setArc(6);
		panel_field.setBackground(main_color[3]);
		panel_field.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel_field.setLayout(new GridLayout(1, 2));
		head.add(panel_field);
		
		uom_label = new UomLabel(pack.getUom());
		panel_field.add(uom_label);
		
		qty_field = new QuantityField(pack.getQty());
		panel_field.add(qty_field);
		
		uom_list = new UomList(pack.getUom()) {
			private static final long serialVersionUID = -6239345212236450519L;
			@Override
			public void onSelectUom(Uom uom) {
				getUomLabel().setUom(uom);
			}
		};
		uom_list.setSelectedItem(0);
		getPanelBody().add(uom_list, BorderLayout.CENTER);
		
		setMainPackaging(pack);
	}
	@Override
	public void onOk() {
		try {
			setSubPackaging(new Packaging(-1, qty_field.getQty(), uom_label.getUom(), -1, PackagingLine.Descendant, -1));
			packageOk(getMainPackaging(), getSubPackaging());
			Window.getStackPanel().popPanel(this);
		} catch (Exception e) {
			Window.floatMessageAndBeep(e.getMessage());
			e.printStackTrace();
		}
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void setUomLabel(UomLabel uom_label) {
		this.uom_label = uom_label;
	}
	public void setQtyField(QuantityField qty_field) {
		this.qty_field = qty_field;
	}
	public void setMainPackaging(Packaging main_pack) {
		this.main_pack = main_pack;
	}
	public void setSubPackaging(Packaging sub_pack) {
		this.sub_pack = sub_pack;
	}
	public QuantityField getQtyField() {
		return qty_field;
	}
	public UomLabel getUomLabel() {
		return uom_label;
	}
	public Packaging getMainPackaging() {
		return main_pack;
	}
	public Packaging getSubPackaging() {
		return sub_pack;
	}
	public void packageOk(Packaging main_pack, Packaging sub_pack) {
		onPackageOk(main_pack, sub_pack);
	}

	public abstract void onPackageOk(Packaging main_pack, Packaging sub_pack);
	
	private class UomLabel extends Label{
		private static final long serialVersionUID = -3681630256932707753L;
		private Uom uom;
		
		public UomLabel(Uom uom) {
			setUom(uom);
		}
		public Uom getUom() {
			return uom;
		}
		public void setUom(Uom uom) {
			setText(uom.getUnitType().name());
			this.uom = uom;
		}
	}
	private class QuantityField extends NumericField{
		private static final long serialVersionUID = -7309132666949436633L;
		private Quantity qty;
		
		public QuantityField(Quantity qty) {
			super(qty.getQuantity());
			setHorizontalAlignment(NumericField.LEFT);
			setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			setBackground(main_color[2]);
			setQty(qty);
		}
		public Quantity getQty() {
			Uom uom = getMainPackaging().getUom();
			
			int 
			quantity = getNumber(),
			scale = 1;
			
			if(uom.getUnitType() != getUomLabel().getUom().getUnitType()) {
				while(uom != null) {
					scale *= uom.getUnitSize();
					
					if(uom.getUnitType() == getUomLabel().getUom().getUnitType()) break;
					uom = uom.getSubUom();
				}
			}
			
			if(quantity > (qty.getQuantity() * scale)) throw new RuntimeException("Insufficient product quantity");
			else if(quantity == 0) throw new RuntimeException("Please set product quantity");
			return new Quantity(quantity);
		}
		public void setQty(Quantity qty) {
			setNumber(qty.getQuantity());
			this.qty = qty;
		}
	}
}
