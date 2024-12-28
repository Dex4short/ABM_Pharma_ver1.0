package system.ui.cells.labeling;


import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;

import components.Label;
import components.drawables.Dot;
import components.table.Cell;
import system._default_.Settings;
import system.objects.Quantity;

public class CellLabelQuantity extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;
	private Quantity quantity;
	private Dot dot;

	public CellLabelQuantity(Quantity quantity) {
		super(new Label(quantity.getAmount() + "/" + quantity.getSize()));
		setLabel((Label)getComponent(0));
		setQuantity(quantity);
		setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		setDot(new Dot());
	}
	private Graphics2D g2d;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		dot.draw(g2d);
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public void setLabelText(String txt) {
		label.setText(txt);
	}
	public void getLabelText() {
		label.getText();
	}
	public Quantity getQuantity() {
		return quantity;
	}
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	public Dot getDot() {
		return dot;
	}
	public void setDot(Dot dot) {
		this.dot = dot;
	}
}
