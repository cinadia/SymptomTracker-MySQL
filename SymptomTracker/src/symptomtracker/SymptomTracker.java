package symptomtracker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;

public class SymptomTracker {
	
	 int client = 1;
	 String location;
	 String symptom;
	 java.sql.Date date;
	 int severity = -1; // Default non-selected value
	 int length = -1; //   "
	 int score;
	 String homeRem;
	
	static int userID;
	
	/**
	 * Checks if the given text is valid.
	 * Only accepted values are positive integers.
	 * @param text the text to be tested
	 * @return true if text is a positive integer
	 */
	public boolean isValid(String text) {
	   try {
	      Integer.parseInt(text);
	      if (Integer.parseInt(text) <= 0)
	    	  return false;
	      return true;
	   } catch (NumberFormatException e) {
	      return false;
	   }
	}
	
	/**
	 * Sets the text of a label to the 
	 * final calculated score. Final score is 
	 * calculated by severity multiplied by length.
	 * @param severity the provided severity
	 * @param lblCalculatedScore the label to be updated
	 */
	public void setCalculatedScore(int severity, JLabel lblCalculatedScore) {
		setSeverity(severity);
		int length = getLength();
		if (length != -1) {
			int score = severity*length;
			lblCalculatedScore.setText(Integer.toString(score));
			setScore(score);
			//System.out.println("Setting score as " + score);
		}
	}
	
	// SETTERS
	/**
	 * Set location
	 * @param l new location
	 */
	public void setLocation(String l) {
		location = l;
		//System.out.println(location);
	}
	
	/** 
	 * Set symptom
	 * @param s new symptom
	 */
	public void setSymptom(String s) {
		symptom = s;
		//System.out.println(symptom);
	}
	
	/**
	 * Set date
	 * @param d new date
	 */
	public void setDate(java.sql.Date d) {
		date = d;
		//System.out.println(date);
	}
	
	/** Set severity
	 * @param s new severity
	 */
	public void setSeverity(int s) {
		severity = s;
		//System.out.println(severity);
	}
	
	/**
	 * Set length
	 * @param l new length
	 */
	public void setLength(int l) {
		length = l;
		//System.out.println(length);
	}
	
	/** 
	 * Set score
	 * @param s new score
	 */
	public void setScore(int s) {
		score = s;
		//System.out.println(score);
	}
	
	/** 
	 * Set home remedy
	 * @param hr new home remedy
	 */
	public void setHomeRem(String hr) {
		homeRem = hr;
		//System.out.println(homeRem);
	}
	
	/**
	 * Set user ID
	 * @param id current user ID
	 */
	public void setUserID(int id) {
		userID = id;
		//System.out.println(userID);
	}
	
