package symptomtracker;


public class Main {
	
	public static void main(String[] args) {
		// New login window
		LoginWindow lw = new LoginWindow();
		
		// Register database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
