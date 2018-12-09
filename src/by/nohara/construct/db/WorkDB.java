package by.nohara.construct.db;

import by.nohara.construct.dao.DaoOrders;
import by.nohara.construct.dao.DaoUsers;
import by.nohara.construct.entity.Orders;
import by.nohara.construct.entity.Users;
import by.nohara.construct.frame.StartFrame;

import java.sql.ResultSet;


public class WorkDB {
	
	private DB db;
	
	public static void createDB(String url, String name, String login, String password) {
		
		DB db = new DB(url, "", login, password);
		db.update("CREATE DATABASE " + name); 
		db.update("USE " + name);
		
		db.update("CREATE TABLE roles (id INT AUTO_INCREMENT,"
				+ "type VARCHAR(40) UNIQUE NOT NULL,"
				+ "PRIMARY KEY(id))");
		
		
		db.update("CREATE TABLE users (id INT AUTO_INCREMENT,"
				+ "login VARCHAR(21) UNIQUE NOT NULL,"
				+ "password VARCHAR(22) NOT NULL,"
				+ "role INT(1) DEFAULT 1,"
				+ "del_status INT(1) DEFAULT 1,"
				+ "eff INT(20) NOT NULL , "
				+ "PRIMARY KEY(id),"
				+ "FOREIGN KEY (role) REFERENCES roles (id))");
		
		db.update("CREATE TABLE workers (id INT AUTO_INCREMENT,"
				+ "login VARCHAR(21) UNIQUE NOT NULL,"
				+ "eff INT(20) NOT NULL , "
				+ "PRIMARY KEY(id))");
		
		db.update("CREATE TABLE orders (id_order INT AUTO_INCREMENT,"
				+ "user_id INT(20)  NOT NULL ,"
				+ "workers_id VARCHAR(40) NOT NULL,"
				+ "cost INT(10) NOT NULL, "
				+ "data VARCHAR(200) NOT NULL,"
				+ "PRIMARY KEY(id_order),"
				+ "FOREIGN KEY (user_id) REFERENCES users (id))");
		
		
		
		db.update("INSERT INTO roles VALUES (1, 'user')"); 
		db.update("INSERT INTO roles VALUES (2, 'admin')"); 
		db.update("INSERT INTO roles VALUES (3, 'worker')");
		
		DaoUsers du = new DaoUsers(db);
		du.insert(new Users("admin", "admin", 2 , 0));
		du.insert(new Users("admin1", "admin"));
		du.insert(new Users("admin2", "admin"));
		du.insert(new Users("admin3", "admin"));
		du.insert(new Users("admin4", "admin"));
		du.insert(new Users("admin5", "admin"));
		
		
		du.insert(new Users("Miha", "pass1",3 , 2));
		du.insert(new Users("tolia", "pass2",3 , 3));
		du.insert(new Users("Grisha", "pass3",3 , 4));
		du.insert(new Users("Anton", "pass4",3 , 1));
		du.insert(new Users("Yra", "pass5",3 , 3));
		du.insert(new Users("Vasia", "pass5",3 , 2));
		du.insert(new Users("ds ", "pass2",3 , 2));

		du.insert(new Users("d sd ", "pass2",3 , 3));

		du.insert(new Users("dwqd ", "pass2",3 , 1));

		du.insert(new Users("dqwdq ", "pass2",3 , 4));

		du.insert(new Users("dqwdqw", "pass2",3 , 5));
		
		ResultSet rs3 = db.query("SELECT * FROM users");
		db.showTable(rs3);
		
		DaoOrders du4 = new DaoOrders(db);
		du4.insert(new Orders( 4, "1,3,4", 500));
		ResultSet rs4 = db.query("SELECT * FROM orders");
		
		db.showTable(rs4);
		
		new StartFrame();
	}
	
	public static void deleteDB(String url, String name, String login, String password) {
		DB db = new DB(url, name, login, password);
		db.update("DROP DATABASE " + name);
	}
	
		
	

}