	/**
	 * Set user's first name in database
	 * @param first first name
	 */
	public void setFirstName(String first) {
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET first_name = " + "'"+ first + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			//System.out.println(query);
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
	
	/**
	 * Set user's last name in database
	 * @param last last name
	 */
	public void setLastName(String last) {
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET last_name = " + "'"+ last + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			//System.out.println(query);
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
	
	/** 
	 * Set user's username in database
	 * @param un user's username
	 */
	public void setUsername(String un) {
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET client_user = " + "'"+ un + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			//System.out.println(query);
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
	
	/**
	 * Set user's password in the database
	 * @param pass new password
	 */
	public void setPassword(String pass) {
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "UPDATE clients SET client_pass = " + "'"+ pass + "'"
					+ " WHERE client_id = " + "'" + userID + "'";
			//System.out.println(query);
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
	/**
	 * @return severity
	 */
	public int getSeverity() {
		return severity;
	}
	
	/**
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @return user ID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * 
	 * @return symptom
	 */
	public String getSymptom() {
		return symptom;
	}
	
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns symptom locations from database
	 * @return String[] of symptom locations
	 */
	public String[] getSymptomLocations() {
		final long NUMLOC; // number of rows in symptom_locations
		String[] locations = null;
		
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
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
	
	
	/**
	 * Returns symptoms from database
	 * @return String[] of all symptoms 
	 */
	public String[] getSymptoms() {
		final long NUMSYMS; // number of rows in symptom_locations
		String[] symptoms = null;
		
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
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
	
	/**
	 * Return home remedies from database
	 * @return String[] of home remedies
	 */
	public String[] getHomeRems() {
		final long NUM; // number of rows in symptom_locations
		String[] homeRems = null;
		
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
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
	
	/**
	 * Return all usernames from database
	 * @return ArrayList<String> of usernames
	 */
	public ArrayList<String> getAllUsernames() {
		ArrayList<String> allUsers = new ArrayList<>();
		
		ConnectDB raC = new ConnectDB();
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
	
	/**
	 * Returns all information about current client
	 * @return String[] of client information
	 */
	public String[] getClientInfo() {
		String[] info = new String[4];
		
		// establish database connection
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "SELECT first_name, last_name, client_user, client_pass FROM clients"
					+ " WHERE client_id = " + "'" + userID + "'";
			//System.out.println(query);
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
	

	//TODO: use join instead?
	/**
	 * Get primary key for location 
	 * @param loc the location whose primary key is returned
	 * @return PK of the location
	 */
	public String getLocationID(String loc) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		int location_id = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT location_id FROM symptom_locations " 
					+ "WHERE location = " 
					+ "'" + loc + "'";
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location_id = rs.getInt("location_id");
			
			//System.out.println(Integer.toString(location_id));
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

	//TODO: use join instead?
	/**
	 * Get primary key for symptom type 
	 * @return PK of the symptom type
	 */
	public String getSymptomTypeID() {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		int symptom_type_id = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT symptom_type_id FROM symptom_types " 
					+ "WHERE symptom = " 
					+ "'" + getSymptom() + "'";
			
			//System.out.println(query);
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			symptom_type_id = rs.getInt("symptom_type_id");
			//System.out.println(Integer.toString(symptom_type_id));
			
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
	/**
	 * Get primary key for Home Remedy 
	 * @param hRem the remedy whose primary key is returned
	 * @return PK of the remedy
	 */
	public String getHomeRemID(String hRem) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		int hRemID = -1;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT home_remedy_id FROM home_remedies " 
					+ "WHERE home_remedy = " 
					+ "'" + hRem + "'";
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			hRemID = rs.getInt("home_remedy_id");
			
			//System.out.println(Integer.toString(hRemID));
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
	
	/**
	 *  Get location of a symptom log corresponding to
	 * the given symptom_instance
	 * @param instance the current symptom instance
	 * @return String of the symptom instances's location
	 */
	public String getSymLocFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		String location = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT symptom_instance, location "
					+ "FROM symptoms_log log "
					+ "JOIN symptom_locations locations "
					+ "	ON log.location_id = locations.location_id "
					+ "WHERE symptom_instance = " 
					+ instance;
					 
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location = rs.getString("location");
			//System.out.println(location);
			setLocation(location);
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
	 * 
	 */
	/**
	 * Get symptom type of a symptom log corresponding to
	 * the given symptom_instance
	 * @param instance the current symptom instance
	 * @return String of the symptom type of the symptom instance
	 */
	public String getSymTypeFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
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
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			symptom = rs.getString("symptom");
			//System.out.println(symptom);
			setSymptom(symptom);

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
	 *
	 */
	/**
	 *  Get date of a symptom log corresponding to
	 * the given symptom_instance
	 * @param instance the current symptom instance
	 * @return Date of the symptom instance
	 */
	public Date getSymDateFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		java.sql.Date date = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT date FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			date = rs.getDate("date");
			//System.out.println(date);
			setDate(date);
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
	
	/**
	 *  Get severity of a symptom log corresponding to
	 * the given symptom_instance
	 * @param instance the current symptom instance
	 * @return int of the severity of the symptom instance
	 */
	public int getSeverityFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		int severity = -1;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT severity FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			severity = rs.getInt("severity");
			//System.out.println(severity);
			setSeverity(severity);
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
	 * 
	 */
	/**
	 * Get duration of a symptom log corresponding to
	 * the given symptom_instance
	 * @param instance the current symptom instance
	 * @return String of the duration of the symptom instance
	 * @return
	 */
	public String getDurationFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		int duration = -1;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT length FROM symptoms_log "
					+ "WHERE symptom_instance = "
					+ instance;
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			duration = rs.getInt("length");
			//System.out.println(duration);
			setLength(duration);
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

	/**
	 * Update symptom instance, used when user updates a 
	 * symptom log using the EditLogsWindow
	 * @param instance instance of the symptom being edited
	 */
	public void updateSymInstance(int instance) {	
		ConnectDB raC = new ConnectDB();
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
					
			//System.out.println(query);
			
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
	
	/**
	 * Get home remedy of a home remedy log corresponding to
	 * the given hr_instance
	 * @param instance the home remedy instance
	 * @return String of the home remedy of the current home remedy instance
	 */
	public String getHomeRemFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
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
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			hr = rs.getString("home_remedy");
			//System.out.println(hr);
			
			setHomeRem(hr);

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
	
	/**
	 * Get location of a home remedy log corresponding to
	 * the given hr_instance
	 * @param instance the current home remedy instance
	 * @return String of the location of the home remedy instance
	 */
	public String getHRLocFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
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
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			location = rs.getString("location");
			//System.out.println(location);
			
			setLocation(location);

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

	/**
	 * Get date of a home remedy log corresponding to
	 * the given hr_instance
	 * @param instance home remedy instance (PK)
	 * @return the date of the logged home remedy
	 */
	public Date getHRDateFromInstance(int instance) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		java.sql.Date date = null;
		Statement stat;
		ResultSet rs;
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT date FROM home_remedies_log "
					+ "WHERE hr_instance = "
					+ instance;
			//System.out.println(query);
			
			// execute query
			rs = stat.executeQuery(query);
			rs.next();
			date = rs.getDate("date");
			//System.out.println(date);
			setDate(date);
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
	
	/**
	 * Update home remedy instance, used when user updates a 
	 * home remedy log using the EditHomeRemsWindow
	 * @param instance instance of the home remedy being edited
	 */
	public void updateHRInstance(int instance) {	
		ConnectDB raC = new ConnectDB();
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
					
			//System.out.println(query);
			
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
	
	
	/**
	 * Insert user-selected data into
	 * home_remedies_log in database
	 */
	public void runLogHomeRem() {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO home_remedies_log (client_id, home_remedy_id, location_id, date) "
					+ "VALUES ("
					+ "'" + userID + "',"
					+ "'" + getHomeRemID(homeRem) + "', "
					+ "'" + getLocationID(location) + "', "
					+ "'" + date + "')"; 
			//System.out.println(query);
			
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

	/**
	 * Insert user-selected data into symptoms_log
	 */
	public void runLogSymptom() {
		
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
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
			//System.out.println(query);
			
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
		
	/**
	 * Add new user to client schema
	 * @param first client's first name 
	 * @param last client's last name
	 * @param user client's username
	 * @param pass client's password
	 */
	public void addNewUser(String first, String last, String user, String pass) {
		setFirstName(first);
		setLastName(last);
		setUsername(user);
		setPassword(pass);
		
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			String query = "INSERT INTO clients (first_name, last_name, client_user, client_pass) "
					+ "VALUES ("
					+ "'" + first + "', " 
					+ "'" + last + "', "
					+ "'" + user + "', "
					+ "'" + pass + "')"; 
			//System.out.println(query);
			
			// execute query
			stat.executeUpdate(query);
			
			String getUser = "SELECT client_id FROM clients WHERE client_user = "
					+ "'" + user + "'";
			//System.out.println(getUser);
			rs = stat.executeQuery(getUser);
			rs.next();
			setUserID(rs.getInt("client_id"));
			//System.out.println("setting current client id for new user to " + getUserID());
			
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

