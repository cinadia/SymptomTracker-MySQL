package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class ProfileWindow {
	// TODO: profile labels should update when 'save' is pressed
	NewClientWindow ncw = new NewClientWindow();
	SymptomTracker st = new SymptomTracker();
	
	private JFrame frame;
	private JTextField changeFirst;
	private JTextField changeLast;
	private JTextField changeUser;
	private JPasswordField changePassword;
	private String[] info = st.getClientInfo(); // holds all client information

	/**
	 * Launch the ProfileWindow application, where
	 * users can view and edit their information.
	 */
//	public void newProfileWindow() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProfileWindow window = new ProfileWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ProfileWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();

//					ProfileWindow window = new ProfileWindow();
//					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 548, 213);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Labels
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setBounds(10, 46, 123, 14);
		frame.getContentPane().add(firstLabel);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setBounds(10, 71, 123, 14);
		frame.getContentPane().add(lastLabel);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 96, 123, 14);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(10, 121, 123, 14);
		frame.getContentPane().add(passLabel);
		
		JLabel showFirst = new JLabel(info[0]);
		showFirst.setBounds(88, 46, 116, 14);
		frame.getContentPane().add(showFirst);
		
		JLabel showLast = new JLabel(info[1]);
		showLast.setBounds(88, 71, 116, 14);
		frame.getContentPane().add(showLast);
		
		JLabel showUser = new JLabel(info[2]);
		showUser.setBounds(87, 96, 117, 14);
		frame.getContentPane().add(showUser);
		
		JLabel showPass = new JLabel(info[3]);
		showPass.setBounds(88, 121, 116, 14);
		frame.getContentPane().add(showPass);
		
		JLabel editLabel = new JLabel("Edit");
		editLabel.setBounds(212, 25, 48, 14);
		frame.getContentPane().add(editLabel);
		
		// Text fields
		changeFirst = new JTextField();
		changeFirst.setBounds(214, 43, 163, 20);
		frame.getContentPane().add(changeFirst);
		changeFirst.setColumns(10);
		
		changeLast = new JTextField();
		changeLast.setBounds(214, 68, 163, 20);
		frame.getContentPane().add(changeLast);
		changeLast.setColumns(10);
		
		changeUser = new JTextField();
		changeUser.setBounds(214, 93, 163, 20);
		frame.getContentPane().add(changeUser);
		changeUser.setColumns(10);
		
		changePassword = new JPasswordField();
		changePassword.setBounds(214, 121, 163, 20);
		frame.getContentPane().add(changePassword);
		
		
		// Buttons
		
		// Save first name 
		JButton saveFirst = new JButton("Save");
		saveFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeFirst.getText() != null) {
					String text = changeFirst.getText();
					st.setFirstName(text);
					showFirst.setText(text);
					JOptionPane.showMessageDialog(frame, "Name saved! Hello, " + changeFirst.getText() + "!");
				}
			}
		});
		saveFirst.setBounds(425, 42, 85, 23);
		frame.getContentPane().add(saveFirst);
		
		// Save last name
		JButton saveLast = new JButton("Save");
		saveLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeLast.getText() != null) {
					String text = changeLast.getText();
					showLast.setText(text);
					st.setLastName(text);
					JOptionPane.showMessageDialog(frame, "Last name saved!");
				}
			}
		});
		saveLast.setBounds(425, 67, 85, 23);
		frame.getContentPane().add(saveLast);
		
		// Save new username
		JButton saveUser = new JButton("Save");
		saveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeUser.getText() != null) {
					// New username must be unique
					if (ncw.checkUser(changeUser.getText()) == false) {
						// Attempted new username is taken
						JOptionPane.showMessageDialog(frame, "Sorry! That username has been taken.");
					} else {
						String text = changeUser.getText();
						st.setUsername(text);
						showUser.setText(text);
						JOptionPane.showMessageDialog(frame, "Username saved! Cool name:)");
					}
				}
				
			}
		});
		saveUser.setBounds(425, 92, 85, 23);
		frame.getContentPane().add(saveUser);
		
		// Save new password
		JButton savePassword = new JButton("Save");
		savePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changePassword.getText() != null) {
					String text = changePassword.getText();
					st.setPassword(text);
					showPass.setText(text);
					JOptionPane.showMessageDialog(frame, "Password saved! Don't forget it!");
				}
			}
		});
		savePassword.setBounds(425, 144, 85, 23);
		frame.getContentPane().add(savePassword);
		
		// Show current password
		JCheckBox showPassButt = new JCheckBox("Show Password");
		showPassButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (showPassButt.isSelected()) {
					// Show
					changePassword.setEchoChar((char)0);
				} else {
					// Hide
					changePassword.setEchoChar('*');
				}
			}
		});
		showPassButt.setBounds(383, 117, 147, 23);
		frame.getContentPane().add(showPassButt);
		frame.setVisible(true);

	}
}
