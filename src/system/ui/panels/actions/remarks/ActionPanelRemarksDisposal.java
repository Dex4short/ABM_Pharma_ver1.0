package system.ui.panels.actions.remarks;

import system.objects.Date;
import system.objects.Time;
import system.ui.Window;

public abstract class ActionPanelRemarksDisposal extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;
	
	public ActionPanelRemarksDisposal() {
		super("Disposal Remarks");	
	}
	@Override
	public void onRemarksOk(Time time, Date date, String details) {
		Window.load( () -> {
			disposeRemarksOk(time, date, details);
		}, "Disposing...");
	}
	public void disposeRemarksOk(Time time, Date date, String details) {
		onDisposeRemarksOk(time, date, details);
	}
	
	public abstract void onDisposeRemarksOk(Time time, Date date, String details);
}
