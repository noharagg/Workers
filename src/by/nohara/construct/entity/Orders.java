package by.nohara.construct.entity;

import java.util.Date;

public class Orders {
	
	private int id_orders;
	private int  user_id;
	private String workers_id;
	private int cost;
	private String data;
	
	public Orders(int id_orders, int user_id, String workers_id,int cost, String data) {
		this.id_orders = id_orders;
		this.user_id = user_id;
		this.workers_id = workers_id;
		this.cost = cost;
		this.data = data;
	}
	
	
	public Orders(int user_id, String workers_id, int cost) {
		this.user_id = user_id;
		this.workers_id = workers_id;
		this.cost = cost;
		
		Date date = new Date();
		this.data = date.toString();
	}


	public Orders(int id_orders) {
		this.id_orders = id_orders;
	}
	
	public Orders() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id_orders;
		result = prime * result + user_id;
		result = prime * result + ((workers_id == null) ? 0 : workers_id.hashCode());
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
		Orders other = (Orders) obj;
		if (cost != other.cost)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id_orders != other.id_orders)
			return false;
		if (user_id != other.user_id)
			return false;
		if (workers_id == null) {
			if (other.workers_id != null)
				return false;
		} else if (!workers_id.equals(other.workers_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orders [id_orders=" + id_orders + ", user_id=" + user_id + ", workers_id=" + workers_id + ", cost="
				+ cost + ", data=" + data + "]";
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getId_orders() {
		return id_orders;
	}

	public void setId_orders(int id_orders) {
		this.id_orders = id_orders;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getWorkers_id() {
		return workers_id;
	}

	public void setWorkers_id(String workers_id) {
		this.workers_id = workers_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
	
}
