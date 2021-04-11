package symptomtracker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

public class EditHomeRemsWindow {
	SymptomTracker st = new SymptomTracker();
	private JFrame frame;
	private static int hrInstance; // ID for the given home remedy log
	
	/**
	 * Constructor to launch the EditHomeRemsWindow application,
	 * where user can edit previously logged Home Remedies.
	 */
	public EditHomeRemsWindow(int instance) {
		hrInstance = instance;	// Set ID/PK of current Home Remedy instance	
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
		JLabel lblRemedy = new JLabel("Remedy");
		lblRemedy.setBounds(10, 11, 48, 14);
		frame.getContentPane().add(lblRemedy);
		
		JLabel lblLocation = new JLabel("For");
		lblLocation.setBounds(10, 36, 48, 14);
		frame.getContentPane().add(lblLocation);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblDate);
		
		// Location combo box
		JComboBox cbLocation = new JComboBox(st.getSymptomLocations());
		//System.out.println("sending instance " + hrInstance + " into getHRLoc method");
		cbLocation.setSelectedItem(st.getHRLocFromInstance(hrInstance));
		cbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				st.setLocation(location); 
			}
		});
		cbLocation.setBounds(68, 32, 110, 22);
		frame.getContentPane().add(cbLocation);
		
		// 
		JComboBox cbRemedy = new JComboBox(st.getHomeRems());
		cbRemedy.setSelectedItem(st.getHomeRemFromInstance(hrInstance)); 
		cbRemedy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				st.setHomeRem(location); 
			}
		});
		cbRemedy.setBounds(68, 7, 110, 22);
		frame.getContentPane().add(cbRemedy);
		
		// Date chooser
		JDateChooser dateChooser = new JDateChooser(); 
		
		// Set default date
		java.util.Date d = st.getHRDateFromInstance(hrInstance);
		java.util.Date defaultDate;
		try {
			defaultDate = new SimpleDateFormat("yyyy-MM-dd").parse(d.toString());
			dateChooser.setDate(defaultDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateChooser.setBounds(68, 61, 110, 22);
		dateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	// Set newly selected date
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			            	st.setDate(sqlDate);
			            }
			        }
			    });
		frame.getContentPane().add(dateChooser);
		
		// Save button, updates the current
		// home remedy instance.
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.updateHRInstance(hrInstance);
				JOptionPane.showMessageDialog(frame, "Updates saved! Please refresh to see changes.");
				frame.dispose();
			}
		});
		btnSave.setBounds(75, 112, 63, 22);
		frame.getContentPane().add(btnSave);
		
		frame.setVisible(true);
		
	}
}
