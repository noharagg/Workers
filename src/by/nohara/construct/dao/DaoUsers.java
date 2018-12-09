package by.nohara.construct.dao;

import by.nohara.construct.db.DB;
import by.nohara.construct.entity.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoUsers implements DaoInterface<Users>{
	
	private DB db;
	
	public DaoUsers(DB db) {
		this.db = db;
	}

	@Override
	public void insert(Users ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?)");
			ps.setInt(1, ob.getId());
			ps.setString(2, ob.getLogin());
			ps.setString(3, ob.getPassword());
			ps.setInt(4, ob.getRole());
			ps.setInt(5, ob.getDel_status());
			ps.setInt(6, ob.getEff());

			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Users ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("DELETE FROM users WHERE id = ?");
			ps.setInt(1, ob.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Users ob) {
		try {
			PreparedStatement ps = db.getCn().prepareStatement("UPDATE users SET login = ?, password = ?, role = ?, del_status = ?, eff = ? WHERE id = ?");
			ps.setString(1, ob.getLogin());
			ps.setString(2, ob.getPassword());
			ps.setInt(3, ob.getRole());
			ps.setInt(4, ob.getDel_status());
			ps.setInt(5, ob.getEff());
			ps.setInt(6, ob.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Users get(int id) {
		ResultSet rs = db.query("SELECT * FROM users WHERE id = " + id);
		Users user = null;
		try {
			if(rs.next()) {
				user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
