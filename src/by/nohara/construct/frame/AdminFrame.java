package by.nohara.construct.frame;
import by.nohara.construct.dao.DaoUsers;
import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Users;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class AdminFrame extends JFrame{

	private JPanel panel;
	private JButton update, back;
	private DB db;
	private Table table;
	private JScrollPane scroll;
	
	public AdminFrame(DB db) {
		this.db = db;
		setTitle("AdminFrame");
		setSize(550, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initComponents();
		action();
		setVisible(true);
	}
	
	public void initComponents() {
		panel = new JPanel();
		update = new JButton("Update");
		back = new JButton("back");

		update.setPreferredSize(new Dimension(100, 50));
		back.setPreferredSize(new Dimension(100, 50));

		table = new Table(db.query("SELECT * FROM users"));
		scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(500, 150));
		panel.add(update);
		panel.add(back);
		panel.add(scroll);

		add(panel);
	}
	
	public void action() {
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DaoUsers du = new DaoUsers(db);

				du.update(new Users(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
						String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
						String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
						Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
						Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
						Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)))));
				
				updateTable();
			}
		});
		
		
		
		
		table.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					DaoUsers du = new DaoUsers(db);
					du.update(new Users(Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
							String.valueOf(table.getValueAt(table.getSelectedRow(), 1)),
							String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
							Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 3))),
							Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 4))),
							Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)))));
					
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
	
	public void updateTable() {
		panel.remove(scroll);

		table = new Table(db.query("SELECT * FROM users"));

		scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(500, 150));

		panel.add(scroll);

		panel.updateUI();
	}
}

