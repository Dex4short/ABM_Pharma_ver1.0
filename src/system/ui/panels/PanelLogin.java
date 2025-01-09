package system.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import components.Label;
import components._misc_.Graphix;
import components.fields.PasswordField;
import components.fields.TextField;
import components.panels.Panel;
import res.Resource;
import system._default_.Login;
import system._default_.Settings;
import system.ui.Window;

public abstract class PanelLogin extends Panel implements Login, KeyListener{
	private static final long serialVersionUID = 1519322616962176557L;
	private final int w=300,h=400;
	private Panel roundRect_panel;
	private TextField username_field;
	private PasswordField password_field;

	public PanelLogin() {
		setLayout(null);
		setBackground(new Color(0,0,0,0));
		
		roundRect_panel = new Panel();
		roundRect_panel.setArc(20);
		roundRect_panel.setLayout(new BorderLayout(0, 30));
		roundRect_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		add(roundRect_panel);
		
		Label logo = new Label(new ImageIcon(Resource.get("ABM LOGO.png")));
		logo.setHorizontalAlignment(Label.CENTER);
		logo.setVerticalAlignment(Label.CENTER);
		roundRect_panel.add(logo, BorderLayout.CENTER);
		
		Panel panel = new Panel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(4, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
		roundRect_panel.add(panel, BorderLayout.SOUTH);
		
		username_field = new TextField("username");
		username_field.setName("username_field");
		panel.add(username_field);
		
		password_field = new PasswordField();
		password_field.setName("password_field");
		password_field.addKeyListener(this);
		panel.add(password_field);
		
		Button login_btn = new Button();
		login_btn.setText("Log In");
		login_btn.addActionListener(e -> inputPassword(password_field.getPassword()));
		panel.add(login_btn);
		
		Button cancel_exit = new Button.Secondary();
		cancel_exit.setText("Cancel or Exit");
		cancel_exit.addActionListener(e -> System.exit(0));
		panel.add(cancel_exit);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				roundRect_panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
			}
		});
		roundRect_panel.setBounds((getWidth()/2) - (w/2), (getHeight()/2) - (h/2), w, h);
		
	}
	private Graphics2D g2d;
	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D)g;
		Settings.rendering_hint(g2d);
		Graphix.Shadow.cast_shadow(roundRect_panel.getArea(), roundRect_panel.getX(), roundRect_panel.getY(), g2d);
		super.paint(g2d);
	}
	@Override
	public void onUnidentifiedEntry() {
		password_field.setText("");
		password_field.setMessage("Unidentified...");
		Window.floatMessageAndBeep("Unidentified");
	}
	@Override
	public void onWrongPassword() {
		password_field.setText("");
		password_field.setMessage("Wrong Password!");
		Window.floatMessageAndBeep("Wrong Password!");
	}
	@Override
	public void onAdminAccess() {
		password_field.setText("");
		password_field.setMessage("Welcome Admin...");
		onOpenAdminInterface();
	}
	@Override
	public void onEmployeeAccess() {
		password_field.setText("");
		password_field.setMessage("Welcome Employee...");
		onOpenEmployeeInterface();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//none
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) inputPassword(password_field.getPassword());
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//none
	}
	
	public abstract void onOpenAdminInterface();
	public abstract void onOpenEmployeeInterface();
	
}
