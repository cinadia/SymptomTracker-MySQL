package symptomtracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class RegAndConn {
	
	final String URL = "jdbc:mysql://localhost:3306/symptom_tracker";
	final String USER = "root";
	final String PASS = "ccuiMySQL";
	
	Connection conn = null;
	
	// register JDBC driver
	public void registerDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// get connection to database
	// when calling method, remember to close database
	public void connectDB() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}

}
