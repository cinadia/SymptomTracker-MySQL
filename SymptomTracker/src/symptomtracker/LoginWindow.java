package symptomtracker;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow {
	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Create the LoginWindow application, where
	 * all users sign in.
	 */
	public LoginWindow() {
		/* EventQueue.invokeLater is used if the code
		 *  being displayed on the GUI is in a 
		 *  separate thread/path.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		// Labels
		JLabel greetingLabel = new JLabel("Welcome back! Please enter your username and password.");
		greetingLabel.setBounds(74, 41, 337, 14);
		frame.getContentPane().add(greetingLabel);
		
		JLabel userLabel = new JLabel("username");
		userLabel.setBounds(98, 95, 73, 14);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("password");
		passLabel.setBounds(98, 139, 60, 14);
		frame.getContentPane().add(passLabel);
		
		JLabel noAccLabel = new JLabel("Don't have an account?");
		noAccLabel.setBounds(10, 232, 142, 20);
		frame.getContentPane().add(noAccLabel);
		
		
		// Text fields
		userField = new JTextField();
		userField.setBounds(181, 92, 96, 20);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 136, 96, 20);
		/* If ENTER button is pressed on keyboard, 
		 * check password validity.
		 */
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Username and password must both be provided
				if (userField != null && passwordField != null) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						login(userField.getText(), passwordField.getText());
					}
				}
			}
		});
		frame.getContentPane().add(passwordField);
		
		
		// Checkbox to toggle showing password
		JCheckBox showPassBox = new JCheckBox("show password");
		showPassBox.setBounds(283, 135, 128, 23);
		showPassBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Show chars
				if (showPassBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				}
				// Hide chars
				else {
					passwordField.setEchoChar('*');
				}
			}
		});
		frame.getContentPane().add(showPassBox);
		 
		
		// Login button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(188, 190, 89, 23);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login(userField.getText(), passwordField.getText());
			}
		});
		frame.getContentPane().add(loginButton);
		
		
		// New account button
		JButton btnNewButton = new JButton("Create new account");
		btnNewButton.setBounds(158, 231, 188, 23);
		// Opens new window to create new account
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewClientWindow ncw = new NewClientWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
		
	}
	
	/**
	 * Checks the validity of the given password against the
	 * user's set password in the database.
	 * 
	 * @param user the client's username
	 * @param attemptPass the password provided - it may be incorrect
	 * @return true if password provided is correct
	 */
	private boolean checkPass(String user, String attemptPass) {
		ConnectDB raC = new ConnectDB();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "SELECT client_pass, client_id FROM clients " 
					+ "WHERE client_user = "
					+ "'" + user + "'";
			//System.out.println(query);
	        
			rs = stat.executeQuery(query);
			
			rs.next();
		    String actualPass = rs.getString("client_pass");
			if (actualPass.equals(attemptPass)) {
				// Set the user ID of the current client logging in
				SymptomTracker st = new SymptomTracker();
				st.setUserID(rs.getInt("client_id"));
				//System.out.println("user id: " + st.getUserID());
				return true;
			} // Incorrect password 
			else {
				return false;
			}
		} catch (Exception e){
			e.printStackTrace();
			return false;
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
	 * Logs user into application or gives dialog 
	 * if username or password is incorrect or 
	 * not provided.
	 * 
	 * @param user the user's provided username
	 * @param pass the user's provided password
	 */
	private void login(String user, String pass) {
		if (user != null && pass != null) {
			//System.out.println(user + pass);
			if (checkPass(user, pass) == false) {
				JOptionPane.showMessageDialog(frame, "Incorrect username or password.");
			} else {
				HomePage hp = new HomePage();
				frame.dispose();
				
			}
		}
	}
}
