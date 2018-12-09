package by.nohara.construct.dao;

import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Workers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoWorkers implements DaoInterface<Workers>{
	
	private DB db;
	
	public DaoWorkers(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Workers ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO workers VALUES (?,?,?)");
			ps.setInt(1, ob.getId());
			ps.setString(2, ob.getLogin());
			ps.setInt(3, ob.getEff());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Workers ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("DELETE FROM workers WHERE id = ?");
			ps.setInt(1, ob.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Workers ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE workers SET login =?, eff = ? , WHERE id = ?");
			ps.setString(1, ob.getLogin());
			ps.setInt(2, ob.getEff());
			ps.setInt(3, ob.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Workers get(int id) {
		ResultSet rs = db.query("SELECT * FROM workers WHERE id = " + id);
		Workers user = null;
		try {
			if(rs.next()) {
				user = new Workers(rs.getInt(1), rs.getString(2),rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}

