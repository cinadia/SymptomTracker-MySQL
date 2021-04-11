package symptomtracker;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Stats {

	SymptomTracker st = new SymptomTracker();
	
	/** 
	 * Returns a distinct HashMap of all locations 
	 * that user has logged today and their 
	 * corresponding scores
	 * @return HashMap<String, Integer> of locations to scores
	 */
	public HashMap<String, Integer> getTodaysLocations() {
		HashMap<String, Integer> locScore = new HashMap<>();
				
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
				
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT SUM(final_score), date, location FROM symptoms_log log "
					+ "JOIN symptom_locations loc ON loc.location_id = log.location_id "
					+ "WHERE client_id = " + st.getUserID()
					+ " AND date = CURDATE() "
					+ "GROUP BY location";
					
			rs = stat.executeQuery(query);
			
			while (rs.next()) {
				locScore.put(rs.getString("location"), rs.getInt("SUM(final_score)"));
			}
			System.out.print("location and scores for today");
			System.out.println(locScore);
			
			return locScore;
			
		} catch (Exception e){
			e.printStackTrace();
			return locScore;
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
	 * Get scores of specific location from the 
	 * given start date to end date. 
	 * @param start start date provided
	 * @param end end date provided
	 * @param location_id the location whose scores are to be retrieved
	 * @return HashMap<java.sql.Date, Integer> of date to score
	 */
	public HashMap<java.sql.Date, Integer> getScoresDates(java.sql.Date start, java.sql.Date end, String location_id) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
		
		HashMap<java.sql.Date, Integer> dateAndScore = new HashMap<>();
		
		try {
			stat = raC.getConn().createStatement();
			
			String query = "SELECT SUM(final_score), date FROM symptoms_log "
					+ "WHERE client_id = '" + st.getUserID() 
					+ "' AND location_id = '" + st.getLocationID(location_id) + "'"
					+ " GROUP BY date HAVING date BETWEEN "
					+ "'" + start + "'" + " AND " + "'" + end + "'";
			System.out.println(query);
		
			rs = stat.executeQuery(query);
			
			while (rs.next()) {
				dateAndScore.put(rs.getDate("date"), rs.getInt("SUM(final_score)"));
			}
			System.out.print("dates and scores");
			System.out.println(dateAndScore);
			
			return dateAndScore;
			
		} catch (Exception e){
			e.printStackTrace();
			return dateAndScore;
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
