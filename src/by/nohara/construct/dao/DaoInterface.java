package by.nohara.construct.dao;

public interface DaoInterface <T>{
	
	 void insert(T ob);
	
	 void delete(T ob);
	
	 void update(T ob);
	
	 T get(int id);

}
