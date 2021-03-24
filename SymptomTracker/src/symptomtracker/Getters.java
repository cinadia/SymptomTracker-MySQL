package symptomtracker;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Getters {

	public static String[] getSymptomLocations() {
		final long NUMLOC; // number of rows in symptom_locations
		String[] locations = null;
		//HashMap<Integer, String> locations = new HashMap<>();
		
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
				//Object col1 = rs.getObject(0);
					// put values of column 1 and 2 from symptom_locations into HashMap
				//locations.put((Integer)col1, String.valueOf(rs.getObject(1)));
				locations[i] = rs.getString("location");
				i++;
			}
//			System.out.print("symptom locations: ");
//			for (String s : locations) 
//				System.out.println(s);
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
//			System.out.print("symptom locations: ");
//			for (String s : locations) 
//				System.out.println(s);
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
}

