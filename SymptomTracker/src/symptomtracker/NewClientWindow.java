package symptomtracker;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewClientWindow {

	private JFrame frame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userField;
	private JTextField passwordField;
	
	SymptomTracker st = new SymptomTracker();

	/**
	 * Create the NewClientWindow application, where
	 * new users create a new account.
	 */
	public NewClientWindow() {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Labels
		JLabel lblGreeting = new JLabel("Hi, there! We're so happy to meet you. Please enter the following information.");
		lblGreeting.setBounds(27, 11, 383, 43);
		frame.getContentPane().add(lblGreeting);
		
		JLabel fnamelabel = new JLabel("First Name");
		fnamelabel.setBounds(27, 65, 106, 14);
		frame.getContentPane().add(fnamelabel);
		
		JLabel lnamelabel = new JLabel("Last Name");
		lnamelabel.setBounds(27, 99, 106, 14);
		frame.getContentPane().add(lnamelabel);
		
		JLabel userlabel = new JLabel("Username");
		userlabel.setBounds(27, 130, 106, 14);
		frame.getContentPane().add(userlabel);
		
		JLabel passlabel = new JLabel("Password");
		passlabel.setBounds(27, 161, 106, 14);
		frame.getContentPane().add(passlabel);
		
		// Text fields
		firstNameField = new JTextField();
		firstNameField.setBounds(143, 62, 174, 20);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(143, 96, 174, 20);
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);
		
		userField = new JTextField();
		userField.setBounds(143, 127, 174, 20);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(143, 158, 174, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		
		// Finalization button to make new account
		JButton btnCreateAcc = new JButton("Create Account");
		btnCreateAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// All fields must be filled to make new account
				if (firstNameField != null 
						&& lastNameField != null 
						&& userField != null 
						&& passwordField != null) {
					// Check if username has been taken
					if (checkUser(userField.getText()) == false) {
						// Taken
						JOptionPane.showMessageDialog(frame, "Sorry! That username has been taken.");
					} else {
						// Not taken - add new user to database
						st.addNewUser(firstNameField.getText(), 
								lastNameField.getText(), 
								userField.getText(), 
								passwordField.getText());								
						JOptionPane.showMessageDialog(frame, "Success! Start to your journey towards better pain management.");
						HomePage hp = new HomePage();
						frame.dispose();
					}
				}
			}
		});
		btnCreateAcc.setBounds(143, 189, 141, 23);
		frame.getContentPane().add(btnCreateAcc);
		frame.setVisible(true);
	}
	
	/**
	 * Checks if the given username has been taken 
	 * by another client
	 * @param attemptedUser the username the client want to use
	 * @return true if attemptedUser has not been used, false otherwise
	 */
	public boolean checkUser(String attemptedUser) {
		if (st.getAllUsernames().contains(attemptedUser)) {
			return false;
		} else {
			return true;
		}
	}
}
