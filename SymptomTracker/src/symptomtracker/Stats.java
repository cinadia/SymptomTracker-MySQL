package symptomtracker;

import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.JFreeChart;

public class Stats {

	/*
	  String P1 = "Player 1";
        String P2 = "Player 2";     
        String Attk = "Attack";
        String Def = "Defense";
        String Speed = "Speed";
        String Stealth = "Stealth";
         
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  
         
        // Player 1
        dataset.addValue(10, P1, Attk);
        dataset.addValue(7, P1, Def);
        dataset.addValue(6, P1, Speed);
        dataset.addValue(6, P1, Stealth);
         
        // Player 2
        dataset.addValue(7, P2, Attk);
        dataset.addValue(9, P2, Def);
        dataset.addValue(8, P2, Speed);
        dataset.addValue(8, P2, Stealth);
         
        JFreeChart barChart = ChartFactory.createBarChart("JFreeChart BarChart", "Players", "Points",
                                                           dataset, PlotOrientation.VERTICAL, true, true, false); 
         
        ChartUtils.saveChartAsPNG(new File("C://Users/CodersLegacy/Desktop/histogram.png"), barChart, 650, 400);
    }
	 */
	
	// Helper method to make the chart
	// TODO: add parameters of method. return JFreeChart
	public static void makeWeeklyChart() {
		String sc = "Score";
		String hr = "Hour";
		// TODO: strings for each hour of day?
		
	}
	public static void getWeeklyData() {
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
			//NUMLOC = rs.getLong("totalCount");
			//locations = new String[(int)NUMLOC];
	        
			
			// execute query
			String query = "SELECT location FROM symptom_locations";
			rs = stat.executeQuery(query);
			
			// process result set
			int i = 0;
			while (rs.next()) {
				//Object col1 = rs.getObject(0);
					// put values of column 1 and 2 from symptom_locations into HashMap
				//locations.put((Integer)col1, String.valueOf(rs.getObject(1)));
				//locations[i] = rs.getString("location");
				i++;
			}
			//return locations;
		} catch (Exception e){
			e.printStackTrace();
			//return locations;
		} finally {
			try {
				// close db connection
				raC.getConn().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
