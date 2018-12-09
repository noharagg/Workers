package by.nohara.construct.frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import by.nohara.construct.dao.DaoOrders;
import by.nohara.construct.dao.DaoWorkers;
import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Orders;
import by.nohara.construct.entity.Workers;

public class UsersFrame extends JFrame{
	public static final int COST = 350;
	private JPanel panel;
	private JTextField login;
	private JPasswordField password;
	private JLabel labelLogin, labelPassword;
	private JButton update, back, create,show,refreh;
	private DB db;
	private Table table, table2;
	private JScrollPane scroll,scroll2;
	private int index;
	public UsersFrame(DB db, int index) {
		this.db = db;
		this.index = index;
		setTitle("UsersFrame");
		setSize(650, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initComponents();
		action();
		setVisible(true);
	}
	
	public  void createWorkers() {
		db.update("TRANCATE TABLE workers");
		updateTable();
	}
	
	public void initComponents() {
		panel = new JPanel();
		update = new JButton("Update");
		back = new JButton("back");
		create = new JButton("create");
		show = new JButton("show");
		refreh = new JButton("refreh");
		update.setPreferredSize(new Dimension(100, 50));
		back.setPreferredSize(new Dimension(100, 50));
		create.setPreferredSize(new Dimension(100, 50));
		show.setPreferredSize(new Dimension(100, 50));
		refreh.setPreferredSize(new Dimension(100, 50));

		table = new Table(db.query("SELECT id,login,eff FROM users WHERE eff > 0")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
		};
		scroll = new JScrollPane(table);
		
		table2 = new Table(db.query("SELECT id,login,eff FROM workers")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
		};
		scroll2 = new JScrollPane(table2);
		scroll.setPreferredSize(new Dimension(650, 150));
		
		scroll2.setPreferredSize(new Dimension(650, 150));

		panel.add(scroll);
		panel.add(update);
		panel.add(back);
		panel.add(create);
		panel.add(show);
		panel.add(refreh);
		panel.add(scroll2);


		add(panel);
	}
		
	public void action() {
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DaoWorkers du = new DaoWorkers(db);
				du.insert(new Workers(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
						String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
						Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)))));
				updateTable();
				
				
			}
		});
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				DaoOrders du = new DaoOrders(db);
				du.insert(new Orders(index,
						work(db.query("SELECT id FROM workers")),
						cost(db.query("SELECT eff FROM workers"))
						));
				
				updateTable();
				JOptionPane.showMessageDialog(panel, "Orders create!!", "Orders", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
		
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new OrdersFrame(db);
				
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame(db);
				
				
			}
		});
		
		refreh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				db.update("TRUNCATE TABLE workers")		;		
				updateTable();
			}
		});
		
	}
	
	public void updateTable() {
		panel.remove(scroll2);

		
		table2 = new Table(db.query("SELECT id,login,eff FROM workers")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
		};
		scroll2 = new JScrollPane(table2);
		
		scroll2.setPreferredSize(new Dimension(650, 150));
		panel.add(scroll2);
		panel.updateUI();
	}
	
	public static String work(ResultSet rs) {
		String str = "";

		try {
			while(rs.next()) {
				
					str = str.concat(rs.getString(1) + ",");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static int cost(ResultSet rs) {
		int sum = 0;
		try {
			while(rs.next()) {
				
					sum += Integer.valueOf(rs.getString(1)) * COST;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
}
