package system.printers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;

import system.objects.Product;
import system.printers.manipulations.GraphicsManipulation;
import system.printers.manipulations.NumberManipulation;

public class InventoryPrinter {
	private PrinterJob printer;
	private int w,h,pageRows,rowY,descWidth,printablePages;
	
	public InventoryPrinter() {
		printer = PrinterJob.getPrinterJob();
	}
	public void printItems(Object items[][]){
		printablePages = 1;
		printer.setPrintable(new Printable() {
			String str;
			int i = 0;
			@Override
			public int print(Graphics g, PageFormat pf, int page) throws PrinterException {	
				if(page == printablePages){
					return NO_SUCH_PAGE;
				}
				
				Graphics2D g2d = (Graphics2D)g;
				g2d.translate(pf.getImageableX() + 25, pf.getImageableY() + 50);
				w = (int)pf.getImageableWidth();
				h = (int)pf.getImageableHeight();
				
				g2d.setFont(new Font("Default",Font.PLAIN,8));
				g2d.drawString("#", 0, 0);
				g2d.drawString("Item No", 30, 0);
				g2d.drawString("Description", 75, 0);
				g2d.drawString("QTY", (w / 2) + 5, 0);
				g2d.drawString("UOM", (w / 2) + 35, 0);
				g2d.drawString("Cost", (w / 2) + 75, 0);
				g2d.drawString("Unit Price", (w / 2) + 120, 0);
				g2d.drawString("Discount", (w / 2) + 180, 0);
				g2d.drawString("Unit Amount", (w / 2) + 220, 0);
				
				pageRows = (h - 100) / 30;
				i        = pageRows * page;
				String date,exp;
				int row=0;
				while(i < items.length){		
					rowY = ((row + 1) * 30) - 10;
					
					str = (i + 1) + ".";
					g2d.drawString(str , 0, rowY);

					str = items[i][1]+"";
					g2d.drawString(str , 30, rowY);

					date = (String)items[i][4];
					exp  = (String)items[i][5];
					str = "Desc."    + items[i][2] + " "
						+ "Lot No. " + items[i][3] + " "
						+ "Date "    + date.substring(0,2)+"/"+date.substring(6,10) + " "
						+ "Exp. "    + exp.substring(0,2)+"/"+exp.substring(6,10) + " "
						+ "("        + items[i][6] + ")";
					descWidth   = (w / 2) - 100;
					GraphicsManipulation.drawWrapedString(str, descWidth, 75, rowY, g2d);
					
					str = items[i][7]+"";
					g2d.drawString(str , (w / 2) + 5, rowY);

					str = items[i][8]+"";
					GraphicsManipulation.drawCropedString(str, 40, (w / 2) + 35, rowY, g2d);
					
					str = NumberManipulation.toAccountancyFormat((BigDecimal)items[i][9]);
					g2d.drawString(str , ((w / 2) + 95) - g2d.getFontMetrics().stringWidth(str.substring(0,str.indexOf("."))), rowY);

					str = NumberManipulation.toAccountancyFormat((BigDecimal)items[i][10]);
					g2d.drawString(str , ((w / 2) + 155) - g2d.getFontMetrics().stringWidth(str.substring(0,str.indexOf("."))), rowY);
					
					str = items[i][11]+"";
					g2d.drawString(str , ((w / 2) + 200) - g2d.getFontMetrics().stringWidth(str), rowY);

					str = NumberManipulation.toAccountancyFormat((BigDecimal)items[i][12]);
					g2d.drawString(str , ((w / 2) + 250) - g2d.getFontMetrics().stringWidth(str.substring(0,str.indexOf("."))), rowY);
					
					i++;
					row++;
					if(row == pageRows){
						row = 0;
						printablePages = page + 2;
						break;
					}
				}
				
				return PAGE_EXISTS;
			}
		});
		
		boolean doPrint = printer.printDialog();
		
		if(doPrint){
			try {
				printer.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}
	public static void printInventory(Product products[]) {
		Object values[][] = new Object[products.length][13];
		
		for(int p=0; p<products.length; p++) {
			values[p][0] = products[p].getItem().getItemId();
			values[p][1] = products[p].getItem().getItemNo();
			values[p][2] = products[p].getItem().getDescription();
			values[p][3] = products[p].getItem().getLotNo();
			values[p][4] = products[p].getItem().getDateAdded().toString();
			values[p][5] = products[p].getItem().getExpDate().toString();
			values[p][6] = products[p].getItem().getBrand();
			values[p][7] = products[p].getPackaging().getQty().getAmount();
			values[p][8] = products[p].getPackaging().getUom().getUnitType().name();
			values[p][9] = products[p].getPricing().getCost().toBigDecimal();
			values[p][10] = products[p].getPricing().getUnitPrice().toBigDecimal();
			values[p][11] = products[p].getPricing().getDiscount().toString();
			values[p][12] = products[p].getPricing().getUnitAmount().toBigDecimal();
		}
		
		new InventoryPrinter().printItems(values);
	}
}
