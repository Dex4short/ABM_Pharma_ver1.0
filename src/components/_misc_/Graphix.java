package components._misc_;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Graphix {

	public static Color setAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	public static ImageIcon alterIcon(Icon src, Color color, ImageObserver observer) {
		BufferedImage buff_img = new BufferedImage(src.getIconWidth(), src.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		src.paintIcon(null, buff_img.createGraphics(), 0, 0);
		return new ImageIcon(alterImage(buff_img, color, observer));
	}
	public static Image alterImage(BufferedImage buff_img, Color color, ImageObserver observer) {
		int 
		img_w = buff_img.getWidth(),
		img_h = buff_img.getHeight();
		
		Graphics2D g2d = (Graphics2D)buff_img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(buff_img, 0, 0, img_w, img_h, observer);
		g2d.dispose();
		
		for(int x=0; x<img_w; x++) {
			for(int y=0; y<img_h; y++) {
				int argb = buff_img.getRGB(x, y);
				
				int alpha = (argb >> 24) & 0xFF;
				if(alpha==0) {
					continue;
				}
				
				int new_argb = (alpha << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
				buff_img.setRGB(x, y, new_argb);
			}
		}
		return buff_img;
	}
	public static ImageIcon alterImageIcon(ImageIcon src, Color color, ImageObserver observer) {
		BufferedImage buff_img = new BufferedImage(src.getIconWidth(), src.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		buff_img.createGraphics().drawImage(src.getImage(), 0, 0, observer);
		return new ImageIcon(alterImage(buff_img, color, observer));
	}
	public static ImageIcon alterImageIcon(URL src, Color color, ImageObserver observer) {
		BufferedImage buff_img = null;
		try {
			buff_img = ImageIO.read(src);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ImageIcon(alterImage(buff_img, color, observer));
	}
	
	public static class RoundRect extends Area{
		public RoundRect(int x, int y, int w, int h, int arcW, int arcH, boolean arc_topLeft, boolean arc_topRight, boolean arc_bottomLeft, boolean arc_bottomRight) {
			add(new Area(new Rectangle2D.Double(x, y, w, h)));
		
			if(arc_topLeft) {
				subtract(new Area(new Rectangle2D.Double( x, y, Math.round(arcW/2f),Math.round( arcH/2f))));
				add(new Area(new Ellipse2D.Double( x, y, arcW, arcH)));
			}
			
			if(arc_topRight) {
				subtract(new Area(new Rectangle2D.Double( x + w - Math.round(arcW/2f), y, Math.round(arcW/2f), Math.round(arcH/2f))));
				add(new Area(new Ellipse2D.Double( x + w - arcW, y, arcW, arcH)));
			}
			
			if(arc_bottomLeft) {
				subtract(new Area(new Rectangle2D.Double( x, y + h - Math.round(arcH/2f), arcW/2f, Math.round(arcH/2f))));
				add(new Area(new Ellipse2D.Double( x, y + h - arcH, arcW, arcH)));
			}
			
			if(arc_bottomRight) {
				subtract(new Area(new Rectangle2D.Double( x + w - (arcW/2f), y + h - (arcH/2f), arcW/2f, arcH/2f)));
				add(new Area(new Ellipse2D.Double( x + w - arcW, y + h - arcH, arcW, arcH)));
			}
		}
	}
	public static class Shadow {
		public static final Color shadow_color = new Color(0,0,0,128);
		public static int x_offset=0, y_offset=5;

		private Shadow() {
			
		}
		public static void cast_shadow(Area area, int x, int y, Graphics2D g2d) {
			g2d.translate(x + x_offset, y + y_offset);
			g2d.setColor(shadow_color);
			g2d.fill(area);
			g2d.translate(-(x + x_offset), -(y + y_offset));
		}
		public static void cast_shadow(Rectangle rect, Graphics2D g2d) {
			g2d.setColor(shadow_color);
			g2d.fillRect(rect.x + x_offset, rect.y + y_offset, rect.width, rect.height);
		}
	}
}
