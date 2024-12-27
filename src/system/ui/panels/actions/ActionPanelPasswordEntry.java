package system.ui.panels.actions;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import components.Label;
import components._misc_.Graphix;
import components.fields.PasswordField;
import components.panels.ActionPanel;
import res.Resource;
import system._default_.Login;
import system.ui.Window;

public abstract class ActionPanelPasswordEntry extends ActionPanel implements Login{
	private static final long serialVersionUID = 8111038317867184487L;
	private PasswordField password_field;
	private boolean isAdminOnly;
	
	public ActionPanelPasswordEntry() {
		super("Input Password");
		
		getPanelBody().setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
		
		ImageIcon 
		img_ico = Resource.getAsImageIcon("security.png");
		img_ico = Graphix.alterImageIcon(img_ico, main_color[0], null);
		Label logo = new Label(img_ico);
		getPanelBody().add(logo);
		
		password_field = new PasswordField();
		password_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { if(e.getKeyCode() == KeyEvent.VK_ENTER) onOk(); }
		});
		getPanelBody().add(password_field);
		
		setAdminOnly(true);
	}
	@Override
	public void onOk() {
		Window.load(() -> inputPassword(password_field.getPassword()) , "Accessing...");
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onAdminAccess() {
		passwordOk();
		Window.floatMessage("Admin access granted");
	}
	@Override
	public void onEmployeeAccess() {
		if(isAdminOnly) {
			Window.floatMessage("Access denied");
		}
		else {
			passwordOk();
			Window.floatMessage("Employee access granted");
		}
	}
	@Override
	public void onWrongPassword() {
		Window.floatMessageAndBeep("Wrong Password, Please try agian...");
	}
	@Override
	public void onUnidentifiedEntry() {
		Window.floatMessageAndBeep("Unidentified");
	}
	public boolean isAdminOnly() {
		return isAdminOnly;
	}
	public void setAdminOnly(boolean isAdminOnly) {
		this.isAdminOnly = isAdminOnly;
	}
	public void passwordOk() {
		onPasswordOk();
		Window.getStackPanel().popPanel(this);
	}
	
	public abstract void onPasswordOk();
	
}
