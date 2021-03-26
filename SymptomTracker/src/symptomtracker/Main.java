package symptomtracker;


public class Main {
	
	public static void main(String[] args) {
		LoginWindow.newLoginWindow();
		//HomePage.newHomePage();
		//MakeStats.newMakeStatsWindow();
		//Stats.getTodaysLocations();
		//LoginWindow.newLoginWindow();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			LoginWindow login = new LoginWindow();
//			login.newLoginWindow();
//			HomePage home = new HomePage();
//			home.newHomePage();
			
			//Stats.getWeeklyData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
