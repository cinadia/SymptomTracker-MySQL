package symptomtracker;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

// keep getLogs() method
public class MakeTable extends JFrame {
	
	SymptomTracker st = new SymptomTracker();
	
	// Column headers for symptom log
	public String[] symColumns = {
			"Symptom Instance",
			"Location", 
			"Symptom Type", 
			"Length", 
			"Date", 
			"Severity", 
			"Score",
			"Edit"};
	
	// Column headers for home remedy log
		public static String[] hrColumns = {
				"Home Remedy Instance",
				"Home Remedy", 
				"Location", 
				"Date",
				"Edit"};
		
	// Holds data for symptom logs
	public static Object[][] symData; 
	// symptom_instance   location_id   symptom_type_id   length   date   severity   final_score
	
	// Holds data for home remedy logs
	public static Object[][] hrData;
	
	// Table model
	public static DefaultTableModel model;
	
	// The column that holds the Edit button in the symptom log
	private static int SYMEDITCOL = 7;
	
	// The column that holds the Edit button in the home remedy log
	private static int HREDITCOL = 4;

	/*
	 * Make new table
	 */
	public DefaultTableModel newTableModel(int curTable) {
		// New symptom table		
		if (curTable == 0) {
			getSymLogs();
	        model = new DefaultTableModel(symData, symColumns) {
	        	@Override
	            public boolean isCellEditable(int row, int column) {
	        		// Only Edit button column is editable
	                return column == SYMEDITCOL;
	            }
	        };
		}
		// New home remedy table
		if (curTable == 1) {
			getHomeRemLogs();
			model = new DefaultTableModel(hrData, hrColumns) {
	        	@Override
	            public boolean isCellEditable(int row, int column) {
	        		// Only Edit button column is editable
	                return column == HREDITCOL;
	            }
	        };
		}
        
        return model;
	}
	
	
	/*
	 * Gets symptom log data from symptoms_log db. 
	 * Puts data into symData[][].
	 */
	public void getSymLogs() {
		
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
				
		try {
			stat = raC.getConn().createStatement();
			
			// get number of logs (rows)
			String numQuery = "SELECT COUNT(*) AS count FROM symptoms_log "
					+ "WHERE client_id = " + "'" + st.userID + "'";
			System.out.println(numQuery);
			
			rs = stat.executeQuery(numQuery);
			rs.next();
			symData = new Object[rs.getInt("count")][7];
			
			String joinQuery = "SELECT "
					+ "symptom_instance, "
					+ "location, "
					+ "symptom, "
					+ " length,"
					+ " date,"
					+ " severity,"
					+ " final_score "
					+ "FROM symptoms_log log "
					+ "JOIN symptom_locations locations "
					+ "ON log.location_id = locations.location_id "
					+ "JOIN symptom_types types "
					+ "ON log.symptom_type_id = types.symptom_type_id "
					+ "WHERE  client_id = " + "'" + st.userID + "'";
			System.out.println(joinQuery);
			System.out.println("for user " + st.userID);
			rs = stat.executeQuery(joinQuery);
			
			int index = 0;
			while (rs.next()) {
				symData[index] = new Object[] {
						rs.getInt("symptom_instance"),
						rs.getString("location"), 
						rs.getString("symptom"),
						rs.getInt("length"),
						rs.getDate("date"),
						rs.getInt("severity"),
						rs.getInt("final_score")};
				index++;
			}
			
			for (int i = 0; i < symData.length; i++) {
				for (int j = 0; j < symData[0].length; j++) {
					System.out.print(symData[i][j] + " ");
				}
				System.out.println();
			}
			
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
	 * Gets symptom log value
	 */
	public Object getValueAt(int row, int col, int curTable) {
		// Symptom data table selected
		if (curTable == 0) {
			System.out.println("value from table is " + symData[row][col]);
			return symData[row][col];
		}
		else if (curTable == 1) {
			System.out.println("value from table is " + hrData[row][col]);
			return hrData[row][col];
		}
		else {
			return null;
		}
	}

	

	/*
	 * Gets home remedy data from home_remedies_log db. 
	 * Puts data into hrData[][].
	 */
	public void getHomeRemLogs() {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
				
		try {
			stat = raC.getConn().createStatement();
			
			// get number of logs (rows)
			String numQuery = "SELECT COUNT(*) AS count FROM home_remedies_log "
					+ "WHERE client_id = " + "'" + st.userID + "'";
			System.out.println(numQuery);
			
			rs = stat.executeQuery(numQuery);
			rs.next();
			hrData = new Object[rs.getInt("count")][4];
			
			String joinQuery = 	"SELECT "
					+ "hr_instance, "
					+ "location, "
					+ "date, "
					+ "home_remedy "
					+ "FROM home_remedies_log log "
					+ "JOIN home_remedies rems "
					+ "	ON rems.home_remedy_id = log.home_remedy_id "
					+ "JOIN symptom_locations locations "
					+ "	ON log.location_id = locations.location_id "
					+ "WHERE client_id = " + st.userID;	
		
			System.out.println(joinQuery);
			System.out.println("for user " + st.userID);
			rs = stat.executeQuery(joinQuery);
			
			int index = 0;
			while (rs.next()) {
				hrData[index] = new Object[] {
						rs.getInt("hr_instance"),
						rs.getString("home_remedy"),
						rs.getString("location"), 
						rs.getDate("date"),
						};
				index++;
			}
			
			System.out.println("hr log data");
			for (int i = 0; i < hrData.length; i++) {
				for (int j = 0; j < hrData[0].length; j++) {
					System.out.print(hrData[i][j] + " ");
				}
				System.out.println();
			}
			
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
