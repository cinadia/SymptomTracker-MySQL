package symptomtracker;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			LoginWindow login = new LoginWindow();
			login.newLoginWindow();
//			HomePage home = new HomePage();
//			home.newHomePage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
