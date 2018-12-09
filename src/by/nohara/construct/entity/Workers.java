package by.nohara.construct.entity;

public class Workers {
	
	private int id;
	private String login;
	private int eff;
	
	public Workers(int id, String login,int eff) {
		this.id = id;
		this.login = login;
		this.eff = eff;
	}
	
	public Workers(int id) {
		this.id = id;
	}
	
	
	public Workers() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eff;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Workers other = (Workers) obj;
		if (eff != other.eff)
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Workers [id=" + id + ", login=" + login + ", eff=" + eff + "]";
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

	public int getEff() {
		return eff;
	}

	public void setEff(int eff) {
		this.eff = eff;
	}

	
	

}

