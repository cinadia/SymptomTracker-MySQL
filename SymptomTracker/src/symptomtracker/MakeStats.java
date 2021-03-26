package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MakeStats {

	private JFrame frame;
	private static String location;
	private static java.sql.Date startDate;
	private static java.sql.Date endDate;

	/**
	 * Launch the application.
	 */
	public static void newMakeStatsWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeStats window = new MakeStats();
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
	public MakeStats() {
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
		
		JLabel titleLabel = new JLabel("Create your own log charts!");
		titleLabel.setBounds(136, 11, 165, 14);
		frame.getContentPane().add(titleLabel);
		
		JLabel startLabel = new JLabel("Start Date");
		startLabel.setBounds(10, 49, 101, 14);
		frame.getContentPane().add(startLabel);
		
		JLabel endLabel = new JLabel("End Date");
		endLabel.setBounds(10, 89, 67, 14);
		frame.getContentPane().add(endLabel);
		
		JDateChooser chooseStartDate = new JDateChooser();
		chooseStartDate.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	startDate = new java.sql.Date(utilDate.getTime());
			            	System.out.println(startDate);
			            }
			        }
			    });
		chooseStartDate.setBounds(105, 43, 123, 20);
		frame.getContentPane().add(chooseStartDate);
		
		JDateChooser chooseEndDate = new JDateChooser();
		chooseEndDate.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	endDate = new java.sql.Date(utilDate.getTime());
			            	System.out.println(endDate);
			            }
			        }
			    });
		chooseEndDate.setBounds(105, 83, 123, 20);
		frame.getContentPane().add(chooseEndDate);
		
		JLabel chooseLocationLabel = new JLabel("Choose Location");
		chooseLocationLabel.setBounds(10, 138, 90, 14);
		frame.getContentPane().add(chooseLocationLabel);
		
		JComboBox chooseLocation = new JComboBox(Getters.getSymptomLocations());
		chooseLocation.setSelectedItem(null);
		chooseLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        location = (String)cb.getSelectedItem();
			}
		});
		chooseLocation.setBounds(105, 134, 90, 22);
		frame.getContentPane().add(chooseLocation);
		
		JButton makeChartButt = new JButton("Make Chart!");
		makeChartButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LineChart chart = new LineChart(Stats.getScoresDates(startDate, endDate, location), location);
				chart.pack();
				chart.setVisible(true);
			}
		});
		makeChartButt.setBounds(161, 194, 115, 23);
		frame.getContentPane().add(makeChartButt);
	}
}
