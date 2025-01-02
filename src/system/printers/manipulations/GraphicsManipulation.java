package system.printers.manipulations;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

public abstract class GraphicsManipulation {

	public static void drawWrapedString(String str,int wrapWidth,int x,int y, Graphics2D g2d){
			int
			i      = 0,
			length = str.length(),
			stringWidth = 0;
			while(i < length){
				stringWidth += g2d.getFontMetrics().stringWidth(str.substring(i,i+1));
				if(stringWidth > wrapWidth){
					g2d.drawString(str.substring(i) , x, y + 10);
					break;
				}
				i++;
			}
			g2d.drawString(str.substring(0, i) , x, y);
	}
	public static void drawCropedString(String str,int wrapWidth,int x,int y, Graphics2D g2d){
		int
		i      = 0,
		length = str.length(),
		stringWidth = 0;
		String sym = "";
		while(i < length){
			stringWidth += g2d.getFontMetrics().stringWidth(str.substring(i,i+1));
			if(stringWidth > wrapWidth){
				sym = "...";
				break;
			}
			i++;
		}
		g2d.drawString(str.substring(0, i)+sym , x, y);
	}
	public static String[] cropString(String value,int cropWidth,Graphics2D g2d){
		FontMetrics fm = g2d.getFontMetrics();
		int
		char_w,
		width  = fm.stringWidth(value),
		length = value.length();
		
		if(width >= cropWidth){
			width = 0;
			int i = 0;
			while(i < length){
				char_w = fm.stringWidth(value.substring(i,i + 1));
				width += char_w;
				if(width >= cropWidth){
					width -= char_w;
					break;
				}
				i++;
			}
			return new String[]{
				value.substring(0, i+1),
				value.substring(i+1, length)
			};
		}
		else{
			return new String[]{value};
		}
	}
}
