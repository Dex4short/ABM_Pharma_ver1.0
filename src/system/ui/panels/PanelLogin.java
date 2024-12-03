package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.Button;
import components.Panel;
import components.fields.PasswordField;
import extras.Graphix;
import extras.Settings;
import extras.Utilities;
import oop.Access;
import res.Resource;
import system._default_.Login;
import system.ui.Window;

public abstract class PanelLogin extends JPanel implements Login{
	private static final long serialVersionUID = 1519322616962176557L;
	private final int w=300,h=400;
	private Panel roundRect_panel;
	private PasswordField password_field;
	private Graphics2D g2d;

	public PanelLogin() {
		setOpaque(false);
		setLayout(null);
		
		roundRect_panel = new Panel();
		roundRect_panel.setArc(20);
		roundRect_panel.setLayout(new BorderLayout(0, 30));
		roundRect_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		add(roundRect_panel);
		
		JLabel logo = new JLabel(new ImageIcon(Resource.get("ABM LOGO.png")));
		roundRect_panel.add(logo, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
		roundRect_panel.add(panel, BorderLayout.SOUTH);
		
		password_field = new PasswordField();
		password_field.setName("password_field");
		password_field.addKeyListener(new PasswordKeyListener());
		panel.add(password_field);
		
		Button login_btn = new Button();
		login_btn.setText("Log In");
		login_btn.addActionListener(new LoginActionListener());
		panel.add(login_btn);
		
		Button cancel_exit = new Button.Secondary();
		cancel_exit.setText("Cancel or Exit");
		cancel_exit.addActionListener(new ExitActionListener());
		panel.add(cancel_exit);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				roundRect_panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
			}
		});
		roundRect_panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
	}
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		Graphix.Shadow.cast_shadow(roundRect_panel.getArea(), roundRect_panel.getX(), roundRect_panel.getY(), g2d);
		super.paint(g2d);
	}
	@Override
	public void onInputPassword(Access access) {
		PasswordField password_field = (PasswordField) Utilities.findComponentByName(roundRect_panel, "password_field");
				
		if(access != null) {
			switch(access.getRole()){
			case adm:
				password_field.setText("");
				password_field.setMessage("Welcome Admin...");
				Window.floatMessage("Welcome Admin");
				openAdminInterface();
				break;
			case emp:
				password_field.setText("");
				password_field.setMessage("Welcome Employee...");
				Window.floatMessage("Welcome Employee");
				openEmployeeInterface();
				break;
			default:
				password_field.setText("");
				password_field.setMessage("Unidentified...");
				Window.floatMessage("Unidentified");
				break;
			}
		}
		else {
			password_field.setText("");
			password_field.setMessage("Wrong Password!");
			Window.floatMessage("Wrong Password!");
		}
	}
	
	public abstract void openAdminInterface();
	public abstract void openEmployeeInterface();
	
	private class PasswordKeyListener extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				inputPassword(password_field.getPassword());
			}
		}
	}
	private class LoginActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			inputPassword(password_field.getPassword());			
		}
	}
	private class ExitActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
