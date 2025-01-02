package system.printers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import system.objects.Decimal;
import system.objects.Transaction;
import system.printers.manipulations.GraphicsManipulation;

public class TransactionsPrinter {
	private PrinterJob printer;
	int w,h,pageRows,rowY,descWidth,printablePages;
	
	public TransactionsPrinter() {
		printer = PrinterJob.getPrinterJob();
	}
	public void printTransactions(Object transactions[][], String total_cost, String total_due){
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
				g2d.drawString("Customer / TIN / Address / Date / Terms", 30, 0);
				g2d.drawString("Cost Amount", (w / 2) + 120, 0);
				g2d.drawString("Due", (w / 2) + 200, 0);
				
				int row  = 0;
				i        = pageRows * page;
				pageRows = (h - 100) / 29;
				while(i < transactions.length){		
					rowY = ((row + 1) * 30) - 10;
					
					str = (i + 1) + ".";
					g2d.drawString(str , 0, rowY);

					str = transactions[i][0] + " / "
						+ "TIN "     + transactions[i][1] + " / "
						+ "Address " + transactions[i][2] + " / "
						+ "Date "    + transactions[i][3] + " "
						+ "("        + transactions[i][4] + ")";
					descWidth   = (int)(w * 0.66f) - 50;
					GraphicsManipulation.drawWrapedString(str, descWidth, 30, rowY, g2d);
					

					str = (String)transactions[i][5];
					g2d.drawString(str , (w / 2) + 120, rowY);
					
					str = (String)transactions[i][6];
					g2d.drawString(str , (w / 2) + 200, rowY);
					
					i++;
					row++;
					if(row == pageRows){
						row = 0;
						printablePages = page + 2;
						break;
					}
					
					if(i == transactions.length){
						str = "Total Cost: "+total_cost+"          Total Due: "+total_due;
						int
						x1 = (w - 50),
						x2 = g2d.getFontMetrics().stringWidth(str),
						y  = rowY + 30;
						g2d.drawString( str, x1 - x2 , y + 10);
						g2d.drawLine(x1 - x2, y - 5, x1, y - 5);
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
	
	public static void printTransactions(Transaction transactions[]) {
		TransactionsPrinter transactionsPrinter = new TransactionsPrinter();
		
		Decimal
		total_cost = new Decimal(),
		total_profit = new Decimal();
		
		Object transactions_data[][] = new Object[transactions.length][7];
		for(int t=0; t<transactions_data.length; t++) {
			transactions_data[t][0] = transactions[t].getCustomer().getCustomerName();
			transactions_data[t][1] = transactions[t].getTinNo();
			transactions_data[t][2] = transactions[t].getCustomer().getAddress();
			transactions_data[t][3] = transactions[t].getDate().toString();
			transactions_data[t][4] = transactions[t].getTerms();
			transactions_data[t][5] = transactions[t].getCostAmount().toString();
			transactions_data[t][6] = transactions[t].getProfit().toString();

			total_cost = total_cost.add(transactions[t].getCostAmount());
			total_profit = total_profit.add(transactions[t].getProfit());
		}
		
		transactionsPrinter.printTransactions(transactions_data, total_cost.toString(), total_profit.toString());
	}
	
}
