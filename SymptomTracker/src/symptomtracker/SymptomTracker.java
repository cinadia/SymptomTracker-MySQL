package symptomtracker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// TODO: should everything be static or non-static?
// if nonstatic, does that mean whenever I want to 
// use a method I have to make an instance of that class first?
// where should I make the instance of the class?
public class SymptomTracker {
	
	// TODO: figure out how to make client lol
	static int client = 1;
	static String location;
	static String symptom;
	static java.sql.Date date;
	static int severity = -1; // Default non-selected value
	static int length = -1; //   "
	static int score;
	static String homeRem;
	
	static int userID;
	static String firstName;
	static String lastName;
	static String username;
	static String password;
	
	

	// SETTERS
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
	
	public static void setHomeRem(String hr) {
		homeRem = hr;
		System.out.println(homeRem);
	}
	
	public static void setUserID(int id) {
		userID = id;
		System.out.println(userID);
	}
	public static void setFirstName(String first) {
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET first_name = " + "'"+ first + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			System.out.println(query);
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
	public static void setLastName(String last) {
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET last_name = " + "'"+ last + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			System.out.println(query);
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
	
	public static void setUsername(String un) {
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET client_user = " + "'"+ un + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			System.out.println(query);
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
	
	/*
	 * Set user's password in the database
	 */
	//TODO: does the password instance variable update too?? does it need to?
	public static void setPassword(String pass) {
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET client_pass = " + "'"+ pass + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			System.out.println(query);
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
	
	// GETTERS
	public static int getSeverity() {
		return severity;
	}
	
	public static int getLength() {
		return length;
	}
	
	public static int getUserID() {
		return userID;
	}
	
	// TODO: probably dont need these next 4
	public static String getFirstName() {
		return firstName;
	}
	
	public static String getLastName() {
		return lastName;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static String getPassword() {
		return password;
	}
	
	/*Returns String[] of symptom locations 
	 * from database 
	 */
	public static String[] getSymptomLocations() {
		final long NUMLOC; // number of rows in symptom_locations
		String[] locations = null;
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			// get number of rows 
			String numQuery = "SELECT COUNT(*) AS totalCount FROM symptom_locations";
			rs = stat.executeQuery(numQuery);
			rs.next();
			NUMLOC = rs.getLong("totalCount");
			locations = new String[(int)NUMLOC];
	        
			
			// execute query
			String query = "SELECT location FROM symptom_locations";
			rs = stat.executeQuery(query);
			
			// add result set to list
			int i = 0;
			while (rs.next()) {
				locations[i] = rs.getString("location");
				i++;
			}
			return locations;
		} catch (Exception e){
			e.printStackTrace();
			return locations;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
	
	
	/* Returns String[] of symptoms
	 * from databases
	 */
	public static String[] getSymptoms() {
		final long NUMSYMS; // number of rows in symptom_locations
		String[] symptoms = null;
		
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			// get number of rows 
			String numQuery = "SELECT COUNT(*) AS totalCount FROM symptom_types";
			rs = stat.executeQuery(numQuery);
			rs.next();
			NUMSYMS = rs.getLong("totalCount");
			symptoms = new String[(int)NUMSYMS];
	        
			
			// execute query
			String locQuery = "SELECT symptom FROM symptom_types";
			rs = stat.executeQuery(locQuery);
			
			// add result set to list
			int i = 0;
			while (rs.next()) {
				symptoms[i] = rs.getString("symptom");
				i++;
			}
			return symptoms;
		} catch (Exception e){
			e.printStackTrace();
			return symptoms;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	/*
	 * Returns String[] of home remedies
	 * from database
	 */
	public static String[] getHomeRems() {
		final long NUM; // number of rows in symptom_locations
		String[] homeRems = null;
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			// get number of rows 
			String numQuery = "SELECT COUNT(*) AS totalCount FROM home_remedies";
			rs = stat.executeQuery(numQuery);
			rs.next();
			NUM = rs.getLong("totalCount");
			homeRems = new String[(int)NUM];
	        
			
			// execute query
			String query = "SELECT home_remedy FROM home_remedies";
			rs = stat.executeQuery(query);
			
			// add result set to list
			int i = 0;
			while (rs.next()) {
				homeRems[i] = rs.getString("home_remedy");
				i++;
			}
			return homeRems;
		} catch (Exception e){
			e.printStackTrace();
			return homeRems;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Returns ArrayList<String> of all usernames
	 * from database
	 */
	public static ArrayList<String> getAllUsernames() {
		ArrayList<String> allUsers = new ArrayList<>();
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "SELECT client_user FROM clients";
			rs = stat.executeQuery(query);
			
			// add result set to list
			while (rs.next()) {
				allUsers.add(rs.getString("client_user"));
			}
			return allUsers;
		} catch (Exception e){
			e.printStackTrace();
			return allUsers;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Returns information about current client
	 */
	public static String[] getClientInfo() {
		String[] info = new String[4];
		
		// establish database connection
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "SELECT first_name, last_name, client_user, client_pass FROM clients"
					+ " WHERE client_id = " + "'" + userID + "'";
			System.out.println(query);
			rs = stat.executeQuery(query);
			
			rs.next();
			info[0] = rs.getString("first_name");
			info[1] = rs.getString("last_name");
			info[2] = rs.getString("client_user");
			info[3] = rs.getString("client_pass");
			return info;
			
		} catch (Exception e){
			e.printStackTrace();
			return info;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	
	/*
	 * Get primary key for location
	 */
	//TODO: use join instead
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

	
	/*
	 *  Get Primary Key for Symptom Type
	 */
	//TODO: use join instead
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
	
	/*
	 * Get Primary Key for Home Remedy
	 */
	//TODO: use join instead
	public static String getHomeRemID(String hRem) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
		
		int hRemID = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT home_remedy_id FROM home_remedies " 
					+ "WHERE home_remedy = " 
					+ "'" + hRem + "'";
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			hRemID = rs.getInt("home_remedy_id");
			
			System.out.println(Integer.toString(hRemID));
			return Integer.toString(hRemID);
			
		} catch (Exception e){
			e.printStackTrace();
			return Integer.toString(hRemID);
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	// EDITING SYMPTOM LOG Methods
	// Called in EditLogsWindow
	
	/*
	 * Get location of a symptom log corresponding to
	 * the given symptom_instance
	 */
	public static String getSymLocFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		String location = null;
		Statement stat;
		ResultSet rs = null;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT symptom_instance, location "
					+ "FROM symptoms_log log "
					+ "JOIN symptom_locations locations "
					+ "	ON log.location_id = locations.location_id "
					+ "WHERE symptom_instance = " 
					+ instance;
					 
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location = rs.getString("location");
			System.out.println(location);
			SymptomTracker.setLocation(location);
			return location;
			
		} catch (Exception e){
			e.printStackTrace();
			return location;

		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Get symptom type of a symptom log corresponding to
	 * the given symptom_instance
	 */
	public static String getSymTypeFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		String symptom = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT symptom_instance, symptom "
					+ "FROM symptoms_log log "
					+ "JOIN symptom_types t "
					+ "	ON log.symptom_type_id = t.symptom_type_id "
					+ "WHERE symptom_instance = " 
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			symptom = rs.getString("symptom");
			System.out.println(symptom);
			SymptomTracker.setSymptom(symptom);

			return symptom;
			
		} catch (Exception e){
			e.printStackTrace();
			return symptom;

		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Get date of a symptom log corresponding to
	 * the given symptom_instance
	 */
	public static Date getSymDateFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		java.sql.Date date = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT date FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			date = rs.getDate("date");
			System.out.println(date);
			SymptomTracker.setDate(date);
			return date;
			
		} catch (Exception e){
			e.printStackTrace();
			return date;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Get severity of a symptom log corresponding to
	 * the given symptom_instance
	 */
	public static int getSeverityFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		int severity = -1;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT severity FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			severity = rs.getInt("severity");
			System.out.println(severity);
			SymptomTracker.setSeverity(severity);
			return severity;
			
		} catch (Exception e){
			e.printStackTrace();
			return severity;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Get duration of a symptom log corresponding to
	 * the given symptom_instance
	 */
	public static String getDurationFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		int duration = -1;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT length FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			duration = rs.getInt("length");
			System.out.println(duration);
			SymptomTracker.setLength(duration);
			return Integer.toString(duration);
			
		} catch (Exception e){
			e.printStackTrace();
			return Integer.toString(duration);
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

	/*
	 * Update symptom instance.
	 * Used when user updates a symptom log
	 * using the EditLogsWindow
	 */
	public static void updateSymInstance(int instance) {	
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "UPDATE symptoms_log "
					+ "SET "
					+ "location_id = " + getLocationID(location) + ", "
					+ "symptom_type_id = " + getSymptomTypeID() + ", "
					+ "length = " + length + ", "
					+ "date = " + "'" + date + "', "
					+ "severity = " + severity + ", "
					+ "final_score = " + score + " "
					+ "WHERE symptom_instance = " + instance;
					
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

	
	// EDITING HOME REMEDY LOG Methods
	// Called in EditHomeRemsWindow
	
	/*
	 * Get home remedy of a home remedy log corresponding to
	 * the given hr_instance
	 */
	public static String getHomeRemFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		String hr = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			
			String query = "SELECT hr_instance, home_remedy FROM home_remedies_log hrl "
					+ "JOIN home_remedies hr "
					+ "	ON hr.home_remedy_id = hrl.home_remedy_id "
					+ "WHERE hr_instance = " 
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			hr = rs.getString("home_remedy");
			System.out.println(hr);
			
			SymptomTracker.setHomeRem(hr);

			return hr;
			
		} catch (Exception e){
			e.printStackTrace();
			return hr;

		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Get location of a home remedy log corresponding to
	 * the given hr_instance
	 */
	public static String getHRLocFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		String location = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT hr_instance, location FROM home_remedies_log hrl "
					+ "JOIN symptom_locations loc "
					+ "	ON hrl.location_id = loc.location_id "
					+ "WHERE hr_instance = "
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location = rs.getString("location");
			System.out.println(location);
			
			SymptomTracker.setLocation(location);

			return location;
			
		} catch (Exception e){
			e.printStackTrace();
			return location;

		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Get date of a home remedy log corresponding to
	 * the given hr_instance
	 */
	public static Date getHRDateFromInstance(int instance) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		java.sql.Date date = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT date FROM home_remedies_log "
					+ "WHERE hr_instance = "
					+ instance;
			System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			date = rs.getDate("date");
			System.out.println(date);
			SymptomTracker.setDate(date);
			return date;
			
		} catch (Exception e){
			e.printStackTrace();
			return date;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/*
	 * Update symptom instance.
	 * Used when user updates a symptom log
	 * using the EditLogsWindow
	 */
	public static void updateHRInstance(int instance) {	
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "UPDATE home_remedies_log "
					+ "SET home_remedy_id = " + getHomeRemID(homeRem) + ", "
					+ "location_id = " + getLocationID(location) + ", "
					+ "date = " + "'" + date + "' " 
					+ "WHERE hr_instance = " + instance;
					
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
	
	
	
	// LOGGING NEW REMEDIES AND SYMPTOMS Methods
	/* 
	 * Insert user-selected data into
	 * home_remedies_log in database
	 */
	public static void runLogHomeRem() {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO home_remedies_log (client_id, home_remedy_id, location_id, date) "
					+ "VALUES ("
					+ "'" + userID + "',"
					+ "'" + getHomeRemID(homeRem) + "', "
					+ "'" + getLocationID(location) + "', "
					+ "'" + date + "')"; 
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

	/*
	 * Insert user-selected data into symptoms_log
	 */
	public static void runLogSymptom() {
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO symptoms_log (client_id, location_id, symptom_type_id, date, severity, length, final_score) "
					+ "VALUES ("
					+ "'" + userID + "', "
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
		
	
	// CLIENT METHODS
	/*
	 * Add new user to client schema
	 */
	public static void addNewUser(String first, String last, String user, String pass) {
		setFirstName(first);
		setLastName(last);
		setUsername(user);
		setPassword(pass);
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat = null;
		ResultSet rs = null;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO clients (first_name, last_name, client_user, client_pass) "
					+ "VALUES ("
					+ "'" + first + "', " 
					+ "'" + last + "', "
					+ "'" + user + "', "
					+ "'" + pass + "')"; 
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
	
	
}

