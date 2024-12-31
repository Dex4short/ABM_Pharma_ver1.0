package components.fields;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import components.panels.Panel;

public class TwinNumericField extends Panel{
	private static final long serialVersionUID = -3497471385717877254L;
	private NumericField field1, field2;
	private char divider;
	private double aspect_ratio;
	private boolean aspectRatioEnabled;
		
	public TwinNumericField(String qty, String size){		
		field1 = new NumericField(qty);
		field2 = new NumericField(size);
		initialize();
	}
	public TwinNumericField(int qty, int size) {		
		field1 = new NumericField(qty);
		field2 = new NumericField(size);
		initialize();
	}
	private int divider_w;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(text_color[1]);
		g.setFont(font[0]);
		
		divider_w = g.getFontMetrics().stringWidth(divider + "");
		
		g.drawString(
				divider + "",
				(getWidth()/2) - (divider_w/2), 
				(getHeight()/2) + (g.getFontMetrics().getAscent()/2)
		);
	}
	@Override
	public void setEnabled(boolean editable) {
		field1.setEnabled(editable);
		field2.setEnabled(editable);
	}
	public void setDivider(char divider) {
		this.divider = divider;
	}
	public char getDivider() {
		return divider;
	}
	public NumericField getLeftNumericField() {
		return field1;
	}
	public NumericField getRightNumericField() {
		return field2;
	}
	public boolean isAspectRatioEnabled() {
		return aspectRatioEnabled;
	}
	public void maintainAspectRatio(double aspectRatio) {
		this.aspect_ratio = aspectRatio;
		aspectRatioEnabled = aspectRatio != 0;
	}
	
	private final void initialize() {
		setLayout(null);
		setForeground(main_color[3]);
		setFont(font[0]);
		
		addFieldActionListener(field1, field2);
		addFieldActionListener(field2, field1);
		
		add(field1);
		add(field2);
		setDivider('/');
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				field1.setBounds(0, 0, (getWidth()/2) - (divider_w/2), getHeight() + 2);
				field2.setBounds((getWidth()/2) + divider_w, 0, (getWidth()/2) - divider_w, getHeight() + 2);
				
				getRootPane().revalidate();
				getRootPane().repaint();
			}
		});
	}
	private final void addFieldActionListener(NumericField field1, NumericField field2) {
		field1.addKeyListener(new KeyAdapter() {
			private String str;
			private int num, len, column;
			@Override
			public void keyReleased(KeyEvent e) {
				if(isAspectRatioEnabled()) {
					str = field1.getText();
					if(!str.equals("")) {
						num = Integer.parseInt(str);
					}
					else {
						num = 0;
					}
					
					column = field1.getCaretPosition();
					
					field1.setText(num + "");
					field2.setText((int)(num / aspect_ratio) + "");
					
					len = (num + "").length();
					if(column > len) {
						column = len;
					}
					field1.setCaretPosition(column);
				}
			}
		});
	}
}
