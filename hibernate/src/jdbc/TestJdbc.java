package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



public class TestJdbc {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/key";
		String userName = "root";
		String password = "root";
		System.out.println(url+userName+password);
		try {
			
			System.out.println("Connecting To Database ..." + url);
			
			Connection connection =DriverManager.getConnection(url, userName, password);
			System.out.println(" successfully Connected To Database ...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}
