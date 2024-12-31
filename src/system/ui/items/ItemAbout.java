package system.ui.items;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Label;
import components._misc_.Graphix;
import components.list.Item;
import res.Resource;
import system.ui.Window;
import system.ui.panels.dialogs.DialogSystemInformation;

public class ItemAbout extends Item {
	private static final long serialVersionUID = -6323731760408086079L;

	public ItemAbout() {
		super(new Label("System Information") {
			private static final long serialVersionUID = -1886820325575312514L;
			{
				ImageIcon
				img_icon = Resource.getAsImageIcon("info.png");
				img_icon = Graphix.alterImageIcon(img_icon, main_color[0], getFocusCycleRootAncestor());
				setIcon(img_icon);
			}
		});
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Window.getPopUpPanel().pop();
		Window.getStackPanel().pushPanel(new DialogSystemInformation(), 600, 500);
	}
	@Override
	public void setSelected(boolean selected) {
		//disable function
	}
}
