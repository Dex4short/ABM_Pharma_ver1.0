package system.ui.items;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Label;
import components._misc_.Graphix;
import components.list.Item;
import components.panels.DialogPanel;
import res.Resource;
import system._default_.Settings;
import system.ui.Window;
import system.ui.appearance.Theme;

public abstract class ItemTheme extends Item implements Theme{
	private static final long serialVersionUID = 2949663695614910176L;
	private Label label;
	private int mode = 0;

	public ItemTheme() {
		super(new Label() {
			private static final long serialVersionUID = -7118103241794633788L;
			{
				ImageIcon
				img_icon = Resource.getAsImageIcon("theme.png");
				img_icon = Graphix.alterImageIcon(img_icon, main_color[0], getFocusCycleRootAncestor());
				setIcon(img_icon);
			}
		});
		
		String theme_mode = Settings.current_theme();
		mode = 0;
		while(!THEME_MODES[mode].equals(theme_mode)) {
			mode++;
		}
		
		label = (Label)getComponent(0);
		label.setText(THEME_MODES[1-mode]);
		add(label, BorderLayout.CENTER);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Window.getPopUpPanel().pop();
		Window.getStackPanel().pushPanel(new DialogPanel("Change Theme", "This action requires system restart, continue?") {
			private static final long serialVersionUID = -2225087555014409477L;
			@Override
			public void onOk() {
				changeTheme();
			}
			@Override
			public void onCancel() {
				Window.getStackPanel().popPanel();
			}
		}, 200, 200);
	}
	@Override
	public void setSelected(boolean selected) {
		//disable function
	}
	public void changeTheme() {
		mode = 1 - mode;
		label.setText(THEME_MODES[mode]);
		onChangeTheme(THEME_MODES[mode]);
	}
	
	public abstract void onChangeTheme(String theme_mode);
	
}
