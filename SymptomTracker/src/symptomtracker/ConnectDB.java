package symptomtracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	
	// Database login information
	final String URL = "jdbc:mysql://localhost:3306/symptom_tracker";
	final String USER = "root";
	final String PASS = "ccuiMySQL";
	
	Connection conn = null;	
	
	/**
	 * Get a connection to the database. 
	 * Connection is closed when query is 
	 * completed.
	 */
	public void connectDB() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Returns the database connection
	 */
	public Connection getConn() {
		return conn;
	}

}
