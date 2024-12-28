package system.ui.panels.actions.selling;

import java.awt.FlowLayout;

import components.Label;
import components.fields.PercentageField;
import components.panels.ActionPanel;
import system.objects.Percentage;
import system.ui.Window;

public abstract class ActionPanelDiscountEntry extends ActionPanel{
	private static final long serialVersionUID = -1123828018393489530L;
	private PercentageField discount_field;
	
	public ActionPanelDiscountEntry() {
		super("Input Discount");
		
		getPanelBody().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		Label percent = new Label("Discount: ");
		getPanelBody().add(percent);
		
		discount_field = new PercentageField("0%");
		discount_field.setColumns(10);
		getPanelBody().add(discount_field);
		
	}
	@Override
	public void onOk() {
		discountOk();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void discountOk() {
		try {
			Percentage discount = discount_field.getPercent();
			onDiscountOk(discount);
			
			Window.getStackPanel().popPanel(this);
			Window.floatMessage("Sale: " + discount.toString() + " discount");
		} catch (Exception e) {
			Window.floatMessageAndBeep(e.getMessage());
		}
	}
	
	public abstract void onDiscountOk(Percentage discount);

}
