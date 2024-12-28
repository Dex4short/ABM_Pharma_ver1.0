package system.ui.panels.actions.security;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Button;
import components.Label;
import components.fields.PasswordField;
import components.panels.ActionPanel;
import components.panels.Panel;
import system.enumerators.SecurityRole;
import system.ui.Window;

public abstract class ActionPanelSecuritySettings extends ActionPanel{
	private static final long serialVersionUID = 8164267981664270565L;
	
	public ActionPanelSecuritySettings() {
		super("Security Settings");
		
		getPanelBody().setLayout(new GridLayout(2, 1));
		
		getPanelBody().add(new PasswordsPanel("Admin") {
			private static final long serialVersionUID = 4315846683950135131L;
			@Override
			public void onChangePassword(char[] old_pass, char[] new_pass) {
				changeAdminPassword(old_pass, new_pass);
			}
		});
		
		getPanelBody().add(new PasswordsPanel("Employee") {
			private static final long serialVersionUID = 2576224608124509339L;
			@Override
			public void onChangePassword(char[] old_pass, char[] new_pass) {
				changeEmployeePassword(old_pass, new_pass);
			}
		});
	}
	@Override
	public void onOk() {
		Window.getStackPanel().popPanel();
	}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
	public void changeAdminPassword(char[] old_pass, char[] new_pass) {
		Window.load(() -> {
			try {
				onChangePassword(SecurityRole.adm, old_pass, new_pass);
				Window.floatMessage("Admin password updated");
			} catch (Exception e) {
				Window.floatMessageAndBeep(e.getMessage());
			}
		}, "Updating Admin Password...");
	}
	public void changeEmployeePassword(char[] old_pass, char[] new_pass) {
		Window.load(() -> {
			try {
				onChangePassword(SecurityRole.emp, old_pass, new_pass);
				Window.floatMessage("Employee password updated");
			} catch (Exception e) {
				Window.floatMessageAndBeep(e.getMessage());
			}
		}, "Updating Employee Password...");
	}
	
	public abstract void onChangePassword(SecurityRole role, char[] old_pass, char[] new_pass);
	
	private abstract class PasswordsPanel extends Panel{
		private static final long serialVersionUID = 3854412679318376547L;
		
		public PasswordsPanel(String title) {
			setLayout(new BorderLayout(10,10));
			
			Panel title_panel = new Panel();
			title_panel.setArc(5);
			title_panel.setForeground(main_color[3]);
			title_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			title_panel.add(new Label(title));
			add(title_panel, BorderLayout.NORTH);
			
			Panel center_panel = new Panel();
			center_panel.setLayout(new BorderLayout());
			add(center_panel, BorderLayout.CENTER);
			
			Panel passwords_panel = new Panel();
			passwords_panel.setLayout(new GridLayout(3, 1));
			center_panel.add(passwords_panel, BorderLayout.NORTH);
			
			PasswordBar currentPass_bar = new PasswordBar("Current Password");
			passwords_panel.add(currentPass_bar);

			PasswordBar newPass_bar = new PasswordBar("New Password");
			passwords_panel.add(newPass_bar);
			
			ButtonBar btnBar_changePass = new ButtonBar("Change Password") {
				private static final long serialVersionUID = -61575560601383785L;
				@Override
				public void onClickButton() {
					changePassword(currentPass_bar.getPassword(), newPass_bar.getPassword());
					currentPass_bar.clear();
					newPass_bar.clear();
				}
			};
			passwords_panel.add(btnBar_changePass);
		}
		public void changePassword(char current_pass[], char new_pass[]) {
			if(current_pass.length > 16 || new_pass.length > 16) {
				Window.floatMessageAndBeep("Passwords too long, (16 characters only)");
				return;
			}
			else if(current_pass.length == 0 || new_pass.length == 0) {
				Window.floatMessageAndBeep("Password input is empty...");
				return;
			}
			onChangePassword(current_pass, new_pass);
		}
		
		public abstract void onChangePassword(char current_pass[], char new_pass[]);
		
		private class PasswordBar extends Panel {
			private static final long serialVersionUID = 8569332381128111766L;
			private PasswordField pass_field;

			public PasswordBar(String str) {
				setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
				
				Label label = new Label(str);
				label.setPreferredSize(new Dimension(120, 30));
				label.setMaximumSize(getPreferredSize());
				label.setMinimumSize(getPreferredSize());
				add(label);
				
				pass_field = new PasswordField();
				pass_field.setColumns(10);
				add(pass_field);				
			}
			public char[] getPassword() {
				return pass_field.getPassword();
			}
			public void clear() {
				pass_field.setText("");
			}
		}
		
		private abstract class ButtonBar extends Panel implements ActionListener{
			private static final long serialVersionUID = -3174686007046161689L;

			public ButtonBar(String str) {
				setLayout(new FlowLayout(FlowLayout.CENTER));

				Button.Tertiary changePass_btn = new Button.Tertiary(str);
				changePass_btn.setPreferredSize(new Dimension(120, 30));
				changePass_btn.setMaximumSize(getPreferredSize());
				changePass_btn.setMinimumSize(getPreferredSize());
				changePass_btn.addActionListener(this);
				add(changePass_btn);
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				clickButton();
			}
			public void clickButton() {
				onClickButton();
			}
			
			public abstract void onClickButton();
			
		}
	}
}
