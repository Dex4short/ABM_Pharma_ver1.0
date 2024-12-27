package system.ui.panels.actions;

import system.objects.Date;
import system.objects.Remarks;
import system.objects.Time;
import system.ui.Window;

public abstract class ActionPanelRemarksDispose extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;
	
	public ActionPanelRemarksDispose() {
		super("Dispose Remarks");	
	}
	@Override
	public void onRemarksOk(Time time, Date date, String details) {
		Window.load( () -> {
			disposeRemarksOk(time, date, details);
		}, "Disposing...");
	}
	public void disposeRemarksOk(Time time, Date date, String details) {
		onDisposeRemarksOk(new Remarks(-1, date, time, details));
	}
	
	public abstract void onDisposeRemarksOk(Remarks remarks);
}
