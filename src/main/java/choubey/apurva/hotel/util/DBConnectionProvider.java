package choubey.apurva.hotel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
	
	public static Connection getConnection() {
		
		Connection connection = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/hotelmanagement";
		String user = "root";
		String password = "root";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while getting connection");
		}
		
		return connection;
	}
}
