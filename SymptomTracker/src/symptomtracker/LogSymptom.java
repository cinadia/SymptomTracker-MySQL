package symptomtracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogSymptom {
	
	// TODO: figure out how to make client lol
	static int client = 1;
	static String location;
	static String symptom;
	static java.sql.Date date;
	static int severity = -1; // Default non-selected value
	static int length = -1; //   "
	static int score;
	
	// TODO: client column
	
	// Insert data into symptoms_log
	public static void runLogSymptom() {
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO symptoms_log (client_id, location_id, symptom_type_id, date, severity, length, final_score) "
					+ "VALUES ("
					+ "'1'," // tester client value
					+ "'" + getLocationID(location) + "', "
					+ "'" + getSymptomTypeID() + "', "
					+ "'" + date + "', " 
					+ "'" + severity + "', " 
					+ "'" + length + "', " 
					+ "'" + score + "')"; 
			System.out.println(query);
			
			// execute query
			stat.executeUpdate(query);
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	// Get Primary Key for Location
	public static String getLocationID(String loc) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
		
		int location_id = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT location_id FROM symptom_locations " 
					+ "WHERE location = " 
					+ "'" + loc + "'";
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location_id = rs.getInt("location_id");
			
			System.out.println(Integer.toString(location_id));
			return Integer.toString(location_id);
			
		} catch (Exception e){
			e.printStackTrace();
			return Integer.toString(location_id);
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	// Get Primary Key for Symptom Type
	public static String getSymptomTypeID() {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
		
		int symptom_type_id = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT symptom_type_id FROM symptom_types " 
					+ "WHERE symptom = " 
					+ "'" + symptom + "'";
			
			System.out.println(query);
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			symptom_type_id = rs.getInt("symptom_type_id");
			System.out.println(Integer.toString(symptom_type_id));
			
			return Integer.toString(symptom_type_id);
			
			
		} catch (Exception e){
			e.printStackTrace();
			return Integer.toString(symptom_type_id);
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// Setters
	public static void setLocation(String l) {
		location = l;
		System.out.println(location);
	}
	
	public static void setSymptom(String s) {
		symptom = s;
		System.out.println(symptom);
	}
	
	public static void setDate(java.sql.Date d) {
		date = d;
		System.out.println(date);
	}
	
	public static void setSeverity(int s) {
		severity = s;
		System.out.println(severity);
	}
	
	public static void setLength(int l) {
		length = l;
		System.out.println(length);
	}
	
	public static void setScore(int s) {
		score = s;
		System.out.println(score);
	}
	
	// Getters
	public static int getSeverity() {
		return severity;
	}
	
	public static int getLength() {
		return length;
	}

}

