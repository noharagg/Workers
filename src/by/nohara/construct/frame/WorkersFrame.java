package by.nohara.construct.frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import by.nohara.construct.db.DB;

public class WorkersFrame extends JFrame {
	public static final int COST = 350;
	private JPanel panel;
	private JButton back;
	private DB db;
	private Table table, table2;
	private JScrollPane scroll, scroll2;

	public WorkersFrame(DB db) {
		this.db = db;
		setTitle("WorkersFrame");
		setSize(650, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initComponents();
		action();
		setVisible(true);
	}

	public void initComponents() {
		panel = new JPanel();
		back = new JButton("back");
		back.setPreferredSize(new Dimension(100, 50));

		// StringTokenizer st = new StringTokenizer(s, "\t\n\r,.");
		// while(st.hasMoreTokens()){
		// System.out.println(st.nextToken());
		// }

		table = new Table(db.query("SELECT * FROM orders")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

		};
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(650, 150));
		panel.add(scroll);
		panel.add(back);
		add(panel);
	}

	public void action() {
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame(db);

			}
		});
	}

}
