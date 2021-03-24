package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	public static void newHomePage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TODO: need set layout to null initially??
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setBounds(10, 11, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Log Symptom");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymWindow lsw = new LogSymWindow();
				lsw.newLogSymWindow();
			}
		});
		btnNewButton.setBounds(10, 36, 162, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Log Home Remedy");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogHomeRemedy hr = new LogHomeRemedy();
			}
		});
		btnNewButton_1.setBounds(10, 70, 162, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log Professional Remedy");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogProfRemedy pr = new LogProfRemedy();
			}
		});
		btnNewButton_2.setBounds(10, 104, 162, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Profile");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client ls = new Client();
			}
		});
		btnNewButton_3.setBounds(10, 137, 162, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Edit Logs");
		btnNewButton_4.setBounds(10, 169, 162, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		panel.setBounds(201, 52, 256, 75);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(201, 161, 256, 64);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(201, 260, 256, 70);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Today");
		lblNewLabel_1.setBounds(203, 36, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("This Week");
		lblNewLabel_2.setBounds(201, 141, 96, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(200, 359, 257, 70);
		frame.getContentPane().add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("This Month");
		lblNewLabel_3.setBounds(201, 235, 96, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("This Year");
		lblNewLabel_4.setBounds(201, 341, 48, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("GREETING");
		lblNewLabel_5.setBounds(304, 11, 101, 14);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
