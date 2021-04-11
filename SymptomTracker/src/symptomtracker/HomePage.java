package symptomtracker;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class HomePage {

	private JFrame frame;
	
	// Tracks if user is currently editing/viewing Symptoms or Home Remedies.
	// TODO: When making Professional Remedies, must change to an integer
	private boolean editSymptoms; 
	PieChart pc = new PieChart();


	Stats s = new Stats();

	
	/**
	 * Launch the HomePage application, where
	 * client can further select different features.
	 */
	public void newHomePage() {
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
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 517, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Greeting label changes based on time of day
		JLabel lblGreeting = new JLabel("GREETING");
		lblGreeting.setFont(new Font("MV Boli", Font.PLAIN, 15));
		lblGreeting.setBounds(166, 11, 173, 14);
		if (LocalTime.now().isBefore(LocalTime.parse("12:00"))) {
			lblGreeting.setText("Good Morning!");
		} else if (LocalTime.now().isAfter(LocalTime.parse("12:00")) && LocalTime.now().isBefore(LocalTime.parse("17:00"))) {
			lblGreeting.setText("Good Afternoon!");
		} else {
			lblGreeting.setText("Good Evening!");
		}
		frame.getContentPane().add(lblGreeting);
		
		
		// Buttons
		
		// Log new symptom
		JButton btnLogSym = new JButton("Log Symptom");
		btnLogSym.setBackground(new Color(153, 153, 255));
		btnLogSym.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnLogSym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymWindow lsw = new LogSymWindow();
				lsw.newLogSymWindow();
			}
		});
		btnLogSym.setBounds(10, 36, 162, 23);
		frame.getContentPane().add(btnLogSym);
		
		// Log new home remedy
		JButton btnLogHR = new JButton("Log Home Remedy");
		btnLogHR.setBackground(new Color(153, 153, 255));
		btnLogHR.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnLogHR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogRemWindow lrw = new LogRemWindow();
				lrw.newLogRem();
			}
		});
		btnLogHR.setBounds(10, 70, 162, 23);
		frame.getContentPane().add(btnLogHR);
		
		// View and edit symptoms
		JButton btnViewSyms = new JButton("View/Edit Symptom Logs");
		btnViewSyms.setBackground(new Color(153, 153, 255));
		btnViewSyms.setFont(new Font("Eras Demi ITC", Font.PLAIN, 10));
		btnViewSyms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Currently editing symptoms
				editSymptoms = true;
				ViewSymLogs vsl = new ViewSymLogs();
				vsl.newViewSymLogs();
			}
		});
		btnViewSyms.setBounds(10, 171, 162, 23);
		frame.getContentPane().add(btnViewSyms);
		
		
		// View and edit home remedies
		JButton btnViewRems = new JButton("View/Edit Remedies");
		btnViewRems.setBackground(new Color(153, 153, 255));
		btnViewRems.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnViewRems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Not editing Symptoms - editing Home Remedies
				editSymptoms = false;
				ViewHomeRems vhr = new ViewHomeRems();
				vhr.newViewHomeRems();
			}
		});
		btnViewRems.setBounds(10, 138, 162, 23);
		frame.getContentPane().add(btnViewRems);
		
		
		// View and edit profile
		JButton btnProfile = new JButton("Profile");
		btnProfile.setBackground(new Color(153, 153, 255));
		btnProfile.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfileWindow pf = new ProfileWindow();
				//pf.newProfileWindow();
			}
		});
		btnProfile.setBounds(10, 104, 162, 23);
		frame.getContentPane().add(btnProfile);
		
		// Create charts with user statistics
		JButton btnCreateStats = new JButton("Create Log Charts");
		btnCreateStats.setBackground(new Color(153, 153, 255));
		btnCreateStats.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnCreateStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MakeStats ms = new MakeStats();
			}
		});
		btnCreateStats.setBounds(10, 205, 162, 23);
		frame.getContentPane().add(btnCreateStats);
		
		
		// Pie chart
		JPanel panel = new JPanel();
		panel.setBounds(200, 36, 286, 192);
		panel.setLayout(new java.awt.BorderLayout());
		
		ChartPanel CP = new ChartPanel(pc.PieChart(s.getTodaysLocations()));
		panel.add(CP,BorderLayout.CENTER);
		panel.validate();
		
		frame.getContentPane().add(panel);
				
		// Button to refresh pie chart
		JButton btnStatRefresh = new JButton("Refresh Stats");
		btnStatRefresh.setBackground(new Color(153, 153, 204));
		btnStatRefresh.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnStatRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Create new chart with updated data and add to panel
				ChartPanel CP = new ChartPanel(pc.PieChart(s.getTodaysLocations()));
				panel.add(CP,BorderLayout.CENTER);
				panel.validate();
			}
		});
		btnStatRefresh.setBounds(331, 239, 155, 23);
		frame.getContentPane().add(btnStatRefresh);
		
	}
}
