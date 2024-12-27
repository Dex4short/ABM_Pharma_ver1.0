package components.scroll;

import java.awt.BorderLayout;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import components.panels.Panel;


public abstract class ScrollPane extends Panel {
	private static final long serialVersionUID = 270761354238773251L;
	private int scroll_x, scroll_y;
	private Panel panel_view;
	private ScrollBar vertical_scrollbar, horizontal_scrollbar;

	public ScrollPane(Panel panel_view){
		setOpaque(false);
		setLayout(new BorderLayout(0,0));
		setArc(0);
		setPanelView(panel_view);

		vertical_scrollbar = new VerticalScrollBar();
		add(vertical_scrollbar, BorderLayout.EAST);
		
		horizontal_scrollbar = new HorizontalScrollBar();
		add(horizontal_scrollbar, BorderLayout.SOUTH);
		
		panel_view.setLayout(null);
		panel_view.addMouseWheelListener(vertical_scrollbar);
		panel_view.addContainerListener(new ContainerListener() {//something not working
			@Override
			public void componentRemoved(ContainerEvent e) {
				vertical_scrollbar.update();
				horizontal_scrollbar.update();
			}
			@Override
			public void componentAdded(ContainerEvent e) {
				vertical_scrollbar.update();
				horizontal_scrollbar.update();
			}
		});
		add(panel_view, BorderLayout.CENTER);
	}
	public Panel getPanelView() {
		return panel_view;
	}
	public void setPanelView(Panel panel_view) {
		this.panel_view = panel_view;
	}
	public ScrollBar getVertical_scrollbar() {
		return vertical_scrollbar;
	}
	public void setVertical_scrollbar(ScrollBar vertical_scrollbar) {
		this.vertical_scrollbar = vertical_scrollbar;
	}
	public ScrollBar getHorizontal_scrollbar() {
		return horizontal_scrollbar;
	}
	public void setHorizontal_scrollbar(ScrollBar horizontal_scrollbar) {
		this.horizontal_scrollbar = horizontal_scrollbar;
	}
	public int getScrollX() {
		return scroll_x;
	}
	public int getScrollY() {
		return scroll_y;
	}
	public void updateScrollers() {
		vertical_scrollbar.update();
		horizontal_scrollbar.update();
	}

	public abstract int viewingWidth();
	public abstract int viewingHeight();
	public abstract void onScrollPanel(int scroll_x, int scroll_y);

	private class VerticalScrollBar extends ScrollBar{
		private static final long serialVersionUID = -3698390770684607782L;
		
		public VerticalScrollBar() {
			super(ScrollBar.VERTICAL);
		}
		@Override
		public int getViewLength() {
			return viewingHeight();
		}
		@Override
		public void onScroll(int scroll_amount) {
			scroll_y = scroll_amount;
			onScrollPanel(scroll_x, scroll_y);
		}
	}
	private class HorizontalScrollBar extends ScrollBar{
		private static final long serialVersionUID = -8686912247778799158L;
		
		public HorizontalScrollBar() {
			super(ScrollBar.HORIZONTAL);
		}
		@Override
		public int getViewLength() {
			return viewingWidth();
		}
		@Override
		public void onScroll(int scroll_amount) {
			scroll_x = scroll_amount;
			onScrollPanel(scroll_x, scroll_y);
		}
	}
}
