package system.ui.overlays;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.Timer;
import java.util.TimerTask;

import components.panels.Panel;
import oop.implementations.Theme;

public abstract class LoadingScreen extends Panel implements Theme{
	private static final long serialVersionUID = 2355348374570638690L;
	private Color loading_background, loading_foreground;
	private String load_name;
	private Graphics2D g2d;
	private Stroke arc_stroke;
	private int arc_size, arc_start, arc_end, arc_speed, arc_start_iterate, arc_end_iterate;
	private boolean loading;

	public LoadingScreen() {
		loading_background = main_color[0];
		loading_foreground = main_color[2];
		arc_stroke = new BasicStroke(3);
		
		arc_size = 20;
		arc_start = 0;
		arc_end = 0;
		
		arc_speed = 10;
		
		arc_start_iterate = 0;
		arc_end_iterate = arc_speed;

		setBackground(new Color(0,0,0,0));
		setForeground(new Color(0,0,0,0));
		
		addMouseListener(new MouseAdapter() {});
		addMouseMotionListener(new MouseMotionAdapter() {});
		addMouseWheelListener((MouseWheelEvent e) -> {});
		addKeyListener(new KeyAdapter() {});
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g2d = (Graphics2D)g;
		
		if(loading) {
			g2d.setColor(loading_background);
			g2d.fillRect(getX(), getY(), getWidth(), getHeight());
			
			g2d.setColor(loading_foreground);
			g2d.setStroke(arc_stroke);
			g2d.drawArc(getX() + (getWidth()/2) - (arc_size/2), getY() + (getHeight()/2) - (arc_size/2), arc_size, arc_size, arc_start, arc_end);

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
				(getWidth()/2) - (g2d.getFontMetrics().stringWidth(load_name)/2), 
				(getHeight()/2) + (arc_size/2) + (g2d.getFontMetrics().getAscent() * 2)
			);
			
		}
	}
	public Color getLoadingBackground() {
		return loading_background;
	}
	public Color getLoadingForeground() {
		return loading_foreground;
	}
	public String getLoadName() {
		return load_name;
	}
	public void setLoadingBackground(Color background) {
		this.loading_background = background;
	}
	public void setLoadingForeground(Color foreground) {
		this.loading_foreground = foreground;
	}
	public boolean isLoading() {
		return loading;
	}
	public void setLoadName(String load_name) {
		this.load_name = load_name;
	}
	public void load(Runnable runnable_load) {
		load(runnable_load, 1000, loading_background, loading_foreground, "Loading...");
	}
	public void load(Runnable runnable_load, int delay) {
		load(runnable_load, delay, loading_background, loading_foreground, "Loading...");
	}
	public void load(Runnable runnable_load, int delay, Color bg_color, Color fg_color, String load_name) {		
		setLoadingBackground(bg_color);
		setLoadingForeground(fg_color);
		setLoadName(load_name);

		loading = true;
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				onLoading(runnable_load.getClass().getName());
				if(getRootPane() != null) getRootPane().repaint();
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
