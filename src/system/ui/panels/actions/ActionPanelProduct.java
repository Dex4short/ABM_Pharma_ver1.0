package system.ui.panels.actions;


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
import components.table.Row;
import components.table.Table;
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
import oop.essentials.Accountancy;
import system.ui.Window;
import system.ui.buttons.ButtonUomPicker;
import system.ui.cells.CellButtonDatePicker;
import system.ui.cells.CellButtonUomPicker;
import system.ui.cells.CellDecimalField;
import system.ui.cells.CellLabelAmount;
import system.ui.cells.CellPercentageField;
import system.ui.cells.CellQuantityField;
import system.ui.cells.CellTextField;
import system.ui.tables.TableProducts;

public abstract class ActionPanelProduct  extends ActionPanel{
	private static final long serialVersionUID = -4880139382406239412L;
	private Table table;
	private Row rows[];
	private ActionPanelUOMPicker uom_picker;
	
	public ActionPanelProduct() {
		super(new Label(null, "Product"));
		setArc(20);
		
		table = new Table(TableProducts.fields);
		table.setSelectionEnabled(false);
		table.setCheckBoxesEnabled(false);
		getPanelBody().add(table, BorderLayout.CENTER);
		
		setProductSet(new Product[] {new Product(), null, null});
	}
	@Override
	public void onOk() {
		productOk();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public Product[] getProductSet() throws Exception{
		Product products[] = new Product[rows.length];
		for(int r=0; r<rows.length; r++) {
			if(rows[r].isVisible()) {
				products[r] = getProduct(r);
			}
			else {
				products[r] = null;
			}
		}
		return products;
	}
	public void setProductSet(Product products[]) {
		table.removeAllRows();
		
		rows = new Row[3];
		rows[0] = new ProductRow(products[0]);
		table.addRow(rows[0]);
		
		int r;
		for(r=1; r<products.length; r++) {
			rows[r] = new ProductRow((products[r]!=null) ? products[r] : new Product());
			for(int c=0; c<7; c++) {
				rows[r].setCell(new Cell(), c);
			}
			table.addRow(rows[r]);
			
			if(products[r]==null) rows[r].setVisible(false);
		}
		
		uom_picker.setSelectedUom(products[0].getPackaging().getUom());
	}
	public Product getProduct(int row) throws Exception{
		String
		item_no = ((CellTextField)rows[0].getCell(0)).getText(),//limit to 8 characters
		description = ((CellTextField)rows[0].getCell(1)).getText(),//limit to 64 characters
		lot_no = ((CellTextField)rows[0].getCell(2)).getText(),//limit to 8 characters
		brand = ((CellTextField)rows[0].getCell(5)).getText();//limit to 32 characters
		
		if(item_no.length() > 8) {
			throw new Exception("Item No. should not exceed to 8 characters");
		}
		else if(item_no.length() == 0) {
			throw new Exception("Item No. is empty");
		}
		else if(description.length() > 64) {
			throw new Exception("Description should not exceed to 64 characters");
		}
		else if(description.length() == 0) {
			throw new Exception("Description is empty");
		}
		else if(lot_no.length() > 8) {
			throw new Exception("Lot No. should not exceed to 8 characters");
		}
		else if(lot_no.length() == 0) {
			throw new Exception("Lot No. is empty");
		}
		else if(brand.length() > 32) {
			throw new Exception("Brand name should not exceed to 32 characters");
		}
		else if(brand.length() == 0) {
			throw new Exception("Brand Name is empty");
		}	
		else if(((CellButtonDatePicker)rows[0].getCell(4)).getButtonDatePicker().getText().equals("yyyy-mm-dd")) {
			throw new Exception("Please set expiry date");
		}
		
		Date
		date_added = ((CellButtonDatePicker)rows[0].getCell(3)).getDate(),
		exp_date = ((CellButtonDatePicker)rows[0].getCell(4)).getDate();
		
		Quantity qty = ((CellQuantityField)rows[0].getCell(6)).getQuantity();
		Uom	uom = ((CellButtonUomPicker)rows[0].getCell(7)).getButtonUomPicker().getUom();
		
		if(qty.getQuantity() == 0) { 
			throw new Exception("Please set Quantity value");
		}
		else if(uom == null) {
			throw new Exception("Please set UOM");
		}
		
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
		
		if(cost.toString().equals("0.00")) {
			throw new Exception("Cost amount is zero");
		}
		else if(unit_amount.toString().equals("0.00")) {
			throw new Exception("Unit Amount is zero");
		}
		
		return new Product(
				-1,
				new Item(-1, item_no, description, lot_no, date_added, exp_date, brand), 
				new Packaging(-1, qty, uom, -1),
				new Pricing(-1, cost, unit_price, discount, unit_amount), 
				new Remarks(-1)
		);
	}
	public void productOk() {
		try {
			onProductOk(getProductSet());
			Window.getStackPanel().popPanel();
		} catch (Exception e) {
			Window.floatMessage(e.getMessage());
			//e.printStackTrace();
		}
	}	
	
	public abstract void onProductOk(Product product_set[]);
	
	private class ProductRow extends Row{
		private static final long serialVersionUID = -5000559444226743025L;
		private static final int Cell_dateAdded=3, Cell_Uom=7, Cell_Cost=8 ,Cell_Unit_Price=9, Cell_Percentage=10, Cell_Amount=11;
		
		public ProductRow(Product product) {
			super(new Cell[] {
					new CellTextField(product.getItem().getItemNo()),
					new CellTextField(product.getItem().getDescription()),
					new CellTextField(product.getItem().getLotNo()),
					new CellButtonDatePicker(product.getItem().getDateAdded()),
					new CellButtonDatePicker(product.getItem().getExpDate()),
					new CellTextField(product.getItem().getBrand()),
					new CellQuantityField(product.getPackaging().getQty()),
					new CellButtonUomPicker(product.getPackaging().getUom()),
					new CellDecimalField(product.getPricing().getCost()),
					new CellDecimalField(product.getPricing().getUnitPrice()),
					new CellPercentageField(product.getPricing().getDiscount()),
					new CellLabelAmount(product.getPricing().getUnitAmount())
			});
			((CellButtonDatePicker)getCell(Cell_dateAdded)).getButtonDatePicker().setAutoCheckExpiry(false);
			
			ButtonUomPicker btn_uom_picker = ((CellButtonUomPicker)getCell(Cell_Uom)).getButtonUomPicker();
			DecimalField unit_cost_field = ((CellDecimalField)getCell(Cell_Cost)).getDecimalField();
			DecimalField unit_price_field = ((CellDecimalField)getCell(Cell_Unit_Price)).getDecimalField();
			PercentageField discount_field = ((CellPercentageField)getCell(Cell_Percentage)).getPercentageField();
			Label unit_amount_label = ((CellLabelAmount)getCell(Cell_Amount)).getLabel();
			
			if(uom_picker == null) {
				uom_picker = btn_uom_picker.getUomPicker();
				uom_picker.getButtonOk().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Uom uom = btn_uom_picker.getUomPicker().getSelectedUom();
						
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
				btn_uom_picker.setUomPicker(uom_picker);
			}
			
			unit_cost_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						Decimal.isFormatValid(unit_cost_field.getText());
						unit_cost_field.setForeground(text_color[0]);
					} catch (Exception ex) {
						unit_cost_field.setForeground(accent_color[0][3]);
					}
				}
			});
			
			unit_price_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						unit_amount_label.setText(Accountancy.calculateUnitAmount(unit_price_field.getDecimal(), discount_field.getPercent()).toString());
						unit_price_field.setForeground(text_color[0]);
					} catch (Exception ex) {
						//ex.printStackTrace();
						unit_price_field.setForeground(accent_color[0][3]);
					}
				}
			});
			
			discount_field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					try {
						unit_amount_label.setText(Accountancy.calculateUnitAmount(unit_price_field.getDecimal(), discount_field.getPercent()).toString());
						discount_field.setForeground(text_color[0]);
					}catch (Exception ex) {
						//ex.printStackTrace();
						discount_field.setForeground(accent_color[0][3]);
					}
				}
			});
		}
	}
}
