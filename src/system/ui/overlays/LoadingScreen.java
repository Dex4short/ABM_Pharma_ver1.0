package system.ui.overlays;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Timer;
import java.util.TimerTask;

import oop.interfaces.Drawable;
import oop.interfaces.Theme;

public abstract class LoadingScreen extends Rectangle implements Drawable, Theme{
	private static final long serialVersionUID = 2355348374570638690L;
	private Color background, foreground;
	private String load_name;
	private Stroke arc_stroke;
	private int arc_size, arc_start, arc_end, arc_speed, arc_start_iterate, arc_end_iterate;
	private boolean loading;

	public LoadingScreen() {
		background = main_color[0];
		foreground = main_color[2];
		arc_stroke = new BasicStroke(3);
		
		arc_size = 20;
		arc_start = 0;
		arc_end = 0;
		
		arc_speed = 10;
		
		arc_start_iterate = 0;
		arc_end_iterate = arc_speed;
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		if(loading) {
			g2d.setColor(background);
			g2d.fillRect(x, y, width, height);
			
			g2d.setColor(foreground);
			g2d.setStroke(arc_stroke);
			g2d.drawArc(x + (width/2) - (arc_size/2), y + (height/2) - (arc_size/2), arc_size, arc_size, arc_start, arc_end);

			arc_end += arc_end_iterate;
			arc_start += arc_speed + arc_start_iterate;
			if(arc_end >= 360 || arc_end<=0) {
				arc_end_iterate *= -1;
				arc_start_iterate = arc_speed - arc_start_iterate;
			}
			
			if(arc_start>=360) {
				arc_start = 0;
			}
			else if(arc_start<=0){
				arc_start=360;
			}
			
			g2d.setFont(font[0]);
			g2d.drawString(
				load_name, 
				(width/2) - (g2d.getFontMetrics().stringWidth(load_name)/2), 
				(height/2) + (arc_size/2) + (g2d.getFontMetrics().getAscent() * 2)
			);
			
		}
	}
	public Color getBackground() {
		return background;
	}
	public Color getForeground() {
		return foreground;
	}
	public String getLoadName() {
		return load_name;
	}
	public void setBackground(Color background) {
		this.background = background;
	}
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	public boolean isLoading() {
		return loading;
	}
	public void setLoadName(String load_name) {
		this.load_name = load_name;
	}
	public void load(Runnable runnable_load) {
		load(runnable_load, 1000, background, foreground, "Loading...");
	}
	public void load(Runnable runnable_load, int delay) {
		load(runnable_load, delay, background, foreground, "Loading...");
	}
	public void load(Runnable runnable_load, int delay, Color bg_color, Color fg_color, String load_name) {
		setBackground(bg_color);
		setForeground(fg_color);
		setLoadName(load_name);

		loading = true;
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				onLoading(runnable_load.getClass().getName());
			}
		}, 0, 20);
		
		new Thread() {
			public void run() {
				runnable_load.run();
				
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				loading = false;
				timer.cancel();
			}
		}.start();
		
	}
	
	public abstract void onLoading(String load_name);
	
}
