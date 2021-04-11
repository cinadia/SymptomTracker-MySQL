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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class EditLogsWindow {

	SymptomTracker st = new SymptomTracker();
	private JFrame frame;
	private JTextField txtFieldDur;
	private JLabel lblCalculatedFinal;
	private static int symptomInstance; // ID for the given symptom instance log
	
	/**
	 * Launch the application.
	 */
//	public static void newEditLogsWindow() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditLogsWindow window = new EditLogsWindow();
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
//	public EditLogsWindow() {
//		initialize();
//	}
	
	/**
	 * Constructor to launch the EditLogsWindow application,
	 * where user can edit previously logged symptoms.
	 */
	public EditLogsWindow(int instance) {
		symptomInstance = instance;
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
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 11, 71, 14);
		frame.getContentPane().add(lblLocation);
		
		JLabel lblType = new JLabel("Symptom Type");
		lblType.setBounds(10, 36, 86, 14);
		frame.getContentPane().add(lblType);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblSeverity = new JLabel("Severity");
		lblSeverity.setBounds(10, 86, 48, 14);
		frame.getContentPane().add(lblSeverity);
		
		JLabel lblDuration = new JLabel("Duration (min)");
		lblDuration.setBounds(10, 116, 105, 14);
		frame.getContentPane().add(lblDuration);
		
		JLabel lblFinScore = new JLabel("Final Score");
		lblFinScore.setBounds(10, 141, 71, 14);
		frame.getContentPane().add(lblFinScore);
		
		// Symptom Location dropdown 
		JComboBox cbLocation = new JComboBox(st.getSymptomLocations());
		// Set default item 
		cbLocation.setSelectedItem(st.getSymLocFromInstance(symptomInstance));
		cbLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				st.setLocation(location); 
			}
		});
		cbLocation.setBounds(125, 7, 86, 22);
		frame.getContentPane().add(cbLocation);
		
		// Symptom Type dropdown
		JComboBox cbType = new JComboBox(st.getSymptoms());
		// Set default item
		cbType.setSelectedItem(st.getSymTypeFromInstance(symptomInstance));
		cbType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
				String symptom = (String)cb.getSelectedItem();
				st.setSymptom(symptom);
			}
		});
		cbType.setBounds(125, 32, 86, 22);
		frame.getContentPane().add(cbType);
		
		
		// Final score label 
		lblCalculatedFinal = new JLabel("CALCULATING...");
		lblCalculatedFinal.setBounds(125, 141, 128, 14);
		frame.getContentPane().add(lblCalculatedFinal);
				
		
		// Severity radio buttons
		JRadioButton rb1 = new JRadioButton("1");
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(1, lblCalculatedFinal);
			}
		});
		rb1.setBounds(125, 82, 37, 23);
		frame.getContentPane().add(rb1);
		
		JRadioButton rb2 = new JRadioButton("2");
		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(2, lblCalculatedFinal);
			}
		});
		rb2.setBounds(164, 82, 37, 23);
		frame.getContentPane().add(rb2);
		
		JRadioButton rb3 = new JRadioButton("3");
		rb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(3, lblCalculatedFinal);
			}
		});
		rb3.setBounds(203, 82, 37, 23);
		frame.getContentPane().add(rb3);
		
		JRadioButton rb4 = new JRadioButton("4");
		rb4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(4, lblCalculatedFinal);
			}
		});
		rb4.setBounds(242, 82, 37, 23);
		frame.getContentPane().add(rb4);
		
		JRadioButton rb5 = new JRadioButton("5");
		rb5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.setCalculatedScore(5, lblCalculatedFinal);
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
	    
	    // Set default radio button
	    int defaultScore = st.getSeverityFromInstance(symptomInstance);
	    
	    if (defaultScore == 1) {
	    	rb1.setSelected(true);
	    }
	    else if (defaultScore == 2) {
	    	rb2.setSelected(true);
	    }
	    else if (defaultScore == 3) {
	    	rb3.setSelected(true);
	    }
	    else if (defaultScore == 4) {
	    	rb4.setSelected(true);
	    }
	    else if (defaultScore == 5) {
	    	rb5.setSelected(true);
	    }
	    
	    // Duration text field
		txtFieldDur = new JTextField(st.getDurationFromInstance(symptomInstance));
    	int sev = st.getSeverity();
    	System.out.println("severity " + sev);
    	System.out.println("duration " + st.getLength());
    	if (sev != -1) {
    		int score = sev*st.getLength();
    		lblCalculatedFinal.setText(Integer.toString(score));
			st.setScore(score);
    	}
		txtFieldDur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String text = txtFieldDur.getText();
			    if (st.isValid(text)) {
			    	Integer dur = Integer.parseInt(text);
			    	st.setLength(dur);
			    	int sev = st.getSeverity();
			     } else {
			    	 JOptionPane.showMessageDialog(frame, "Whoops! Positive numbers only!");
			    	 }
			  }
		});
		txtFieldDur.setBounds(125, 113, 42, 20);
		frame.getContentPane().add(txtFieldDur);
		txtFieldDur.setColumns(10);
		
		// SAVE button
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				st.updateSymInstance(symptomInstance);
				System.out.println("SAVE button was clicked.");
				System.out.println(st.getScore());
				JOptionPane.showMessageDialog(frame, "Updates saved! Please refresh to see changes.");
				
				frame.dispose();
			}
		});
		btnSave.setBounds(174, 166, 89, 23);
		frame.getContentPane().add(btnSave);
		
		// Date
		JDateChooser dateChooser = new JDateChooser();
		
		// Set default date
		java.util.Date d = st.getSymDateFromInstance(symptomInstance);
		java.util.Date defaultDate;
		try {
			defaultDate = new SimpleDateFormat("yyyy-MM-dd").parse(d.toString());
			dateChooser.setDate(defaultDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		dateChooser.setBounds(125, 61, 154, 22);
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
		frame.setVisible(true);

	}
}
