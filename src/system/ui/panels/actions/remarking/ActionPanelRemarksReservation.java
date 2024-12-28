package system.ui.panels.actions.remarking;

import system.objects.Date;
import system.objects.Remarks;
import system.objects.Time;
import system.ui.Window;

public abstract class ActionPanelRemarksReservation extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;

	public ActionPanelRemarksReservation() {
		super("Reservation Remarks");
	}
	@Override
	public void onRemarksOk(Time time, Date date, String details) {
		Window.load( () -> {
			reserveRemarksOk(time, date, details);
		}, "Reserving...");
	}
	public void reserveRemarksOk(Time time, Date date, String details) {
		onReserveRemarksOk(new Remarks(-1, date, time, details));
	}
	
	public abstract void onReserveRemarksOk(Remarks remarks);
	
}
