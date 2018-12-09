package by.nohara.construct.entity;

public class Users {
	
	private int id;
	private String login;
	private String password;
	private int role;
	private int del_status;
	private int eff;
	
	public Users(int id, String login, String password, int role, int del_status,int eff) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.del_status = del_status;
		this.eff = eff;
	}
	
	public Users(String login, String password, int role,int eff) {
		this.login = login;
		this.password = password;
		this.del_status = 1;
		this.eff = eff;
		if(eff != 0) {
			this.role = 3;
		} else {
			this.role = role;
		}
	}
	public Users(int id) {
		this.id = id;
	}
	
	public Users(String login, String password) {
		this.login = login;
		this.password = password;
		this.role = 1;
		this.del_status = 1;
		this.eff = 0;

	}
	
	public Users() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + del_status;
		result = prime * result + eff;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (del_status != other.del_status)
			return false;
		if (eff != other.eff)
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", del_status="
				+ del_status + ", eff=" + eff + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getDel_status() {
		return del_status;
	}

	public void setDel_status(int del_status) {
		this.del_status = del_status;
	}

	public int getEff() {
		return eff;
	}

	public void setEff(int eff) {
		this.eff = eff;
	}
	
	

}
