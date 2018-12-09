package by.nohara.construct.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import by.nohara.construct.db.DB;

public class LoginFrame extends JFrame {

	private JPanel panel, panel1, panel2, panel3;
	private JTextField login;
	private JPasswordField password;
	private JLabel labelLogin, labelPassword;
	private JButton connect, registration;
	private DB db;

	public LoginFrame(DB db) {
		this.db = db;
		setTitle("LoginFrame");
		setSize(250, 200);
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
		panel.setLayout(new GridLayout(8, 16));
		panel.add(panel1);
		panel.add(panel3);
		login = new JTextField("admin", 20);
		password = new JPasswordField("admin", 20);

		labelLogin = new JLabel("login");
		labelPassword = new JLabel("pass");

		registration = new JButton("�����������");
		connect = new JButton("�����");

		panel.add(labelLogin);
		panel.add(login);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(connect);
		panel.add(registration);

		add(panel);
	}

	public void action() {
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ResultSet rs = db.query("SELECT * FROM users WHERE login = '" + login.getText() + "' AND password = '"
						+ String.valueOf(password.getPassword()) + "'");
				try {
					if (rs.next()) {
						if (rs.getInt("del_status") == 1) {
							if (rs.getInt("role") == 1) {
								dispose();
								new UsersFrame(db, rs.getInt("id"));
								JOptionPane.showMessageDialog(panel, "USER", "�� ������",
										JOptionPane.INFORMATION_MESSAGE);
							} else if (rs.getInt("role") == 2) {
								dispose();
								new AdminFrame(db);
								JOptionPane.showMessageDialog(panel, "ADMIN", "Connnect",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								//dispose();
								//new WorkersFrame(db);
								JOptionPane.showMessageDialog(panel, "WORKER", "�� ������",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(panel, "�� �������������", "������",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(panel, "������ ��� ����� �� ��������", "������",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		registration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegistFrame(db);
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