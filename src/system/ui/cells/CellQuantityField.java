package system.ui.cells;

import javax.swing.BorderFactory;

import components.fields.TwinNumericField;
import components.table.Cell;
import oop.Quantity;

public class CellQuantityField extends Cell{
	private static final long serialVersionUID = -1866512012059963833L;
	private TwinNumericField twin_field;
	
	public CellQuantityField(String str1, String str2) {
		super(new TwinNumericField(str1, str2));
		setArc(0);
		
		twin_field = (TwinNumericField)getComponent(0);
		twin_field.maintainAspectRatio(1);
		twin_field.getLeftNumericField().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		twin_field.getRightNumericField().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public CellQuantityField(Quantity qty) {
		super(new TwinNumericField(qty.getAmount(), qty.getSize()));

		twin_field = (TwinNumericField)getComponent(0);
		twin_field.maintainAspectRatio(1);
		twin_field.getLeftNumericField().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		twin_field.getRightNumericField().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public TwinNumericField getTwinNumericField() {
		return twin_field;
	}
	public void setTwinNumericField(TwinNumericField twin_field) {
		this.twin_field = twin_field;
	}
	public Quantity getQuantity() {
		int 
		qty = twin_field.getLeftNumericField().getNumber(),
		size = twin_field.getRightNumericField().getNumber();
		return new Quantity(qty, size);
	}
	public void setQuantity(Quantity quantity) {
		twin_field.getLeftNumericField().setNumber(quantity.getAmount());
	}
}
