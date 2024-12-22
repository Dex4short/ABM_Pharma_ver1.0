package system.ui.panels.actions;

import oop.Remarks;
import system.ui.Window;

public abstract class ActionPanelRemarksReserve extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;

	public ActionPanelRemarksReserve() {
		super("Reserve Remarks");
	}
	@Override
	public void onRemarksOk(Remarks remarks) {
		Window.load( () -> {
			reserveRemarksOk(remarks);
		}, "Reserving...");
	}
	public void reserveRemarksOk(Remarks remarks) {
		remarks.setDetails(getTitleField().getText() + "\n \n" + getRemarksField().getText());
		onReserveRemarksOk(remarks);
	}
	
	public abstract void onReserveRemarksOk(Remarks remarks);
}
