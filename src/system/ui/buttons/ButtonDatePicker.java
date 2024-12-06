package system.ui.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Button;
import components.pickers.DatePicker;
import oop.Date;
import oop.abstracts.Decoration;
import system.ui.Window;

public class ButtonDatePicker extends Button.Notified implements ActionListener{
	private static final long serialVersionUID = -8947393395162386027L;
	private DatePicker date_picker;

	public ButtonDatePicker(String str) {
		super(str);
		setAccentColorDecoration(0);
		
		date_picker = new DatePicker(str) {
			private static final long serialVersionUID = 7485068439816912681L;

			@Override
			public void onPickCalendarDate(int yyyy, String month, int mm, String day, int dd) {
				setText(new Date(yyyy, mm, dd).toString());
				Window.getStackPanel().popPanel();
			}
			@Override
			public void onCloseDatePicker() {
				Window.getStackPanel().popPanel();
			}
		};
		
		addActionListener(this);
		setNotified(true);
	}
	public ButtonDatePicker(Date date) {
		super(date.toString());
		setAccentColorDecoration(0);
		
		date_picker = new DatePicker(date.toString()) {
			private static final long serialVersionUID = 7485068439816912681L;

			@Override
			public void onPickCalendarDate(int yyyy, String month, int mm, String day, int dd) {
				setText(new Date(yyyy, mm, dd).toString());
				Window.getStackPanel().popPanel();
			}
			@Override
			public void onCloseDatePicker() {
				Window.getStackPanel().popPanel();
			}
		};
		
		addActionListener(this);
		setNotified(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(date_picker == null) return;
		Window.getStackPanel().pushPanel(date_picker, 300, 300);
	}
	public void setAccentColorDecoration(int accent) {
		Decoration decorations[] = new Decoration[4];
		(decorations[Normal_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent],
				new Color(0,0,0,0)
		)).decorate(this);
		decorations[Hovered_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent],
				accent_color[0][accent]
		);
		decorations[Pressed_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent].brighter(),
				accent_color[0][accent]
		);
		decorations[Disabled_State] = decorations[Normal_State];
		setDecorations(decorations);
		
		setNotificationColor(getForeground());
	}
	public DatePicker getDatePicker() {
		return date_picker;
	}
	public void setDatePicker(DatePicker date_picker) {
		this.date_picker = date_picker;
	}
	public void setDate(Date date) {
		setText(date.toString());
	}
	public Date getDate() {
		return new Date(getText());
	}
}
