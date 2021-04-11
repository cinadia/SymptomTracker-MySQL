package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;

public class LogSymWindow {

	SymptomTracker st = new SymptomTracker();
	private JFrame frame;
	private JTextField txtDuration;
	private JLabel lblCalculatedScore;

	/**
	 * Launch the LogSymWindow application, where
	 * users can log new symptoms into the database.
	 */
	public void newLogSymWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogSymWindow window = new LogSymWindow();
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
	public LogSymWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Labels
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		lblLocation.setBounds(10, 11, 71, 14);
		frame.getContentPane().add(lblLocation);
		
		JLabel lblSymptom = new JLabel("Symptom Type");
		lblSymptom.setBounds(10, 36, 86, 14);
		lblSymptom.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		frame.getContentPane().add(lblSymptom);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblDate);
		lblDate.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		JLabel lblSeverity = new JLabel("Severity");
		lblSeverity.setBounds(10, 86, 48, 14);
		frame.getContentPane().add(lblSeverity);
		lblSeverity.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		JLabel lblDuration = new JLabel("Duration (min)");
		lblDuration.setBounds(10, 116, 105, 14);
		frame.getContentPane().add(lblDuration);
		lblDuration.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		JLabel lblFinalScore = new JLabel("Final Score");
		lblFinalScore.setBounds(10, 141, 71, 14);
		frame.getContentPane().add(lblFinalScore);
		lblFinalScore.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		// Final score dynamic label 
		lblCalculatedScore = new JLabel("CALCULATING...");
		lblCalculatedScore.setBounds(125, 141, 128, 14);
		frame.getContentPane().add(lblCalculatedScore);
		lblCalculatedScore.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));

		
		// Comboboxes
		// Symptom Location combo box (dropdown)
		JComboBox cbLocation = new JComboBox(st.getSymptomLocations());
		cbLocation.setBackground(new Color(204, 255, 255));
		cbLocation.setSelectedItem(null);
		cbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Get selected String and set location in SymptomTracker class
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				st.setLocation(location); 
			}
		});
		cbLocation.setBounds(125, 7, 86, 22);
		frame.getContentPane().add(cbLocation);
		
		// Symptom Type dropdown
		JComboBox cbSymType = new JComboBox(st.getSymptoms());
		cbSymType.setBackground(new Color(204, 255, 255));
		cbSymType.setSelectedItem(null);
		cbSymType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Get selected String and set symptom 
				// in SymptomTracker class
				JComboBox cb = (JComboBox)arg0.getSource();
				String symptom = (String)cb.getSelectedItem();
				st.setSymptom(symptom);
			}
		});
		cbSymType.setBounds(125, 32, 86, 22);
		frame.getContentPane().add(cbSymType);
		
	
		// Severity radio buttons
		/* Set severity for corresponding radio buttons and 
		 * calculate and display final score using
		 * setScore(int) method.
		 */
		
		JRadioButton rb1 = new JRadioButton("1");
		rb1.setBackground(new Color(204, 255, 255));
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(1, lblFinalScore);
			}
		});
		rb1.setBounds(125, 82, 37, 23);
		frame.getContentPane().add(rb1);
		
		JRadioButton rb2 = new JRadioButton("2");
		rb2.setBackground(new Color(204, 255, 255));
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(2, lblFinalScore);			
				}
		});
		rb2.setBounds(164, 82, 37, 23);
		frame.getContentPane().add(rb2);
		
		JRadioButton rb3 = new JRadioButton("3");
		rb3.setBackground(new Color(204, 255, 255));
		rb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(3, lblFinalScore);			
				}
		});
		rb3.setBounds(203, 82, 37, 23);
		frame.getContentPane().add(rb3);
		
		JRadioButton rb4 = new JRadioButton("4");
		rb4.setBackground(new Color(204, 255, 255));
		rb4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(4, lblFinalScore);			
				}
		});
		rb4.setBounds(242, 82, 37, 23);
		frame.getContentPane().add(rb4);
		
		JRadioButton rb5 = new JRadioButton("5");
		rb5.setBackground(new Color(204, 255, 255));
		rb5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(1, lblFinalScore);			
				}
		});
		rb5.setBounds(281, 82, 37, 23);
		frame.getContentPane().add(rb5);
		
		// Group radio buttons
		ButtonGroup group = new ButtonGroup();
	    group.add(rb1);
	    group.add(rb2);
	    group.add(rb3);
	    group.add(rb4);
	    group.add(rb5);
		
	    // Duration text field
		txtDuration = new JTextField();
		txtDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String text = txtDuration.getText();
			    if (st.isValid(text)) {
			    	Integer dur = Integer.parseInt(text);
			    	st.setLength(dur);
			    	int sev = st.getSeverity();
			    	if (sev != -1) {
			    		int score = sev*dur;
			    		lblCalculatedScore.setText(Integer.toString(score));
						st.setScore(score);
			    	}
			     } else {
			    	 JOptionPane.showMessageDialog(frame, "Whoops! Positive numbers only!");
			    	 }
			  }
		});
		txtDuration.setBounds(125, 113, 42, 20);
		frame.getContentPane().add(txtDuration);
		txtDuration.setColumns(10);
		
		
		// SAVE button
		// Runs the runLogSymptom() method in SymptomTracker
		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Eras Demi ITC", Font.PLAIN, 11));
		btnSave.setBackground(new Color(204, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.runLogSymptom();
				// TODO remove print
				System.out.println("saved score " + st.score);
				frame.dispose();
			}
		});
		btnSave.setBounds(174, 166, 89, 23);
		frame.getContentPane().add(btnSave);
		
		// Date chooser
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(125, 61, 115, 20);
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
	}
	
	/**
	 * Sets the final calculated score. Score is 
	 * calculated by severity multiplied by length.
	 * @param severity the provided severity
	 */
	private void setScore(int severity) {
		st.setSeverity(severity);
		int length = st.getLength();
		if (length != -1) {
			int score = severity*length;
			lblCalculatedScore.setText(Integer.toString(score));
			st.setScore(score);
			System.out.println("Setting score as " + score);
	}
	
	}
}
