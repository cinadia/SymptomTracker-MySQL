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

	private JFrame frame;
	private static int hrInstance; 
	
	/**
	 * Launch the application.
	 */
	public static void newEditHomeRemsWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditHomeRemsWindow window = new EditHomeRemsWindow();
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
	public EditHomeRemsWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Remedy");
		lblNewLabel.setBounds(10, 11, 48, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("For");
		lblNewLabel_1.setBounds(10, 36, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox(SymptomTracker.getSymptomLocations());
		comboBox.setSelectedItem(SymptomTracker.getHRLocFromInstance(hrInstance));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				SymptomTracker.setLocation(location); 
			}
		});
		
		comboBox.setBounds(68, 32, 110, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(SymptomTracker.getHomeRems());
		comboBox_1.setSelectedItem(SymptomTracker.getHomeRemFromInstance(hrInstance)); 
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				SymptomTracker.setHomeRem(location); 
			}
		});
		comboBox_1.setBounds(68, 7, 110, 22);
		frame.getContentPane().add(comboBox_1);
		
		JDateChooser dateChooser = new JDateChooser(); 
		
		// Set default date
		java.util.Date d = SymptomTracker.getHRDateFromInstance(hrInstance);
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
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			            	SymptomTracker.setDate(sqlDate);
			            }
			        }
			    });
		frame.getContentPane().add(dateChooser);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SymptomTracker.updateHRInstance(hrInstance);
				System.out.println("SAVE button was clicked.");
				JOptionPane.showMessageDialog(frame, "Updates saved! Please refresh to see changes.");
				frame.dispose();
			}
		});
		btnNewButton.setBounds(75, 112, 63, 22);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	public static void setHRInstance(int ins) {
		hrInstance = ins;
		System.out.println("symptom instance: " + hrInstance);
	}
}
