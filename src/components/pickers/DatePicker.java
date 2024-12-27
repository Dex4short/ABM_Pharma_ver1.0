package components.pickers;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;

import components.Button;
import components.Label;
import components.list.Item;
import components.list.ListPane;
import components.panels.ActionPanel;
import components.panels.Panel;
import components.panels.StackPanel;
import system.objects.Date;

public abstract class DatePicker extends ActionPanel{
	private static final long serialVersionUID = -322731435061702702L;
	private int last_panel;
	private Button btn_year, btn_month, btn_day;
	private Panel content_panel[];
	private Date date;

	{
		setArc(20);
	}
	public DatePicker() {
		super(new Label("Date Picker"));
		
		date = new Date();
		
		int
		yyyy = date.getYear(),
		mm = date.getMonth(),
		dd = date.getDay();
		
		construct_date_picker(yyyy + "", mm + "", dd + "");
	}
	public DatePicker(String date) {
		super(new Label("Date Picker"));
		
		int yyyy, mm, dd;
		
		try {
			yyyy = Date.extractYear(date);
		}catch (Exception e) {
			yyyy = Date.getCurrentYear();
		}
		
		try {
			mm = Date.extractMonth(date);
		} catch (Exception e) {
			mm = Date.getCurrentMonth();
		}
		
		try {
			dd = Date.extractDay(date);
		} catch (Exception e) {
			dd = Date.getCurrentDay();
		}
		
		this.date = new Date(yyyy, mm, dd);
		
		construct_date_picker(yyyy+"", mm+"", dd+"");
	}
	private final void construct_date_picker(String year, String month, String day) {
		getPanelBody().setLayout(new BorderLayout(10,10));
		getPanelBody().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Panel btn_panel = new Panel();
		btn_panel.setLayout(new GridLayout(0, 3, 10, 10));
		btn_panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		getPanelBody().add(btn_panel, BorderLayout.NORTH);
		
		btn_year = new Button(year);
		btn_year.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openPanel(0);
			}
		});
		btn_panel.add(btn_year);
		
		btn_month = new Button(month);
		btn_month.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openPanel(1);
			}
		});
		btn_panel.add(btn_month);
		
		btn_day= new Button(day);
		btn_day.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openPanel(2);
			}
		});
		btn_panel.add(btn_day);
		
		content_panel = new Panel[3];
		content_panel[0] = new YearPanel() {
			private static final long serialVersionUID = -903881822796808752L;
			@Override
			public void onPickYear(String year, int yyyy) {
				onPickCalendarYear(year, yyyy);
			}
		};
		content_panel[1] = new MonthPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onPickMonth(String month, int mm) {
				onPickCalendarMonth(month, mm);
			}
		};
		content_panel[2] = new DayPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void onPickDay(String day, int dd) {
				onPickCalendarDay(day, dd);
			}
		};

		StackPanel stack_panel = new StackPanel();
		for(int p=0; p<content_panel.length; p++) {
			stack_panel.pushPanel(content_panel[p]);
			content_panel[p].setVisible(p == 0);
		}
		getPanelBody().add(stack_panel, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				getRootPane().revalidate();
				getRootPane().repaint();
			}
		});
		
		last_panel = -1;
		openPanel(0);
		
	}
	@Override
	public void onOk() {
		try {
			Integer.parseInt(btn_year.getText());
			Integer.parseInt(btn_month.getText());
			Integer.parseInt(btn_day.getText());
			
			
			onPickCalendarDate(date.getYear(), date.getMonthName(), date.getMonth(), date.getDayName(), date.getDay());
		} catch (Exception e) {
			onInvalidPickDate();
		}
	}
	@Override
	public void onCancel() {
		onCloseDatePicker();
	}
	public Date getCalendar(){
		return date;
	}
	public Date getDate() {
		return date;
	}
	public void openPanel(int next_panel) {
		if(last_panel != -1) {
			content_panel[last_panel].setVisible(false);
		}
		content_panel[next_panel].setVisible(true);
		last_panel = next_panel;
		
		if(getRootPane() != null) {
			getRootPane().revalidate();
			getRootPane().repaint();
		}
	}
	public void onPickCalendarYear(String year, int yyyy) {
		date.setYear(yyyy);
		openPanel(1);
	}
	public void onPickCalendarMonth(String month, int mm) {
		date.setMonth(mm);
		openPanel(2);
	}
	public void onPickCalendarDay(String day, int dd) {
		date.setDay(dd);
	}
	public void onInvalidPickDate() {
		Toolkit.getDefaultToolkit().beep();
	}
	public abstract void onPickCalendarDate(int yyyy, String month, int mm, String day, int dd);
	public abstract void onCloseDatePicker();
	
	public abstract class YearPanel extends ListPane{
		private static final long serialVersionUID = 6704820730372838991L;
		private int year_row;
		private Button.Secondary last_btn;

		public YearPanel() {
			setForeground(new Color(0,0,0,0));
			setItemHeight(30);
			
			int year=2000;
			while(year<2100) {
				addRowYears(year, year+3);
				year+=4;
			}

			getVertical_scrollbar().setScrollSpeed(1);
			/*Patch Fix*/
			//requires better approach
			getVertical_scrollbar().scroll(-(getItemHeight() * (year_row-1)));
			getVertical_scrollbar().setScrollerBarLocation(0, Math.round((getItemHeight() * (year_row-1)) * ((float)(year_row-2)/(float)getItemCount())));
			
			setSelectionEnabled(false);
		}
		private final void addRowYears(int starting_year, int end_year) {
			addItem(new Item(new Panel(){
				private static final long serialVersionUID = 4074194059880003482L;
				{
					setBackground(new Color(0,0,0,0));
					setForeground(getBackground());

					for(int year=starting_year; year<=end_year; year++) {
						add(new Button.Secondary(year + "") {
							private static final long serialVersionUID = 7981196744490259644L;
							{
								int yr;
								try {
									yr = Integer.parseInt(btn_year.getText());
								} catch (Exception e) {
									yr = Date.getCurrentYear();
								}
								
								if(getText().equals(yr + "")) {
									setDecorations(new Button.Quaternary().getDecorations());
									setDecorationState(Button.Normal_State);
									last_btn = this;
									year_row = getItemCount() + 1;						
								}
								
								addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										last_btn.setDecorations(new Button.Secondary().getDecorations());
										last_btn.setDecorationState(Button.Normal_State);
										last_btn = (Button.Secondary)e.getSource();
										
										setDecorations(new Button.Quaternary().getDecorations());	
										setDecorationState(Button.Normal_State);
										
										String year = getText();
										btn_year.setText(year);
										
										onPickYear(year, Integer.parseInt(year));
									}
								});
							}
						});
					}
				}
			}));
		}
		public abstract void onPickYear(String year, int yyyy);
	}
	
	public abstract class MonthPanel extends Panel{
		private static final long serialVersionUID = 7904375214809724919L;
		private static final int rows=4,cols=3;
		private Button.Secondary last_btn;
		
		public MonthPanel() {
			setLayout(new GridLayout(rows, cols));
			setBorder(BorderFactory.createEmptyBorder( 10, 10, 10, 10));
			
			
			for(int r=0; r<rows; r++) {
				for(int c=0; c<cols; c++) {
					final int mm = (cols * r) + (c+1);
					
					add(new Button.Secondary(Date.getMonthName(mm)) {
						private static final long serialVersionUID = -171652431288484447L;
						{
							//setForeground(Theme.gray_shade[1]);
							//setHighlightColor(Theme.text_color[1]);
							//setPressedColor(Theme.text_color[1]);

							int m;
							try {
								m = Integer.parseInt(btn_month.getText());
							} catch (Exception e) {
								m = Date.getCurrentMonth();
							}
							
							if(getText().equals(Date.getMonthName(m))) {
								setDecorations(new Button.Quaternary().getDecorations());
								setDecorationState(Button.Normal_State);
								last_btn = this;
							}
							
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									last_btn.setDecorations(new Button.Secondary().getDecorations());
									last_btn.setDecorationState(Button.Normal_State);
									last_btn = (Button.Secondary)e.getSource();
									
									setDecorations(new Button.Quaternary().getDecorations());	
									setDecorationState(Button.Normal_State);
									
									String month = last_btn.getText();
									btn_month.setText(mm + "");
									
									((DayPanel)content_panel[2]).updateDays(date.getYear(), mm);
									
									onPickMonth(month, mm);
								
								}
							});
						}
					});
				}
			}
		}
		public abstract void onPickMonth(String month, int mm);
	}
	
	public abstract class DayPanel extends Panel{
		private static final long serialVersionUID = 7904375214809724919L;
		private final int row,col;
		private Button.Secondary last_btn;
		
		public DayPanel() {
			row=5;
			col=7;
			setLayout(new GridLayout(row, col));
			setBorder(BorderFactory.createEmptyBorder( 10, 10, 10, 10));
			
			for(int i=0; i<row; i++) {
				for(int ii=0; ii<col; ii++) {
					final int dd = (col * i) + (ii+1);
					
					if(dd>31) {
						break;
					}
					
					add(new Button.Secondary(dd + "") {
						private static final long serialVersionUID = -171652431288484447L;
						{							
							int d;
							try {
								d = Integer.parseInt(btn_day.getText());
							} catch (Exception  e) {
								d = Date.getCurrentDay();
							}
							
							if(getText().equals(d + "")) {
								setDecorations(new Button.Quaternary().getDecorations());
								setDecorationState(Button.Normal_State);
								last_btn = this;
							}
							
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									last_btn.setDecorations(new Button.Secondary().getDecorations());
									last_btn.setDecorationState(Button.Normal_State);
									last_btn = (Button.Secondary)e.getSource();
									
									setDecorations(new Button.Quaternary().getDecorations());	
									setDecorationState(Button.Normal_State);
									
									String day = Date.getDayName(date.getYear(), date.getMonth(), dd);
									btn_day.setText(dd + "");
									
									onPickDay(day, dd);
								
								}
							});
							
						}
					});
				}
			}
		}
		public void updateDays(int yyyy, int mm) {
			int days = Date.getMonthDays(yyyy, mm);
			
			boolean visible=true;
			for(int d=27; d<31; d++) {
				if(d==days) {
					visible = false;
				}
				getComponent(d).setVisible(visible);
			}
		}
		public abstract void onPickDay(String day, int dd);
	}
}
