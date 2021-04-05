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

	private JFrame frame;
	private JTextField changeFirst;
	private JTextField changeLast;
	private JTextField changeUser;
	private JPasswordField changePassword;

	/**
	 * Launch the application.
	 */
	public static void newProfileWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow();
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
	public ProfileWindow() {
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
		
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setBounds(10, 46, 87, 14);
		frame.getContentPane().add(firstLabel);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setBounds(10, 71, 87, 14);
		frame.getContentPane().add(lastLabel);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 96, 67, 14);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(10, 121, 67, 14);
		frame.getContentPane().add(passLabel);
		
		
		String[] info = SymptomTracker.getClientInfo();
		
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
		
		changeFirst = new JTextField();
		changeFirst.setBounds(214, 43, 96, 20);
		frame.getContentPane().add(changeFirst);
		changeFirst.setColumns(10);
		
		changeLast = new JTextField();
		changeLast.setBounds(214, 68, 96, 20);
		frame.getContentPane().add(changeLast);
		changeLast.setColumns(10);
		
		changeUser = new JTextField();
		changeUser.setBounds(214, 93, 96, 20);
		frame.getContentPane().add(changeUser);
		changeUser.setColumns(10);
		
		changePassword = new JPasswordField();
		changePassword.setBounds(214, 121, 96, 20);
		frame.getContentPane().add(changePassword);
		
		JButton saveFirst = new JButton("Save");
		saveFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeFirst.getText() != null) {
					SymptomTracker.setFirstName(changeFirst.getText());
					JOptionPane.showMessageDialog(frame, "Name saved! Hello, " + changeFirst.getText() + "!");
				}
			}
		});
		saveFirst.setBounds(320, 42, 57, 23);
		frame.getContentPane().add(saveFirst);
		
		JButton saveLast = new JButton("Save");
		saveLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeLast.getText() != null) {
					SymptomTracker.setLastName(changeLast.getText());
					JOptionPane.showMessageDialog(frame, "Last name saved!");
				}
			}
		});
		saveLast.setBounds(320, 67, 57, 23);
		frame.getContentPane().add(saveLast);
		
		JButton saveUser = new JButton("Save");
		saveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changeUser.getText() != null) {
					if (NewClientWindow.checkUser(changeUser.getText()) == false) {
						JOptionPane.showMessageDialog(frame, "Sorry! That username has been taken.");
					} else {
						SymptomTracker.setUsername(changeUser.getText());
						JOptionPane.showMessageDialog(frame, "Username saved! Cool name:)");
					}
				}
				
			}
		});
		saveUser.setBounds(320, 92, 57, 23);
		frame.getContentPane().add(saveUser);
		
		JButton savePassword = new JButton("Save");
		savePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (changePassword.getText() != null) {
					SymptomTracker.setPassword(changePassword.getText());
					JOptionPane.showMessageDialog(frame, "Password saved! Don't forget it!");
				}
			}
		});
		savePassword.setBounds(320, 144, 57, 23);
		frame.getContentPane().add(savePassword);
		
		JCheckBox showPassButt = new JCheckBox("Show Password");
		showPassButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (showPassButt.isSelected()) {
					changePassword.setEchoChar((char)0);
				} else {
					changePassword.setEchoChar('*');
				}
			}
		});
		showPassButt.setBounds(316, 117, 116, 23);
		frame.getContentPane().add(showPassButt);

	}
}
