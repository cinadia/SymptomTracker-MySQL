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

public class HomePage {

	private JFrame frame;
	private boolean editSymptoms;

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
		frame.setBounds(100, 100, 500, 311);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				LogRemWindow.newLogRem();
			}
		});
		btnNewButton_1.setBounds(10, 70, 162, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View/Edit Remedies");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editSymptoms = false;
				ViewHomeRems.newViewHomeRems();
				
			}
		});
		btnNewButton_2.setBounds(10, 138, 162, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Profile");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfileWindow.newProfileWindow();
			}
		});
		btnNewButton_3.setBounds(10, 104, 162, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(201, 52, 244, 174);
		panel.setLayout(new java.awt.BorderLayout());
		
		ChartPanel CP = new ChartPanel(PieChart.PieChart(Stats.getTodaysLocations()));
		panel.add(CP,BorderLayout.CENTER);
		panel.validate();
		
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_5 = new JLabel("GREETING");
		lblNewLabel_5.setBounds(263, 11, 101, 14);
		if (LocalTime.now().isBefore(LocalTime.parse("12:00"))) {
			lblNewLabel_5.setText("Good Morning!");
		} else if (LocalTime.now().isAfter(LocalTime.parse("12:00")) && LocalTime.now().isBefore(LocalTime.parse("17:00"))) {
			lblNewLabel_5.setText("Good Afternoon!");
		} else {
			lblNewLabel_5.setText("Good Evening!");
		}
		frame.getContentPane().add(lblNewLabel_5);
		
		
		JButton createStatsButt = new JButton("Create Log Charts");
		createStatsButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MakeStats.newMakeStatsWindow();
			}
		});
		createStatsButt.setBounds(10, 205, 162, 23);
		frame.getContentPane().add(createStatsButt);
		
		JButton btnNewButton_5 = new JButton("View/Edit Symptom Logs");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editSymptoms = true;
				ViewSymLogs.newViewSymLogs();
			}
		});
		btnNewButton_5.setBounds(10, 171, 162, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnStatRefresh = new JButton("Refresh Stats");
		btnStatRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChartPanel CP = new ChartPanel(PieChart.PieChart(Stats.getTodaysLocations()));
				panel.add(CP,BorderLayout.CENTER);
				panel.validate();
			}
		});
		btnStatRefresh.setBounds(286, 237, 155, 23);
		frame.getContentPane().add(btnStatRefresh);
	}
}
