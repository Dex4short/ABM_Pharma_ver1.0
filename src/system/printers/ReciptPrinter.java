package system.printers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;

import system.objects.Order;
import system.objects.Transaction;
import system.printers.manipulations.GraphicsManipulation;
import system.printers.manipulations.NumberManipulation;
import system.printers.tools.Accountancy;
import system.printers.tools.Precision;


public class ReciptPrinter {
	private PrinterJob printerJob;
	private String customer_info[];
	private Object purchases[][];
	private float row_height = 300f / 22f;
	private final int cell_x [] = { 0, 30, 60, 260, 310};//,240,280
	private int cell_y,percentage,pages,pageCount,length;
	private BigDecimal sum;
	private double quotient,floor;
	
	public ReciptPrinter(){
		printerJob    = PrinterJob.getPrinterJob();	
		customer_info = new String[5]; 
	}
	public void prepareRecipt(int pages,int purchaseCount){
		purchases  = new Object[purchaseCount][5];
		this.pages = pages;
		quotient = Precision.divide(purchaseCount, 10);
	}
	public void setCustomerInfo(String customerInfo[]){
		customer_info = customerInfo;
	}
	public void insertPurchase(int index,Object purchase[]){
		purchases[index] = purchase;
	}
	public boolean print(){		
		printerJob.setPrintable(new Printable() {
			@Override
			public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
				pageCount = page;
				if(page == pages){
					pages = 0;
					return NO_SUCH_PAGE;
				}
				
				Graphics2D g2d = (Graphics2D)g;
				g2d.translate(pf.getImageableX(), pf.getImageableY());
				g2d.setFont(new Font("Default",Font.PLAIN,8));

				drawCustomerInfo(g2d);
				drawPurchases(g2d);
				drawTotalAmount(g2d);
				
				return PAGE_EXISTS;
			}
		});

		boolean doPrint = printerJob.printDialog();
		
		if(doPrint){
			try {
				printerJob.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}

		return doPrint;
	}
	private void drawCustomerInfo(Graphics2D g2d){
		g2d.drawString(customer_info[0],  75, 125);
		g2d.drawString(customer_info[1],  75, 137);
		
		String value[] = GraphicsManipulation.cropString(customer_info[2], 145, g2d);
		g2d.drawString(value[0],  75, 150);
		if(value.length == 2){
			g2d.drawString(value[1],  75, 164);
		}
		
		g2d.drawString(customer_info[3], 265, 125);
		g2d.drawString(customer_info[4], 265, 137);
		
	}
	private void drawPurchases(Graphics2D g2d){
		g2d.translate(25, 200);
		cell_y = Math.round(row_height);
		length = 0;
		floor  = (int)Math.floor(quotient);
		if(pageCount < floor){
			length = 10 * (pageCount + 1);
		}
		else{
			length = (int)Precision.multiply(10, Precision.subtract(quotient,floor));
		}
		for(int row=0;row<length;row++){
			for(int column=0;column<5;column++){
				drawCell(row, column, g2d);
			}
			cell_y = Math.round(cell_y + (row_height * 2));
		}
		
	}
	private void drawCell(int row,int column,Graphics2D g2d){
		Object value = purchases[row + (pageCount * 10)][column];
		if(value.equals("null")){
			value = "";
		}
		else if(column == 2){
			String hold[] = GraphicsManipulation.cropString(value + "", 160, g2d);
			value = hold[0];  
			if(hold.length == 2){
				g2d.drawString(hold[1], cell_x[column], cell_y + 14);
			}
		}
		
		int alignment = 0;
		if(column > 2){
			value = NumberManipulation.toAccountancyFormat((BigDecimal)value);
			alignment = g2d.getFontMetrics().stringWidth(value.toString().substring(0,value.toString().indexOf(".")));
		}
		g2d.drawString(value + "", cell_x[column] - alignment, cell_y);
	}
	public void setDiscount(int percentage) {
		this.percentage = percentage;
		for(int i=0;i<purchases.length;i++){
			purchases[i][3] = ((BigDecimal)purchases[i][3]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			purchases[i][4] = ((BigDecimal)purchases[i][4]).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	private void drawTotalAmount(Graphics2D g2d){
		sum = BigDecimal.valueOf(0);
		int
		i    = (pageCount * 10),
		sums = i + length;
		while(i<sums){
			sum = sum.add(((BigDecimal)purchases[i][4]).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			i++;
		}
		String 
		value = NumberManipulation.toAccountancyFormat(sum);
		g2d.drawString( value, cell_x[4] - g2d.getFontMetrics().stringWidth(value.substring(0,value.indexOf("."))), 278);
		value = percentage+"%";
		g2d.drawString( value, cell_x[4] - g2d.getFontMetrics().stringWidth(value.substring(0,value.indexOf("%"))), 292);
		
		g2d.setFont(new Font("Default",Font.BOLD,10));
		value = NumberManipulation.toAccountancyFormat(Accountancy.discountedAmount(sum, percentage));
		g2d.drawString( value, cell_x[4] - g2d.getFontMetrics().stringWidth(value.substring(0,value.indexOf("."))), 306);
	}

	public static void printReceipt(Transaction transaction) {
		ReciptPrinter printer = new ReciptPrinter();
		
		String cutomerInfo[] = {
			transaction.getCustomer().getCustomerName(),
			transaction.getTinNo(),
			transaction.getCustomer().getAddress(),
			transaction.getDate().toString(),
			transaction.getTerms()
		};
		printer.setCustomerInfo(cutomerInfo);

		Order orders[] = transaction.getCart().getOrders();
		printer.prepareRecipt((int)Math.ceil(Precision.divide(orders.length , 10)), orders.length);
		
		Object puchase[];
		String date;
		for(int o=0; o<orders.length; o++) {
			date = orders[o].getProduct().getItem().getExpDate().toString();
			puchase = new Object[] {
				orders[o].getProduct().getPackaging().getQty().getAmount(),
				orders[o].getProduct().getPackaging().getUom().getUnitType().name(),
				orders[o].getProduct().getItem().getDescription() +
					" (" + orders[o].getProduct().getItem().getBrand() + ") " + 
					"Lot No."+ orders[o].getProduct().getItem().getLotNo() +
					" Exp." + date.substring(0,2) + "/" + date.substring(6,10),
				orders[o].getProduct().getPricing().getUnitPrice().toBigDecimal(),
				orders[o].getNetAmount().toBigDecimal()
			};
			printer.insertPurchase(o, puchase);
		}
		printer.setDiscount(transaction.getDiscount().toInt());
		printer.print();
	}
	
}
