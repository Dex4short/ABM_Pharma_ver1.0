package system.ui.panels.sub_panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;

import components.Button;
import components.Label;
import components.panels.Panel;
import system.ui.Window;

public abstract class PanelCounterSelection extends Panel{
	private static final long serialVersionUID = -4215974895420427660L;
	private Button btn[], back;
	private Label label;
	private int size=40, gap=20, arc=40, rows=4, cols=4;

	public PanelCounterSelection() {
		setBackground(main_color[0]);
		setLayout(null);
		
		label = new Label("Select counter number:");
		label.setHorizontalAlignment(Label.CENTER);
		label.setForeground(main_color[2]);
		label.setFont(font[0]);
		add(label);
		
		btn = new Button[15];
		for(int b=0; b<btn.length; b++) {
			final int number = b+1;
			btn[b] = new Button(number + "");
			btn[b].setArc(arc);
			btn[b].setBorder(BorderFactory.createEmptyBorder());
			btn[b].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectCounter(number);
				}
			});
			add(btn[b]);
		}
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				layout_components();
			}
		});
		
		back = new Button("back");
		back.addActionListener(e -> {
			goBack();
		});
		add(back);
		
		layout_components();
		
	}
	public void selectCounter(int counter_no) {
		Window.getStackPanel().popPanel(this);
		onSelectCounter(counter_no);
	}
	public void goBack(){
		onGoBack();
	}	
	
	public abstract void onSelectCounter(int counter_no);
	public abstract void onGoBack();
	
	private void layout_components() {
		int r=0, c=0, grid_w=(rows*(size + gap)), grid_h=(cols*(size + gap));
		
		label.setBounds(
			(getWidth()/2) - (grid_w/2) - (gap/2),
			(getHeight()/2) - (grid_h/2) - size - gap,
			grid_w,
			size
		);
		
		for(int b=0; b<btn.length; b++) {
			r = b%rows;
			c = b/cols;
			
			btn[b].setBounds(
				(getWidth()/2) - (grid_w/2) + (r * (size + gap)),
				(getHeight()/2) - (grid_h/2) + (c * (size + gap)),
				size,
				size
			);
		}
		
		back.setBounds(
			(getWidth()/2) - (grid_w/2) - (gap/2),
			(getHeight()/2) + (grid_h/2) + gap,
			grid_w,
			size
		);
	}
}
