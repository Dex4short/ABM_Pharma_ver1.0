package system.ui.panels.actions;

import oop.Remarks;
import system.ui.Window;

public abstract class ActionPanelRemarksDispose extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;
	
	public ActionPanelRemarksDispose() {
		super("Dispose Remarks");	
	}
	@Override
	public void onRemarksOk(Remarks remarks) {
		Window.load( () -> {
			disposeRemarksOk(remarks);
		}, "Disposing...");
	}
	public void disposeRemarksOk(Remarks remarks) {
		remarks.setDetails(getTitleField().getText() + "\n \n" + getRemarksField().getText());
		onDisposeRemarksOk(remarks);
	}
	
	public abstract void onDisposeRemarksOk(Remarks remarks);
}
