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

	private static JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;
	public static int userID;

	/**
	 * Launch the application.
	 */
	public static void newLoginWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel greetingLabel = new JLabel("Welcome back! Please enter your username and password.");
		greetingLabel.setBounds(74, 41, 337, 14);
		frame.getContentPane().add(greetingLabel);
		
		JLabel userLabel = new JLabel("username");
		userLabel.setBounds(98, 95, 73, 14);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("password");
		passLabel.setBounds(98, 139, 60, 14);
		frame.getContentPane().add(passLabel);
		
		userField = new JTextField();
		userField.setBounds(181, 92, 96, 20);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (userField != null && passwordField != null) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (!checkPass(userField.getText(), passwordField.getText())) {
							JOptionPane.showMessageDialog(frame, "Incorrect password.");
						} else {
							HomePage home = new HomePage();
							home.newHomePage();
							frame.dispose();
						}
					}
				}
				
			}
		});
		passwordField.setBounds(181, 136, 96, 20);
		frame.getContentPane().add(passwordField);
		
		JCheckBox showPassBox = new JCheckBox("show password");
		showPassBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (showPassBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		showPassBox.setBounds(283, 135, 128, 23);
		frame.getContentPane().add(showPassBox);
		 
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (userField != null && passwordField != null) {
					System.out.println(userField.getText() + passwordField.getText());
					if (checkPass(userField.getText(), passwordField.getText()) == false) {
						JOptionPane.showMessageDialog(frame, "Incorrect username or password.");
					} else {
						HomePage home = new HomePage();
						home.newHomePage();
						frame.dispose();
						
					}
				}
			}
		});
		loginButton.setBounds(188, 190, 89, 23);
		frame.getContentPane().add(loginButton);
		
		JButton btnNewButton = new JButton("Create new account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewClientWindow.newNewClientWindow();
			}
		});
		btnNewButton.setBounds(163, 231, 145, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Don't have an account?");
		lblNewLabel.setBounds(23, 235, 148, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public static boolean checkPass(String user, String attemptPass) {
		RegAndConn raC = new RegAndConn();
		raC.connectDB();
		
		Statement stat;
		ResultSet rs;
	
		try {
			stat = raC.getConn().createStatement();
			
			// execute query
			String query = "SELECT client_pass, client_id FROM clients " 
					+ "WHERE client_user = "
					+ "'" + user + "'";
			System.out.println(query);
	        
			rs = stat.executeQuery(query);
			
			rs.next();
		    String actualPass = rs.getString("client_pass");
			if (actualPass.equals(attemptPass)) {
				userID = rs.getInt("client_id");
				System.out.println(userID);
				return true;
			} else {
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
}
