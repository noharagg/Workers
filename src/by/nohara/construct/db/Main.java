package by.nohara.construct.db;

public class Main {

	public static void main(String[] args) {
		
		WorkDB.deleteDB("jdbc:mysql://127.0.0.1/", "constr", "root", "root");
		
		WorkDB.createDB("jdbc:mysql://127.0.0.1/", "constr", "root", "root");
		
	
	}

}
