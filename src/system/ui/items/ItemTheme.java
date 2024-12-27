package system.ui.items;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Label;
import components._misc_.Graphix;
import components.list.Item;
import res.Resource;
import system.ui.Window;

public abstract class ItemTheme extends Item{
	private static final long serialVersionUID = 2949663695614910176L;
	private Label label;
	private int mode = 0;
	
	public static final String THEMES[] = {"Light Mode", "Dark Mode"};

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
		
		label = (Label)getComponent(0);
		label.setText(THEMES[mode]);
		add(label, BorderLayout.CENTER);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Window.floatMessageAndBeep("Currently no avialable");
		//changeTheme();
		setSelected(false);
	}
	public void changeTheme() {
		mode = 1 - mode;
		label.setText(THEMES[mode]);
		onChangeTheme(mode);
	}
	
	public abstract void onChangeTheme(int mode);
	
}
