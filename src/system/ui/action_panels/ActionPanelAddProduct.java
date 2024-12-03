package system.ui.action_panels;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import components.Label;
import components.fields.DecimalField;
import components.fields.PercentageField;
import components.panels.ActionPanel;
import components.table.Cell;
import components.table.Column;
import components.table.Row;
import components.table.Table;
import essentials.Accountancy;
import oop.Date;
import oop.Decimal;
import oop.Item;
import oop.Packaging;
import oop.Percentage;
import oop.Pricing;
import oop.Product;
import oop.Quantity;
import oop.Remarks;
import oop.Uom;
import oop.enums.ProductCondition;
import system.ui.Window;
import system.ui.buttons.ButtonUomPicker;
import system.ui.cells.CellButtonDatePicker;
import system.ui.cells.CellButtonUomPicker;
import system.ui.cells.CellDecimalField;
import system.ui.cells.CellLabel;
import system.ui.cells.CellLabelAmount;
import system.ui.cells.CellLabelDecimal;
import system.ui.cells.CellPercentageField;
import system.ui.cells.CellQuantityField;
import system.ui.cells.CellTextField;
import system.ui.tables.TableInventory;

public abstract class ActionPanelAddProduct  extends ActionPanel{
	private static final long serialVersionUID = -4880139382406239412L;
	private Row rows[];
	private ActionPanelUOMPicker uom_picker;
	
	public ActionPanelAddProduct() {
		super(new Label(null, "Add Product"));
		setArc(20);
		
		String fields[] = TableInventory.fields;
		Column columns[] = new Column[12];
		for(int c=0; c<columns.length; c++) {
			columns[c] = new Column(new Label(null, fields[c]));
		}
		
		rows = new Row[3];
		rows[0] = new ProductRow();
		rows[1] = new ProductRow();
		rows[2] = new ProductRow();
		
		for(int c=0; c<7; c++) {
			rows[1].setCell(new Cell(), c);
			rows[2].setCell(new Cell(), c);
		}
		rows[1].setVisible(false);
		rows[2].setVisible(false);
		
		Table table = new Table(columns, rows);
		table.setSelectionEnabled(false);
		table.setCheckBoxesEnabled(false);
		getPanelBody().add(table, BorderLayout.CENTER);
	}
	@Override
	public void onOk() {
		addProductOk();
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void addProductOk() {
		try {
			Product products[] = new Product[rows.length];
			for(int r=0; r<rows.length; r++) {
				if(rows[r].isVisible()) {
					products[r] = getProduct(r);
				}
				else {
					products[r] = null;
				}
			}

			onAddProductOk(products);
		} catch (Exception e) {
			Window.floatMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public abstract void onAddProductOk(Product products[]);
	
	private class ProductRow extends Row{
		private static final long serialVersionUID = -5000559444226743025L;
		private static final int Cell_Uom=7, Cell_Unit_Price=9, Cell_Percentage=10, Cell_Amount=11;
		
		public ProductRow() {
			super(new Cell[] {
					new CellTextField("0000"),
					new CellTextField("sample"),
					new CellTextField("0000"),
					new CellButtonDatePicker(),
					new CellButtonDatePicker("2025-09-21"),
					new CellTextField("xxxx"),
					new CellQuantityField("0","0"),
					new CellButtonUomPicker("set"),
					new CellDecimalField("0.00"),
					new CellDecimalField("0.00"),
					new CellPercentageField("0%"),
					new CellLabelAmount("0.00")
			});

			ButtonUomPicker btn_uom_icker = ((CellButtonUomPicker)getCell(Cell_Uom)).getButtonUomPicker();
			DecimalField decimal_field = ((CellDecimalField)getCell(Cell_Unit_Price)).getDecimalField();
			PercentageField percentage_field = ((CellPercentageField)getCell(Cell_Percentage)).getPercentageField();
			Label amount_label = ((CellLabelAmount)getCell(Cell_Amount)).getLabel();
			
			if(uom_picker == null) {
				uom_picker = btn_uom_icker.getUomPicker();
				uom_picker.getButtonOk().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Uom uom = btn_uom_icker.getUomPicker().getSelectedUom();
						
						for(int r=0; r<rows.length; r++) {
							if(uom != null) {
								((CellButtonUomPicker)rows[r].getCell(Cell_Uom)).getButtonUomPicker().setText(uom.getUnitType().name());
								uom = uom.getSubUom();
								rows[r].setVisible(true);
							}
							else {
								rows[r].setVisible(false);
							}
						}
					}
				});
			}
			else {
				btn_uom_icker.setUomPicker(uom_picker);
			}
			
			decimal_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					amount_label.setText(Accountancy.calculateUnitAmount(decimal_field.getDecimal(), percentage_field.getPercent()).toString());
				}
			});
			
			percentage_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					amount_label.setText(Accountancy.calculateUnitAmount(decimal_field.getDecimal(), percentage_field.getPercent()).toString());
				}
			});
		}
	}
	public Product getProduct(int row) throws Exception{
		String
		item_no = ((CellTextField)rows[0].getCell(0)).getText(),//limit to 8 characters
		description = ((CellTextField)rows[0].getCell(1)).getText(),//limit to 64 characters
		lot_no = ((CellTextField)rows[0].getCell(2)).getText(),//limit to 8 characters
		brand = ((CellTextField)rows[0].getCell(5)).getText();//limit to 32 characters
		
		Date
		date_added = ((CellButtonDatePicker)rows[0].getCell(3)).getDate(),
		exp_date = ((CellButtonDatePicker)rows[0].getCell(4)).getDate();//
		
		Quantity qty = ((CellQuantityField)rows[0].getCell(6)).getQuantity();
		Uom	uom = ((CellButtonUomPicker)rows[0].getCell(7)).getButtonUomPicker().getUom();
		
		for(int u=1; u<=row; u++) {
			uom = uom.getSubUom();
			qty = new Quantity(uom.getUnitSize(), uom.getUnitSize());
		}
		
		Percentage
		discount = ((CellPercentageField)rows[row].getCell(10)).getPercentage();
		
		Decimal
		cost = ((CellDecimalField)rows[row].getCell(8)).getDecimal(),
		unit_price = ((CellDecimalField)rows[row].getCell(9)).getDecimal(),
		unit_amount = new Decimal(((CellLabelAmount)rows[row].getCell(11)).getLabelText());
		
		//before passing product, add conditions
		
		return new Product(
				-1,
				new Item(-1, item_no, description, lot_no, date_added, exp_date, brand), 
				new Packaging(-1, qty, uom, -2),
				new Pricing(-1, cost, unit_price, discount, unit_amount), 
				new Remarks(-1)
		);
	}
}
