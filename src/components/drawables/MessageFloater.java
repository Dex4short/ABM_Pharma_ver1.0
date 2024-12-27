package components.drawables;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import components._misc_.Graphix.Shadow;
import components._misc_.interfaces.Drawable;
import system.ui.Window;
import system.ui.appearance.Theme;

public class MessageFloater extends Rectangle implements Drawable{
	private static final long serialVersionUID = 1853594968606075303L;
	private String message;
	private int y_translate, str_w, str_h, rect_w, rect_h, rect_arc,delay;
	private float opacity=1.00f;
	private Timer timer;

	public MessageFloater() {
		message="";
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		g2d.setFont(Theme.font[0]);
		
		str_w = g2d.getFontMetrics().stringWidth(message);
		str_h = g2d.getFontMetrics().getAscent();
		
		rect_w = str_w + (str_h*3);
		rect_h = str_h*3;
		rect_arc = rect_h;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		g2d.setColor(Shadow.shadow_color);
		g2d.fillRoundRect((width/2) - (rect_w/2), height - y_translate, rect_w, rect_h, rect_arc, rect_arc);
		
		g2d.setColor(Theme.text_color[3]);
		g2d.drawString(message, (width/2) - (str_w/2), height + (rect_h/2) + (str_h/2) - y_translate);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	public void floatMessage(String message) {
		if(timer != null) {
			timer.cancel();
		}
		
		this.message = message;
		y_translate = 0;
		opacity = 1.00f;
		delay = 180;
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(y_translate < (rect_h*2)) {
					y_translate+=2;
				}
				else if(delay > 0) {
					delay -= 1;
				}
				else if(opacity > 0.05f){
					opacity-=0.05f;
				}
				else {
					opacity=0f;
					cancel();
					timer = null;
				}
				
				Window.getStackPanel().getRootPane().repaint();
			}
		}, 0, 15);
		
	}
	public void floatMessageAndBeep(String message) {
		floatMessage(message);
		Toolkit.getDefaultToolkit().beep();
	}
}
