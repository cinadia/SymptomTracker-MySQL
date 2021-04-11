package symptomtracker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

public class LogRemWindow {

	SymptomTracker st = new SymptomTracker();
	private JFrame frame;

	/**
	 * Create the LogRemWindow application, where
	 * users can log new home remedies into the database.
	 */
	public LogRemWindow() {
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
		frame.setBounds(100, 100, 239, 196);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Labels
		JLabel lblRemedy = new JLabel("Remedy");
		lblRemedy.setBounds(10, 11, 48, 14);
		frame.getContentPane().add(lblRemedy);
		
		JLabel lblLocation = new JLabel("For");
		lblLocation.setBounds(10, 36, 48, 14);
		frame.getContentPane().add(lblLocation);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblDate);
		
		
		// Combo boxes
		// Location 
		JComboBox cbLocation = new JComboBox(st.getSymptomLocations());
		cbLocation.setSelectedItem(null);
		cbLocation.addActionListener(new ActionListener() {
			// Get selected string and set location
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
		        st.setLocation(location); 
			}
		});
		cbLocation.setBounds(68, 32, 110, 22);
		frame.getContentPane().add(cbLocation);
		
		// Home Remedy
		JComboBox cbRemedy = new JComboBox(st.getHomeRems());
		cbRemedy.setSelectedItem(null);
		cbRemedy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String remedy = (String)cb.getSelectedItem();
				st.setHomeRem(remedy); 
			}
		});
		cbRemedy.setBounds(68, 7, 110, 22);
		frame.getContentPane().add(cbRemedy);
		
		// Date chooser
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(68, 61, 110, 22);
		dateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			            	st.setDate(sqlDate);
			            }
			        }
			    });
		frame.getContentPane().add(dateChooser);
		
		// Save button
		// Runs the runLogHomeRem() method in SymptomTracker
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.runLogHomeRem();
				frame.dispose();
			}
		});
		btnSave.setBounds(68, 110, 89, 23);
		frame.getContentPane().add(btnSave);
		frame.setVisible(true);
	}
}
