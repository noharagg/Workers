package by.nohara.construct.frame;
import by.nohara.construct.db.DB;
import by.nohara.construct.db.WorkDB;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class StartFrame extends JFrame{
	
	private JPanel panel;
	private JPanel panel1, panel2, panel3;
	private JTextField url, name, login;
	private JPasswordField password;
	private JLabel labelUrl, labelLogin, labelPassword, labelName;
	private JButton create, delete, connect;

	public StartFrame() {
		setTitle("StartFrame");
		setSize(250, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		createComponents();
		initComponents();
		action();
		setVisible(true);
	}
	
	public void initComponents() {
		panel = new JPanel();
	
		panel.setLayout(new GridLayout(16,16));
			panel.add(panel1);
			panel.add(panel3);
			
		url = new JTextField("jdbc:mysql://127.0.0.1/", 20);
		name = new JTextField("constr", 20);
		login = new JTextField("root", 20);
		password = new JPasswordField("root", 20);
		
		labelUrl = new JLabel("url");
		labelName = new JLabel("name");
		labelLogin = new JLabel("login");
		labelPassword = new JLabel("pass");
		
		create = new JButton("create");
		delete = new JButton("delete");
		connect = new JButton("drop");
		
		panel.add(labelUrl);
		panel.add(url);
		panel.add(labelName);
		panel.add(name);
		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(create);
		panel.add(delete);
		panel.add(connect);
		add(panel);

	}
	
	public void action() {
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WorkDB.createDB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WorkDB.deleteDB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
				
			}
		});
		
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DB db = new DB(url.getText(), name.getText(), login.getText(), String.valueOf(password.getPassword()));
				dispose();
				new LoginFrame(db);
				
			}
		});
	}
	
	public void createComponents() {
		panel1 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel2 = new JPanel();
		panel2.setBackground(Color.GREEN);
		panel3 = new JPanel();
		panel3.setBackground(Color.RED);
	}
}
