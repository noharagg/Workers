package by.nohara.construct.frame;

import by.nohara.construct.db.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersFrame extends JFrame{

	private JPanel panel;
	private JButton  back;
	private DB db;
	private Table table;
	private JScrollPane scroll;
	
	public OrdersFrame(DB db) {
		this.db = db;
		setTitle("OrdersFrame");
		setSize(700, 400);
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

		table = new Table(db.query("SELECT * FROM orders ")) {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
			
		};
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(600, 200));
		
		panel.add(scroll);
		panel.add(back);
		
		add(panel);
	}
	
	public void action() {
		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();				
				
			}
		});
		
	}
}


