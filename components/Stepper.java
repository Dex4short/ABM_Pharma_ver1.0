package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import oop.interfaces.Theme;

public abstract class Stepper extends Panel implements Theme{
	private static final long serialVersionUID = 1L;
	private JLabel lbl;
	private Button btn_inc, btn_dec;
	private ArrayList<String> iterations;
	private int selected;
	private Color border_color;
	
	public Stepper(String iterations[], int selected) {
		setLayout(new BorderLayout());
		setArc(5);
		
		lbl = new JLabel();
		lbl.setBorder(BorderFactory.createEmptyBorder(0, 2, 0 ,0));
		lbl.setFont(font[0]);
		add(lbl, BorderLayout.CENTER);
		
		btn_inc = new Button.Quaternary("+");
		btn_inc.setArc(5);
		btn_inc.setBorder(BorderFactory.createEmptyBorder(0, 5, 0 ,5));
		btn_inc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				increment();
			}
		});
		add(btn_inc, BorderLayout.EAST);

		btn_dec = new Button.Quaternary("-");
		btn_dec.setArc(5);
		btn_dec.setBorder(BorderFactory.createEmptyBorder(0, 5, 0 ,5));
		btn_dec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				decrement();
			}
		});
		add(btn_dec, BorderLayout.WEST);
		
		this.iterations = new ArrayList<String>();
		setIterations(iterations);
		setSelectedIteration(selected);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				getRootPane().revalidate();
				getRootPane().repaint();
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, getArc(), getArc());
		
		super.paint(g);
		
		g.setColor(getBorderColor());
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getArc(), getArc());
	}
	public void setIterations(String iterations[]) {
		this.iterations.clear();
		for(String str: iterations) {
			this.iterations.add(str);
		}
	}
	public void setSelectedIteration(int n) {
		if(n>=0 && n<iterations.size()) {
			selected = n;
		}
		setSelectedIterationValue(iterations.get(selected));
	}
	public void setSelectedIterationValue(String value) {
		lbl.setText(value);
	}
	public void setBorderColor(Color border_color) {
		this.border_color = border_color;
	}
	public JLabel getLabel() {
		return lbl;
	}
	public Button getIncrementButton() {
		return btn_inc;
	}
	public Button getDecrementButton() {
		return btn_dec;
	}
	public String getSelectedIterationValue() {
		return lbl.getText();
	}
	public int getSelectedIteration() {
		return selected;
	}
	public Color getBorderColor() {
		return border_color;
	}
	public void increment() {
		if(selected < iterations.size()-1) {
			selected++;
		}
		else {
			Toolkit.getDefaultToolkit().beep();
		}
		setSelectedIterationValue(iterations.get(selected));
		onIncrement(getSelectedIterationValue());
	}
	public void decrement() {
		if(selected > 0) {
			selected--;
		}
		else {
			Toolkit.getDefaultToolkit().beep();
		}
		setSelectedIterationValue(iterations.get(selected));
		onDecrement(getSelectedIterationValue());
	}
	public abstract void onIncrement(String selectedIterationValue);
	public abstract void onDecrement(String selectedIterationValue);
}
