package by.nohara.construct.frame;

import by.nohara.construct.dao.DaoUsers;
import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistFrame extends JFrame{
	private JPanel panel;
	private JTextField login,role, eff;
	private JPasswordField password;
	private JLabel labelLogin, labelPassword, labelrole, labeleff;
	private JButton create, back;
	private Table table;

	private DB db;
	public RegistFrame(DB db) {
		this.db = db;
		setTitle("RegistFrame");
		setSize(250, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initComponents();
		action();
		setVisible(true);
	}
	
	public void initComponents() {
		panel = new JPanel();
		
		login = new JTextField("",20);
		password = new JPasswordField("", 20);
		role = new JTextField("1", 20);
		eff = new JTextField("0", 20);

		labelrole = new JLabel("role");
		labelLogin = new JLabel("login");
		labelPassword = new JLabel("pass");
		labeleff = new JLabel("eff(������ ��� ���� 3)");
		create = new JButton("�������");
		back = new JButton("�����");
		table = new Table(db.query("SELECT * FROM roles")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
		};

		
		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(labelrole);
		panel.add(role);
		panel.add(labeleff);
		panel.add(eff);
		
		panel.add(create);
		panel.add(back);
		panel.add(table);
		
		
		add(panel);
		
		
		
	}
	
	public void action() {
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DaoUsers du = new DaoUsers(db);
				if(Integer.parseInt(role.getText()) == 2) {
					JOptionPane.showMessageDialog(panel, "������ ���������������� ��� �����", "������", JOptionPane.ERROR_MESSAGE);
					dispose();
					new RegistFrame(db);
				} else {
					du.insert(new Users(login.getText(), String.valueOf(password.getPassword()), Integer.parseInt(role.getText()),Integer.parseInt(eff.getText())));
				dispose();
				new LoginFrame(db);
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame(db);
			}
		});
	}
}
