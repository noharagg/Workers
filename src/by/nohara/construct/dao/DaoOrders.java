package by.nohara.construct.dao;

import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoOrders implements DaoInterface<Orders>{

	private DB db;
	
	public DaoOrders(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Orders ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?)");
			ps.setInt(1, ob.getId_orders());
			ps.setInt(2, ob.getUser_id());
			ps.setString(3, ob.getWorkers_id());
			ps.setInt(4, ob.getCost());
			ps.setString(5, ob.getData());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Orders ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("DELETE FROM orders WHERE id = ?");
			ps.setInt(1, ob.getId_orders());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Orders ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE orders SET user_id =?, workers_id = ?, cost = ?, data = ? WHERE id_orders = ?");
			ps.setInt(1, ob.getUser_id());
			ps.setString(2, ob.getWorkers_id());
			ps.setInt(3, ob.getCost());
			ps.setString(4, ob.getData());
			ps.setInt(5, ob.getId_orders());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Orders get(int id_orders) {
		ResultSet rs = db.query("SELECT * FROM orders WHERE id_orders = " + id_orders);
		Orders order = null;
		try {
			if(rs.next()) {
				order = new Orders(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

}