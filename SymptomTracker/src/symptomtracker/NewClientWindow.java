package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class NewClientWindow {

	private JFrame frame;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void newNewClientWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewClientWindow window = new NewClientWindow();
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
	public NewClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hi, there! We're so happy to meet you. Please enter the following information.");
		lblNewLabel.setBounds(27, 11, 383, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(27, 65, 106, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(27, 99, 106, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(27, 130, 106, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(27, 161, 106, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
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
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (firstNameField != null 
						&& lastNameField != null 
						&& userField != null 
						&& passwordField != null) {
					if (checkUser(userField.getText()) == false) {
						JOptionPane.showMessageDialog(frame, "Sorry! That username has been taken.");
					} else {
						SymptomTracker.addNewUser(firstNameField.getText(), 
								lastNameField.getText(), 
								userField.getText(), 
								passwordField.getText());
						JOptionPane.showMessageDialog(frame, "Success! Start to your journey towards better pain management.");
						HomePage.newHomePage();
						frame.dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(143, 189, 141, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public static boolean checkUser(String attemptedUser) {
		if (SymptomTracker.getAllUsernames().contains(attemptedUser)) {
			return false;
		} else {
			return true;
		}
	}
}
